
#include <SPI.h>
#include <MFRC522.h>
#include <LiquidCrystal_I2C.h>
#include <Ethernet.h>
#include <MySQL_Connection.h>
#include <MySQL_Cursor.h>

#define SS_PIN 8 //usando a porta 8 pq a ethernet shield vai utilizar a porta 10
#define RST_PIN 9 //9
MFRC522 mfrc522(SS_PIN, RST_PIN); // criar a instancia MFRC522.

LiquidCrystal_I2C lcd(0x3F, 16, 2);
int Speaker = 7;
int LedConfrima = 2;
int LedAlerta = 4;

byte mac_addr[] = { 0xDE, 0xAD, 0xBE, 0xEF, 0xFE, 0xED };

IPAddress ip(192, 168, 1, 25); // ip arduino

IPAddress servidor_bd(192,168,1,24);  // IP do Servidor Mysql
char user[] = "root";              // Usuario do MySQL
char password[] = "123456";        // Senha do Usuario MySQL

char cartao[] = "";
String cardapio ="";

// query para inserir dados
char INSERT_SQL[] = "INSERT INTO NAMEDIDA.medida(CARTAO,QUANT,DATA)VALUES('%s',%d,CURDATE())";

// Buscar cartão no banco de dados
char CARTAO_SQL[] = "SELECT nome FROM NAMEDIDA.tb_aluno where cartao='%s'";

//Verifica o cartão
char VERIFICA_SQL[] = "SELECT cartao FROM NAMEDIDA.medida where cartao='%s' and data=CURDATE()";

char query[80];

EthernetClient client;
MySQL_Connection conn((Client *)&client);

void setup() {
  Serial.begin(9600);
  SPI.begin(); // Inicia  SPI bus
  mfrc522.PCD_Init();  // Inicia MFRC522

  pinMode(Speaker, OUTPUT);
  pinMode(LedConfrima, OUTPUT);
  pinMode(LedAlerta, OUTPUT);
  
  // inicia o lcd
  lcd.init();
  lcd.backlight();
  lcd.clear();
 
  mensageminicial();
  
         Ethernet.begin(mac_addr, ip);

         if (conn.connect(servidor_bd, 3306, user, password)) {
            delay(1000);
            
            } else {
              
            lcd.clear(); 
            lcd.setCursor(2,1);
             lcd.print(F("A Coneção Falhou!"));
         }
          
 mensageminicial();
}

void alertaLed(){
  
  for (int i=1; i <= 10; i++){
      digitalWrite(LedAlerta, HIGH);  
      delay(100);                 
      digitalWrite(LedAlerta, LOW);   
      delay(100);     
   }
       
}

void confirmaLed(){    
     digitalWrite(LedConfrima, HIGH);  
     delay(2000);                 
     digitalWrite(LedConfrima, LOW);   
     delay(2000);
}

void mensageminicial(){
  lcd.clear();
  lcd.print(F("APROXIME SEU CARTAO"));  
  lcd.setCursor(5,1);
  lcd.print((cardapio));  
  lcd.setCursor(0,2);
  lcd.print(F("    PARA LANCHAR"));  

}

void loop() {

  // busca novo cartão
  if ( ! mfrc522.PICC_IsNewCardPresent()) 
  {
    return;
  }
  // Select one of the cards
  if ( ! mfrc522.PICC_ReadCardSerial()) 
  {
    return;
  }
  //Mostra UID na serial
  Serial.print(F("Codigo do cartao: "));

  String conteudo= "";

  for (byte i = 0; i < mfrc522.uid.size; i++) 
     {

     
    // Serial.print(mfrc522.uid.uidByte[i] < 0x10 ? " 0" : "");
    // Serial.print(mfrc522.uid.uidByte[i], HEX);
     
     conteudo.concat(String(mfrc522.uid.uidByte[i] < 0x10 ? " 0" : ""));
     conteudo.concat(String(mfrc522.uid.uidByte[i], HEX));
     }

        conteudo.toUpperCase();
        conteudo.toCharArray(cartao, 9);

        MySQL_Cursor *cur_mem = new MySQL_Cursor(&conn);
        
        sprintf(query, CARTAO_SQL, cartao); //busca os dados no banco
        Serial.println(query); //mostra o select
        cur_mem->execute(query); //executa a query informada

        column_names *cols = cur_mem->get_columns();
        for (int f = 0; f < cols->num_fields; f++) {
      //      Serial.print(cols->fields[f]->name); //mostra a columa da tabela
            if (f < cols->num_fields-1) {
          //     Serial.print(F(", "));
               }
        }
        
        row_values *row = NULL;
        row_values *linhas = NULL;
        int confirmado = 0;
        int inserir = 1;
        String NomeAluno;

        do {
           row = cur_mem->get_next_row();
           if (row != NULL) {

              NomeAluno = (row->values[0]);
              confirmado = 1;        
            }
    
         }while (row != NULL);

         if (confirmado == 1) {

             sprintf(query,VERIFICA_SQL, cartao);
             cur_mem->execute(query); //executa a query para verificar se ja votou hoje

             column_names *colunas = cur_mem->get_columns();
             for (int A = 0; A < colunas->num_fields; A++) {
       //          Serial.print(colunas->fields[A]->name); //mostra a columa da tabela
                 if (A < colunas->num_fields-1) {
                 Serial.println(", ");
                 }
              }

             row_values *linhas = NULL;

             do{
                linhas = cur_mem->get_next_row();
                if (linhas != NULL) {
                   inserir = 0;
                  }
                  
              } while (linhas != NULL);
              
             if (inserir == 1){
              
                sprintf(query, INSERT_SQL, cartao, 1); //gravar os dados no banco       
                cur_mem->execute(query);
 
                lcd.clear();
                lcd.setCursor(0,1);
                lcd.print(F("REFEICAO CONFIRMADA!"));
                lcd.setCursor(6,2);
                lcd.print(NomeAluno);
                tone(Speaker,440,100);
                confirmaLed();
                 
               } else {

                lcd.clear();
                lcd.setCursor(4,1);
                lcd.print(F("REGISTRO JA")); //se o cartão já estiver passado no mesmo dia, o registro já foi confirmado
                lcd.setCursor(4,2);
                lcd.print(F("CONFIRMADO!")); 
           //     tone(Speaker,392,500);
          //      alertaLed();
                }
          
          } else {
      
           // lcd.clear();
           // lcd.setCursor(2,1);
           // lcd.print(F("CARTAO INVALIDO!")); //se o cartão não estiver cadastrado no banco de dados o cartão e invalido
          //  tone(Speaker,392,500);
          //  alertaLed(); 
          }
      delay(3000);
      mensageminicial(); 
     
      delete cur_mem;  

}
