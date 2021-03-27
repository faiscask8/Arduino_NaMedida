package br.com.namedida.cardapio.controller;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.namedida.model.CardapioModel;
import br.com.namedida.model.ItemCardapioModel;
import br.com.namedida.repository.ItemCardapioRepository;
import br.com.namedida.uteis.Uteis;

@Named(value = "lancarItemCardapioController")
@RequestScoped
public class LancarItemCardapioController {

	@Inject
	ItemCardapioModel itemCardapioModel;

	@Inject
	CardapioModel cardapioModel;

	@Produces
	private List<ItemCardapioModel> itemCardapios;

	@Inject
	ConsultarCardapioController consultarCardapioController;

	@Inject
	ItemCardapioRepository itemCardapioRepository;

	public ItemCardapioModel getItemCardapioModel() {
		return itemCardapioModel;
	}

	public void setItemCardapioModel(ItemCardapioModel itemCardapioModel) {
		this.itemCardapioModel = itemCardapioModel;
	}

	public CardapioModel getCardapioModel() {
		return cardapioModel;
	}

	public void setCardapioModel(CardapioModel cardapioModel) {
		this.cardapioModel = cardapioModel;
	}

	public List<ItemCardapioModel> getItemCardapios() {
		return itemCardapios;
	}

	public void setItemCardapios(List<ItemCardapioModel> itemCardapios) {
		this.itemCardapios = itemCardapios;
	}

	public ConsultarCardapioController getConsultarCardapioController() {
		return consultarCardapioController;
	}

	public void setConsultarCardapioController(
			ConsultarCardapioController consultarCardapioController) {
		this.consultarCardapioController = consultarCardapioController;
	}

	public ItemCardapioRepository getItemCardapioRepository() {
		return itemCardapioRepository;
	}

	public void setItemCardapioRepository(
			ItemCardapioRepository itemCardapioRepository) {
		this.itemCardapioRepository = itemCardapioRepository;
	}

	/**
	 * SALVA UM NOVO ITEM NO CARDAPIO
	 */
	public void SalvarNovoItemCardapio() {

		itemCardapioRepository.SalvarNovoItemRegistro(this.itemCardapioModel);

		this.itemCardapioModel = null;

		Uteis.MensagemInfo("Produto Inserido no Cardápio com Sucesso");

	}

	/***
	 * EXCLUINDO UM REGISTRO
	 * 
	 * @param itemCardapioModel
	 */
	public void ExcluirItemCardapio(ItemCardapioModel itemCardapioModel) {

		// EXCLUI UM PRODUTO DO BANCO DE DADOS
		this.itemCardapioRepository.ExcluirRegistro(itemCardapioModel
				.getId_itens());

		// REMOVENDO O CARDAPIO DA LISTA
		// ASSIM QUE É O PRODUTO É REMOVIDA DA LISTA O DATATABLE É ATUALIZADO
		this.itemCardapios.remove(itemCardapioModel);

	}

}
