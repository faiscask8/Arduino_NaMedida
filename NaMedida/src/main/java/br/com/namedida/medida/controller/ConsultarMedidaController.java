package br.com.namedida.medida.controller;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Produces;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.namedida.model.MedidaModel;
import br.com.namedida.repository.MedidaRepository;

@Named(value = "consultarMedidaController")
@ViewScoped
public class ConsultarMedidaController implements Serializable {

	private static final long serialVersionUID = 1L;

	public static int TotaAlunos = 0;

	@Inject
	transient private MedidaModel medidaModel;

	@Produces
	private List<MedidaModel> medidas;

	@Inject
	transient private MedidaRepository medidaRepository;

	public static int TotalAgora = 0;

	String DiaHoje = null;

	public String getDiaHoje() {
		return DiaHoje;
	}

	public void setDiaHoje(String diaHoje) {
		DiaHoje = diaHoje;
	}

	public Integer getTotalAgora() {
		return TotalAgora;
	}

	public void setTotalAgora(Integer totalAgora) {
		TotalAgora = totalAgora;
	}

	public List<MedidaModel> getMedidas() {
		return medidas;
	}

	public void setMedidas(List<MedidaModel> medidas) {
		this.medidas = medidas;
	}

	public MedidaModel getMedidaModel() {
		return medidaModel;
	}

	public void setMedidaModel(MedidaModel medidaModel) {
		this.medidaModel = medidaModel;
	}

	/***
	 * CARREGA INFORMAÇÕES DE UMA MEDIDA PARA SER EDITADO
	 * 
	 * @param alunoModel
	 */
	public void Editar(MedidaModel medidaModel) {

		this.medidaModel = medidaModel;

	}

	/***
	 * CARREGA INFORMAÇÕES DE UMA MEDIDA PARA SER EDITADO
	 */
	public void TotalAlunos() {

		this.medidaRepository.TotalAlunos();

		medidaModel.setTotalhoje(TotalAgora);
		System.out.println("total hoje: " + medidaModel.getTotalhoje());
		TotaAlunos = medidaModel.getTotalhoje();

	}

	public void DiaHoje() {

		this.medidaRepository.DiaHoje();
		medidaModel.setDiahoje(DiaHoje);
		System.out.println("hoje é Controller: " + medidaModel.getDiahoje());
	}

	/***
	 * ATUALIZA O REGISTRO QUE FOI ALTERADO
	 */
	public void AlterarRegistro() {

		this.medidaRepository.AlterarRegistro(this.medidaModel);

		/* RECARREGA OS REGISTROS */
		this.init();
	}

	/***
	 * EXCLUINDO UM REGISTRO
	 * 
	 * @param medidaModel
	 */
	public void ExcluirMedida(MedidaModel medidaModel) {

		// EXCLUI UMA MEDIDA DO BANCO DE DADOS
		this.medidaRepository.ExcluirRegistro(medidaModel.getCodigo());

		// REMOVENDO UMA MEDIDA DA LISTA
		// ASSIM QUE É A MEDIDA É REMOVIDA DA LISTA O DATATABLE É ATUALIZADO
		this.medidas.remove(medidaModel);

	}

	/***
	 * CARREGA OS ALUNOS NA INICIALIZAÇÃO
	 */
	@PostConstruct
	public void init() {

		// RETORNAR OS ALUNOS CADASTRADOS
		// this.medidas = medidaRepository.GetMedidas();
	}

}
