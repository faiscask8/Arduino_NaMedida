package br.com.namedida.dia.controller;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Produces;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.namedida.medida.controller.ConsultarMedidaController;
import br.com.namedida.model.DiaModel;
import br.com.namedida.repository.DiaRepository;

@Named(value = "consultarDiaController")
@ViewScoped
public class ConsultarDiaController implements Serializable {

	private static final long serialVersionUID = 1L;

	public static String CardapioDoDia = null;

	@Inject
	transient private DiaModel diaModel;

	@Inject
	transient private ConsultarMedidaController consultarMedidaController;

	@Produces
	private List<DiaModel> dias;

	@Inject
	transient private DiaRepository diaRepository;

	public List<DiaModel> getDias() {
		return dias;
	}

	public void setDias(List<DiaModel> dias) {
		this.dias = dias;
	}

	public DiaModel getdiaModel() {
		return diaModel;
	}

	public void setdiaModel(DiaModel diaModel) {
		this.diaModel = diaModel;
	}

	/***
	 * CARREGA INFORMAÇÕES DE UM DIA PARA SER EDITADO
	 * 
	 * @param diaModel
	 */
	public void Editar(DiaModel diaModel) {

		this.diaModel = diaModel;

	}

	/***
	 * ATUALIZA O REGISTRO QUE FOI ALTERADO
	 */
	public void AlterarRegistro() {

		this.diaRepository.AlterarRegistro(this.diaModel);

		/* RECARREGA OS REGISTROS */
		this.init();
	}

	/***
	 * EXCLUINDO UM REGISTRO
	 * 
	 * @param diaModel
	 */
	public void Excluirdia(DiaModel diaModel) {

		// EXCLUI UM DIA DO BANCO DE DADOS
		this.diaRepository.ExcluirRegistro(diaModel.getCodigo());

		// REMOVENDO O DIA DA LISTA
		// ASSIM QUE É O DIA É REMOVIDA DA LISTA O DATATABLE É ATUALIZADO
		this.dias.remove(diaModel);

	}

	public void GetCardapioDia(String diasemana) {

		System.out.println("entrei agora dia: "
				+ consultarMedidaController.getDiaHoje());
		this.diaRepository.GetCardapioDia(consultarMedidaController
				.getDiaHoje());

		System.out.println("Cardapio do Dia: "
				+ diaRepository.getCardapiododia());
		CardapioDoDia = diaRepository.getCardapiododia();
		diaModel.setCardapiohoje(diaRepository.getCardapiododia());

	}

	/***
	 * CARREGA OS DIAS NA INICIALIZAÇÃO
	 */
	@PostConstruct
	public void init() {

		// RETORNAR OS diaS CADASTRADOS
		this.dias = diaRepository.GetDias();
	}

}
