package br.com.namedida.repository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.com.namedida.model.CardapioModel;
import br.com.namedida.model.DiaModel;
import br.com.namedida.model.ItemCardapioModel;
import br.com.namedida.model.MedidaModel;
import br.com.namedida.model.RefeicoesModel;
import br.com.namedida.model.UsuarioModel;
import br.com.namedida.repository.entity.CardapioEntity;
import br.com.namedida.repository.entity.DiaEntity;
import br.com.namedida.repository.entity.UsuarioEntity;
import br.com.namedida.uteis.Uteis;

public class DiaRepository {

	public static int CardapioGlobal = 0;

	@Inject
	DiaEntity diaEntity;

	@Inject
	ItemCardapioModel itemCardapioModel;

	String cardapiododia = null;

	public String getCardapiododia() {
		return cardapiododia;
	}

	public void setCardapiododia(String cardapiododia) {
		this.cardapiododia = cardapiododia;
	}

	@Inject
	MedidaModel medidaModel;

	EntityManager entityManager;

	// ConsultarDiaController consultarDiaController;

	/***
	 * MÉTODO RESPONSÁVEL POR SALVAR UM NOVO DIA
	 * 
	 * @param diaModel
	 */
	public void SalvarNovoRegistro(DiaModel diaModel) {

		entityManager = Uteis.JpaEntityManager();
		diaEntity = new DiaEntity();
		diaEntity.setDescricao(diaModel.getDescricao());

		CardapioEntity cardapioEntity = entityManager.find(
				CardapioEntity.class, diaModel.getCardapioModel().getCodigo());

		UsuarioEntity usuarioEntity = entityManager.find(UsuarioEntity.class,
				diaModel.getUsuarioModel().getCodigo());

		diaEntity.setCardapioEntity(cardapioEntity);
		diaEntity.setUsuarioEntity(usuarioEntity);

		entityManager.persist(diaEntity);

	}

	/***
	 * MÉTODO PARA CONSULTAR O CARDAPIO DO DIA
	 * 
	 * @return
	 */
	public List<DiaModel> GetCardapioDia(String diacardapio) {

		List<DiaModel> diasModel = new ArrayList<DiaModel>();

		entityManager = Uteis.JpaEntityManager();
		Query query = entityManager.createNamedQuery("DiaEntity.findCardapio");
		query.setParameter("diasemana", diacardapio);
		@SuppressWarnings("unchecked")
		Collection<DiaEntity> diasEntity = (Collection<DiaEntity>) query
				.getResultList();

		DiaModel diaModel = null;

		for (DiaEntity diaEntity : diasEntity) {

			diaModel = new DiaModel();
			diaModel.setCodigo(diaEntity.getCodigo());
			diaModel.setDescricao(diaEntity.getDescricao());

			CardapioEntity cardapioEntity = diaEntity.getCardapioEntity();

			CardapioModel cardapioModel = new CardapioModel();
			cardapioModel.setDescricao(cardapioEntity.getDescricao());
			diaModel.setCardapioModel(cardapioModel);

			diasModel.add(diaModel);

			diaModel.setCardapiohoje(cardapioModel.getDescricao());

			cardapiododia = cardapioModel.getDescricao();

			CardapioGlobal = cardapioEntity.getCodigo();

			System.out.println("Cardapio Global 01: " + CardapioGlobal);

		}

		return diasModel;

	}

	/***
	 * MÉTODO PARA CONSULTAR UM DIA
	 * 
	 * @return
	 */
	public List<DiaModel> GetDias() {

		List<DiaModel> diasModel = new ArrayList<DiaModel>();

		entityManager = Uteis.JpaEntityManager();

		Query query = entityManager.createNamedQuery("DiaEntity.findAll");

		@SuppressWarnings("unchecked")
		Collection<DiaEntity> diasEntity = (Collection<DiaEntity>) query
				.getResultList();

		DiaModel diaModel = null;

		for (DiaEntity diaEntity : diasEntity) {

			diaModel = new DiaModel();
			diaModel.setCodigo(diaEntity.getCodigo());
			diaModel.setDescricao(diaEntity.getDescricao());

			CardapioEntity cardapioEntity = diaEntity.getCardapioEntity();
			CardapioModel cardapioModel = new CardapioModel();
			cardapioModel.setDescricao(cardapioEntity.getDescricao());
			diaModel.setCardapioModel(cardapioModel);

			UsuarioEntity usuarioEntity = diaEntity.getUsuarioEntity();
			UsuarioModel usuarioModel = new UsuarioModel();
			usuarioModel.setUsuario(usuarioEntity.getUsuario());
			diaModel.setUsuarioModel(usuarioModel);

			diasModel.add(diaModel);

		}

		return diasModel;

	}

	/***
	 * CONSULTA UM DIA CADASTRADO PELO CÓDIGO
	 * 
	 * @param codigo
	 * @return
	 */
	private DiaEntity GetDia(int codigo) {

		entityManager = Uteis.JpaEntityManager();

		return entityManager.find(DiaEntity.class, codigo);
	}

	/***
	 * ALTERA UM REGISTRO CADASTRADO NO BANCO DE DADOS
	 * 
	 * @param diaModel
	 */
	public void AlterarRegistro(DiaModel diaModel) {

		entityManager = Uteis.JpaEntityManager();

		DiaEntity diaEntity = this.GetDia(diaModel.getCodigo());

		diaEntity.setDescricao(diaModel.getDescricao());

		entityManager.merge(diaEntity);
	}

	/***
	 * EXCLUI UM REGISTRO DO BANCO DE DADOS
	 * 
	 * @param codigo
	 */
	public void ExcluirRegistro(int codigo) {

		entityManager = Uteis.JpaEntityManager();

		DiaEntity diaEntity = this.GetDia(codigo);

		entityManager.remove(diaEntity);
	}

}
