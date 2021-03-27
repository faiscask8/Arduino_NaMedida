-- MySQL dump 10.13  Distrib 8.0.12, for macos10.13 (x86_64)
--
-- Host: 127.0.0.1    Database: naMedida
-- ------------------------------------------------------
-- Server version	8.0.12

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
 SET NAMES utf8 ;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `medida`
--

DROP TABLE IF EXISTS `medida`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `medida` (
  `id_medida` int(11) NOT NULL AUTO_INCREMENT,
  `cartao` varchar(255) DEFAULT NULL,
  `data` tinyblob,
  `quant` int(11) DEFAULT NULL,
  PRIMARY KEY (`id_medida`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `medida`
--

LOCK TABLES `medida` WRITE;
/*!40000 ALTER TABLE `medida` DISABLE KEYS */;
/*!40000 ALTER TABLE `medida` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `refeicoes`
--

DROP TABLE IF EXISTS `refeicoes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `refeicoes` (
  `id_refeicao` int(11) NOT NULL AUTO_INCREMENT,
  `data` datetime DEFAULT NULL,
  `totalalunos` int(11) DEFAULT NULL,
  `id_cardapio` int(11) DEFAULT NULL,
  `id_usuario_cadastro` int(11) DEFAULT NULL,
  PRIMARY KEY (`id_refeicao`),
  KEY `FK_14sr9tedd9suon3c7p9kh9hfx` (`id_cardapio`),
  KEY `FK_a5s2rf1rw9mdelu7o6dxwydp6` (`id_usuario_cadastro`),
  CONSTRAINT `FK_14sr9tedd9suon3c7p9kh9hfx` FOREIGN KEY (`id_cardapio`) REFERENCES `tb_cardapio` (`id_cardapio`),
  CONSTRAINT `FK_a5s2rf1rw9mdelu7o6dxwydp6` FOREIGN KEY (`id_usuario_cadastro`) REFERENCES `tb_usuario` (`id_usuario`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `refeicoes`
--

LOCK TABLES `refeicoes` WRITE;
/*!40000 ALTER TABLE `refeicoes` DISABLE KEYS */;
/*!40000 ALTER TABLE `refeicoes` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_aluno`
--

DROP TABLE IF EXISTS `tb_aluno`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `tb_aluno` (
  `id_aluno` int(11) NOT NULL AUTO_INCREMENT,
  `cartao` varchar(255) DEFAULT NULL,
  `dt_cadastro` datetime DEFAULT NULL,
  `ds_email` varchar(255) DEFAULT NULL,
  `nome` varchar(255) DEFAULT NULL,
  `id_usuario_cadastro` int(11) DEFAULT NULL,
  PRIMARY KEY (`id_aluno`),
  KEY `FK_7dowty02gst1hdnydspeqosta` (`id_usuario_cadastro`),
  CONSTRAINT `FK_7dowty02gst1hdnydspeqosta` FOREIGN KEY (`id_usuario_cadastro`) REFERENCES `tb_usuario` (`id_usuario`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_aluno`
--

LOCK TABLES `tb_aluno` WRITE;
/*!40000 ALTER TABLE `tb_aluno` DISABLE KEYS */;
/*!40000 ALTER TABLE `tb_aluno` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_cardapio`
--

DROP TABLE IF EXISTS `tb_cardapio`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `tb_cardapio` (
  `id_cardapio` int(11) NOT NULL AUTO_INCREMENT,
  `nm_descricao` varchar(255) DEFAULT NULL,
  `id_usuario_cadastro` int(11) DEFAULT NULL,
  PRIMARY KEY (`id_cardapio`),
  KEY `FK_jxf44ynasi33jq177lm4a2qdj` (`id_usuario_cadastro`),
  CONSTRAINT `FK_jxf44ynasi33jq177lm4a2qdj` FOREIGN KEY (`id_usuario_cadastro`) REFERENCES `tb_usuario` (`id_usuario`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_cardapio`
--

LOCK TABLES `tb_cardapio` WRITE;
/*!40000 ALTER TABLE `tb_cardapio` DISABLE KEYS */;
/*!40000 ALTER TABLE `tb_cardapio` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_dia`
--

DROP TABLE IF EXISTS `tb_dia`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `tb_dia` (
  `id_dia` int(11) NOT NULL AUTO_INCREMENT,
  `descricao` varchar(255) DEFAULT NULL,
  `id_cardapio` int(11) DEFAULT NULL,
  `id_usuario_cadastro` int(11) DEFAULT NULL,
  PRIMARY KEY (`id_dia`),
  KEY `FK_gtk9rpjne71x55x28j3trjmyl` (`id_cardapio`),
  KEY `FK_loxq39byili2qg8or20n1bayv` (`id_usuario_cadastro`),
  CONSTRAINT `FK_gtk9rpjne71x55x28j3trjmyl` FOREIGN KEY (`id_cardapio`) REFERENCES `tb_cardapio` (`id_cardapio`),
  CONSTRAINT `FK_loxq39byili2qg8or20n1bayv` FOREIGN KEY (`id_usuario_cadastro`) REFERENCES `tb_usuario` (`id_usuario`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_dia`
--

LOCK TABLES `tb_dia` WRITE;
/*!40000 ALTER TABLE `tb_dia` DISABLE KEYS */;
/*!40000 ALTER TABLE `tb_dia` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_item_cardapio`
--

DROP TABLE IF EXISTS `tb_item_cardapio`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `tb_item_cardapio` (
  `id_itens` int(11) NOT NULL AUTO_INCREMENT,
  `qt_quant` float DEFAULT NULL,
  `id_cardapio` int(11) NOT NULL,
  `id_produto` int(11) NOT NULL,
  PRIMARY KEY (`id_itens`),
  KEY `FK_pond7vpk8m6fur77vhsnu1t7o` (`id_cardapio`),
  KEY `FK_l88m2kphedmeycuhu2etm1axo` (`id_produto`),
  CONSTRAINT `FK_l88m2kphedmeycuhu2etm1axo` FOREIGN KEY (`id_produto`) REFERENCES `tb_produto` (`id_produto`),
  CONSTRAINT `FK_pond7vpk8m6fur77vhsnu1t7o` FOREIGN KEY (`id_cardapio`) REFERENCES `tb_cardapio` (`id_cardapio`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_item_cardapio`
--

LOCK TABLES `tb_item_cardapio` WRITE;
/*!40000 ALTER TABLE `tb_item_cardapio` DISABLE KEYS */;
/*!40000 ALTER TABLE `tb_item_cardapio` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_item_refeicoes`
--

DROP TABLE IF EXISTS `tb_item_refeicoes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `tb_item_refeicoes` (
  `id_itens` int(11) NOT NULL AUTO_INCREMENT,
  `qt_quant` float DEFAULT NULL,
  `id_produto` int(11) NOT NULL,
  `id_refeicoes` int(11) NOT NULL,
  PRIMARY KEY (`id_itens`),
  KEY `FK_jsor3qxnrq15ymcjsxjnmxbhv` (`id_produto`),
  KEY `FK_n6ajc55rk0xvf4sxy1h8a54y2` (`id_refeicoes`),
  CONSTRAINT `FK_jsor3qxnrq15ymcjsxjnmxbhv` FOREIGN KEY (`id_produto`) REFERENCES `tb_produto` (`id_produto`),
  CONSTRAINT `FK_n6ajc55rk0xvf4sxy1h8a54y2` FOREIGN KEY (`id_refeicoes`) REFERENCES `refeicoes` (`id_refeicao`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_item_refeicoes`
--

LOCK TABLES `tb_item_refeicoes` WRITE;
/*!40000 ALTER TABLE `tb_item_refeicoes` DISABLE KEYS */;
/*!40000 ALTER TABLE `tb_item_refeicoes` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_produto`
--

DROP TABLE IF EXISTS `tb_produto`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `tb_produto` (
  `id_produto` int(11) NOT NULL AUTO_INCREMENT,
  `nm_descricao` varchar(255) DEFAULT NULL,
  `id_usuario_cadastro` int(11) DEFAULT NULL,
  PRIMARY KEY (`id_produto`),
  KEY `FK_nltjfbh4kyfqj59d8s7ogf263` (`id_usuario_cadastro`),
  CONSTRAINT `FK_nltjfbh4kyfqj59d8s7ogf263` FOREIGN KEY (`id_usuario_cadastro`) REFERENCES `tb_usuario` (`id_usuario`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_produto`
--

LOCK TABLES `tb_produto` WRITE;
/*!40000 ALTER TABLE `tb_produto` DISABLE KEYS */;
/*!40000 ALTER TABLE `tb_produto` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_usuario`
--

DROP TABLE IF EXISTS `tb_usuario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `tb_usuario` (
  `id_usuario` int(11) NOT NULL AUTO_INCREMENT,
  `ds_senha` varchar(255) DEFAULT NULL,
  `ds_login` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id_usuario`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_usuario`
--

LOCK TABLES `tb_usuario` WRITE;
/*!40000 ALTER TABLE `tb_usuario` DISABLE KEYS */;
INSERT INTO `tb_usuario` VALUES (1,'123','admin');
/*!40000 ALTER TABLE `tb_usuario` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-10-08 21:08:56
