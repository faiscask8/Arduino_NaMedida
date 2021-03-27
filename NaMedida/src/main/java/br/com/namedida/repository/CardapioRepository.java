package br.com.namedida.repository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.com.namedida.model.CardapioModel;
import br.com.namedida.model.UsuarioModel;
import br.com.namedida.repository.entity.CardapioEntity;
import br.com.namedida.repository.entity.UsuarioEntity;
import br.com.namedida.uteis.Uteis;

public class CardapioRepository {

	private static Integer id = null;

	public static Integer getId() {
		return id;
	}

	public static void setId(Integer id) {
		CardapioRepository.id = id;
	}

	private static int CardapioGlobal;

	@Inject
	CardapioEntity cardapioEntity;

	EntityManager entityManager;

	/***
	 * MÉTODO RESPONSÁVEL POR SALVAR UM NOVO CARDAPIO
	 * 
	 * @param cardapioModel
	 */
	public void SalvarNovoRegistro(CardapioModel cardapioModel) {

		entityManager = Uteis.JpaEntityManager();
		cardapioEntity = new CardapioEntity();
		cardapioEntity.setDescricao(cardapioModel.getDescricao());

		UsuarioEntity usuarioEntity = entityManager.find(UsuarioEntity.class,
				cardapioModel.getUsuarioModel().getCodigo());
		cardapioEntity.setUsuarioEntity(usuarioEntity);

		entityManager.persist(cardapioEntity);

	}

	/***
	 * MÉTODO RESPONSÁVEL POR SALVAR UM NOVO PRODUTO NO CARDAPIO
	 * 
	 * @param cardapioModel
	 */
	public void SalvarNovoItemRegistro(CardapioModel cardapioModel) {

		entityManager = Uteis.JpaEntityManager();
		cardapioEntity = new CardapioEntity();
		cardapioEntity.setDescricao(cardapioModel.getDescricao());

		UsuarioEntity usuarioEntity = entityManager.find(UsuarioEntity.class,
				cardapioModel.getUsuarioModel().getCodigo());

		cardapioEntity.setUsuarioEntity(usuarioEntity);

		entityManager.persist(cardapioEntity);

	}

	/***
	 * MÉTODO PARA CONSULTAR UM CARDAPIO
	 * 
	 * @return
	 */
	public List<CardapioModel> GetCardapios() {

		List<CardapioModel> cardapiosModel = new ArrayList<CardapioModel>();

		entityManager = Uteis.JpaEntityManager();

		Query query = entityManager.createNamedQuery("CardapioEntity.findAll");

		@SuppressWarnings("unchecked")
		Collection<CardapioEntity> cardapiosEntity = (Collection<CardapioEntity>) query
				.getResultList();

		CardapioModel cardapioModel = null;

		for (CardapioEntity cardapioEntity : cardapiosEntity) {

			cardapioModel = new CardapioModel();
			cardapioModel.setCodigo(cardapioEntity.getCodigo());
			cardapioModel.setDescricao(cardapioEntity.getDescricao());

			UsuarioEntity usuarioEntity = cardapioEntity.getUsuarioEntity();

			UsuarioModel usuarioModel = new UsuarioModel();
			usuarioModel.setUsuario(usuarioEntity.getUsuario());

			cardapioModel.setUsuarioModel(usuarioModel);

			cardapiosModel.add(cardapioModel);
		}

		return cardapiosModel;

	}

	/***
	 * CONSULTA UM CARDAPAPIO CADASTRADO PELO CÓDIGO
	 * 
	 * @param codigo
	 * @return
	 */
	private CardapioEntity GetCardapio(int codigo) {

		entityManager = Uteis.JpaEntityManager();

		return entityManager.find(CardapioEntity.class, codigo);
	}

	/***
	 * ALTERA UM REGISTRO CADASTRADO NO BANCO DE DADOS
	 * 
	 * @param cardapioModel
	 */
	public void AlterarRegistro(CardapioModel cardapioModel) {

		entityManager = Uteis.JpaEntityManager();

		CardapioEntity cardapioEntity = this.GetCardapio(cardapioModel
				.getCodigo());

		cardapioEntity.setDescricao(cardapioModel.getDescricao());

		entityManager.merge(cardapioEntity);
	}

	/***
	 * EDITAR UM REGISTRO CADASTRADO NO BANCO DE DADOS
	 * 
	 * @param cardapioModel
	 */
	public void Editar(CardapioModel cardapioModel) {

		entityManager = Uteis.JpaEntityManager();

		CardapioEntity cardapioEntity = this.GetCardapio(cardapioModel
				.getCodigo());

		cardapioEntity.setDescricao(cardapioModel.getDescricao());

		System.out
				.println("Cardapio Respository: " + cardapioModel.getCodigo());
		setCardapioGlobal(cardapioModel.getCodigo());

	}

	/***
	 * EXCLUI UM REGISTRO DO BANCO DE DADOS
	 * 
	 * @param codigo
	 */
	public void ExcluirRegistro(int codigo) {

		entityManager = Uteis.JpaEntityManager();

		CardapioEntity cardapioEntity = this.GetCardapio(codigo);

		entityManager.remove(cardapioEntity);
	}

	public static int getCardapioGlobal() {
		return CardapioGlobal;
	}

	public static void setCardapioGlobal(int cardapioGlobal) {
		CardapioGlobal = cardapioGlobal;
	}

}