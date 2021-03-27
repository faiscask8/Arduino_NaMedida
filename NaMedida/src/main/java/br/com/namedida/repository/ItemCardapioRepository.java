package br.com.namedida.repository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.com.namedida.medida.controller.ConsultarMedidaController;
import br.com.namedida.model.CardapioModel;
import br.com.namedida.model.ItemCardapioModel;
import br.com.namedida.model.MedidaModel;
import br.com.namedida.model.ProdutoModel;
import br.com.namedida.repository.entity.CardapioEntity;
import br.com.namedida.repository.entity.ItemCardapioEntity;
import br.com.namedida.repository.entity.ProdutoEntity;
import br.com.namedida.uteis.Uteis;

public class ItemCardapioRepository {

	@Inject
	ItemCardapioEntity itemCardapioEntity;

	@Inject
	ItemCardapioModel itemCardapioModel;

	@Inject
	CardapioRepository cardapioRepository;

	@Inject
	CardapioModel cardapioModel;

	EntityManager entityManager;

	/***
	 * MÉTODO RESPONSÁVEL POR SALVAR UM NOVO PRODUTO NO CARDAPIO
	 * 
	 * @param itemCardapioModel
	 */
	public void SalvarNovoItemRegistro(ItemCardapioModel itemCardapioModel) {

		entityManager = Uteis.JpaEntityManager();
		itemCardapioEntity = new ItemCardapioEntity();

		itemCardapioEntity.setQt_quant(itemCardapioModel.getQt_quant());

		ProdutoEntity produtoEntity = entityManager.find(ProdutoEntity.class,
				itemCardapioModel.getProdutoModel().getCodigo());
		System.out.println("Valor Produto Global: "
				+ itemCardapioModel.getProdutoModel().getCodigo());
		itemCardapioEntity.setProdutoEntity(produtoEntity);

		CardapioEntity cardapioEntity = entityManager.find(
				CardapioEntity.class, cardapioRepository.getCardapioGlobal());
		System.out.println("Código Cardapio Global :"
				+ cardapioRepository.getCardapioGlobal());

		itemCardapioEntity.setCardapioEntity(cardapioEntity);

		entityManager.persist(itemCardapioEntity);

	}

	/***
	 * MÉTODO PARA CONSULTAR UM ITEM
	 * 
	 * @return
	 */
	public List<ItemCardapioModel> GetItemCardapios(Integer CardapioAtual) {

		List<ItemCardapioModel> itemCardapiosModel = new ArrayList<ItemCardapioModel>();

		entityManager = Uteis.JpaEntityManager();

		Query query = entityManager
				.createNamedQuery("ItemCardapioEntity.findItemCardapio");
		query.setParameter("idcardapio", CardapioAtual);

		@SuppressWarnings("unchecked")
		Collection<ItemCardapioEntity> ItemCardapiosEntity = (Collection<ItemCardapioEntity>) query
				.getResultList();

		ItemCardapioModel itemCardapioModel = null;

		for (ItemCardapioEntity itemCardapioEntity : ItemCardapiosEntity) {

			itemCardapioModel = new ItemCardapioModel();
			itemCardapioModel.setQt_quant(itemCardapioEntity.getQt_quant());

			ProdutoEntity produtoEntity = itemCardapioEntity.getProdutoEntity();

			ProdutoModel produtoModel = new ProdutoModel();
			produtoModel.setDescricao(produtoEntity.getDescricao());
			itemCardapioModel.setProdutoModel(produtoModel);

			itemCardapiosModel.add(itemCardapioModel);
		}

		return itemCardapiosModel;

	}

	/***
	 * MÉTODO PARA CONSULTAR UM ITEM SOMANDO OS VALORES
	 * 
	 * @return
	 */
	public List<ItemCardapioModel> GetItemCardapiosTotal(Integer CardapioAtual) {

		System.out.println("CardapioATual + Total: " + CardapioAtual);
		List<ItemCardapioModel> itemCardapiosModel = new ArrayList<ItemCardapioModel>();

		entityManager = Uteis.JpaEntityManager();

		Query query = entityManager
				.createNamedQuery("ItemCardapioEntity.findItemCardapio");
		query.setParameter("idcardapio", CardapioAtual);

		System.out.println("CardapioEntity: " + CardapioAtual);

		@SuppressWarnings("unchecked")
		Collection<ItemCardapioEntity> ItemCardapiosEntity = (Collection<ItemCardapioEntity>) query
				.getResultList();

		ItemCardapioModel itemCardapioModel = null;

		for (ItemCardapioEntity itemCardapioEntity : ItemCardapiosEntity) {
			itemCardapioModel = new ItemCardapioModel();
			itemCardapioModel.setQt_quant(itemCardapioEntity.getQt_quant()
					* ConsultarMedidaController.TotalAgora); // alterar para a
																// varivel de
																// quantidade

			ProdutoEntity produtoEntity = itemCardapioEntity.getProdutoEntity();
			ProdutoModel produtoModel = new ProdutoModel();
			produtoModel.setCodigo(produtoEntity.getCodigo());
			produtoModel.setDescricao(produtoEntity.getDescricao());
			itemCardapioModel.setProdutoModel(produtoModel);

			itemCardapiosModel.add(itemCardapioModel);
		}

		return itemCardapiosModel;

	}

	public void AlterarRegistro(ItemCardapioModel itemCardapioModel) {

	}

	public void ExcluirRegistro(Integer id_itens) {
		// TODO Auto-generated method stub

	}

}