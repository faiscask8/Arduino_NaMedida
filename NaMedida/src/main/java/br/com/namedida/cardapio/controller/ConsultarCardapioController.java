package br.com.namedida.cardapio.controller;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Produces;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.namedida.model.CardapioModel;
import br.com.namedida.repository.CardapioRepository;

@Named(value = "consultarCardapioController")
@ViewScoped
public class ConsultarCardapioController implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	transient private CardapioModel cardapioModel;

	@Inject
	ConsultarItemCardapioController consultarItemCardapioController;

	@Produces
	private List<CardapioModel> cardapios;

	@Inject
	transient private CardapioRepository cardapioRepository;

	public List<CardapioModel> getCardapios() {
		return cardapios;
	}

	public void setCardapio(List<CardapioModel> cardapios) {
		this.cardapios = cardapios;
	}

	public CardapioModel getCardapioModel() {
		return cardapioModel;
	}

	public void setCardapioModel(CardapioModel cardapioModel) {
		this.cardapioModel = cardapioModel;
	}

	/***
	 * CARREGA INFORMAÇÕES DE UM CARDAPIO PARA SER EDITADO
	 * 
	 * @param cardapioModel
	 */
	public void Editar(CardapioModel cardapioModel) {
		this.consultarItemCardapioController.init(cardapioModel.getCodigo());

		this.cardapioModel = cardapioModel;
		this.cardapioRepository.Editar(this.cardapioModel);
		System.out.println("codigo do cardapio Controller: "
				+ cardapioModel.getCodigo());
	}

	/***
	 * ATUALIZA O REGISTRO QUE FOI ALTERADO
	 */
	public void AlterarRegistro() {

		this.cardapioRepository.AlterarRegistro(this.cardapioModel);

		/* RECARREGA OS REGISTROS */
		this.init();

	}

	/***
	 * EXCLUINDO UM REGISTRO
	 * 
	 * @param cardapioModel
	 */
	public void ExcluirCardapio(CardapioModel cardapioModel) {

		// EXCLUI UM PRODUTO DO BANCO DE DADOS
		this.cardapioRepository.ExcluirRegistro(cardapioModel.getCodigo());

		// REMOVENDO O CARDAPIO DA LISTA
		// ASSIM QUE É O PRODUTO É REMOVIDA DA LISTA O DATATABLE É ATUALIZADO
		this.cardapios.remove(cardapioModel);

	}

	/***
	 * CARREGA OS CARDAPIOS NA INICIALIZAÇÃO
	 */
	@PostConstruct
	public void init() {

		// RETORNAR OS CARDAPIOS CADASTRADOS
		this.cardapios = cardapioRepository.GetCardapios();
	}

}
