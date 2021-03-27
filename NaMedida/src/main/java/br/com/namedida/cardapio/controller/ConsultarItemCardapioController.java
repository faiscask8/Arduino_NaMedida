package br.com.namedida.cardapio.controller;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Produces;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.namedida.model.ItemCardapioModel;
import br.com.namedida.repository.DiaRepository;
import br.com.namedida.repository.ItemCardapioRepository;
import br.com.namedida.repository.entity.CardapioEntity;

@Named(value = "consultaritemCardapioController")
@ViewScoped
public class ConsultarItemCardapioController implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	transient private ItemCardapioModel itemCardapioModel;

	@Inject
	transient CardapioEntity cardapioEntity;

	@Produces
	private List<ItemCardapioModel> itemCardapios;

	@Produces
	public static List<ItemCardapioModel> itemCardapiosTotal;

	@Inject
	transient private ItemCardapioRepository itemCardapioRepository;

	public List<ItemCardapioModel> getItemCardapios() {
		return itemCardapios;
	}

	public void setItemCardapio(List<ItemCardapioModel> itemcardapios) {
		this.itemCardapios = itemcardapios;
	}

	public List<ItemCardapioModel> getItemCardapiosTotal() {
		return itemCardapiosTotal;
	}

	public void setItemCardapioTotal(List<ItemCardapioModel> itemcardapiosTotal) {
		this.itemCardapios = itemcardapiosTotal;
	}

	public ItemCardapioModel getItemCardapioModel() {
		return itemCardapioModel;
	}

	public void setItemCardapioModel(ItemCardapioModel itemCardapioModel) {
		this.itemCardapioModel = itemCardapioModel;
	}

	/***
	 * CARREGA INFORMAÇÕES DE UM ITEM PARA SER EDITADO
	 * 
	 * @param itemCardapioModel
	 */
	public void Editar(ItemCardapioModel itemCardapioModel) {

		this.itemCardapioModel = itemCardapioModel;

	}

	public void itemsRefeicao() {

		Integer id_cardapio = DiaRepository.CardapioGlobal;
		System.out.println("Cardapio Global 2: " + id_cardapio);
		this.itemCardapiosTotal = itemCardapioRepository
				.GetItemCardapiosTotal(id_cardapio);
		System.out.println("item total: "
				+ itemCardapioModel.getId_cardapiodia());

	}

	/***
	 * ATUALIZA O REGISTRO QUE FOI ALTERADO
	 */
	public void AlterarRegistro() {

		this.itemCardapioRepository.AlterarRegistro(this.itemCardapioModel);

		/* RECARREGA OS REGISTROS */
		// arrumar this.init();
	}

	/***
	 * EXCLUINDO UM REGISTRO
	 * 
	 * @param itemCardapioModel
	 */
	public void ExcluirItemCardapio(ItemCardapioModel itemCardapioModel) {

		// EXCLUI UM PRODUTO DO BANCO DE DADOS
		System.out.println("item excluido" + itemCardapioModel.getId_itens());
		this.itemCardapioRepository.ExcluirRegistro(itemCardapioModel
				.getId_itens());

		// REMOVENDO O ITEM CARDAPIO DA LISTA
		// ASSIM QUE É O PRODUTO É REMOVIDA DA LISTA O DATATABLE É ATUALIZADO
		this.itemCardapios.remove(itemCardapioModel);
		this.init(null);

	}

	/***
	 * CARREGA OS CARDAPIOS NA INICIALIZAÇÃO
	 */
	@PostConstruct
	public void init(Integer codigo1) {

		System.out.println("ENTREI AQUI MANO ");
		// RETORNAR OS PRODUTOS CADASTRADOS
		this.itemCardapios = itemCardapioRepository.GetItemCardapios(codigo1);
		this.itemCardapiosTotal = itemCardapioRepository
				.GetItemCardapiosTotal(codigo1);

	}

}
