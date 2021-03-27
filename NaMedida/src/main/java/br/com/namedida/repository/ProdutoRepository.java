package br.com.namedida.repository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.com.namedida.model.ProdutoModel;
import br.com.namedida.model.UsuarioModel;
import br.com.namedida.repository.entity.ProdutoEntity;
import br.com.namedida.repository.entity.UsuarioEntity;
import br.com.namedida.uteis.Uteis;

public class ProdutoRepository {

	@Inject
	ProdutoEntity produtoEntity;

	EntityManager entityManager;

	/***
	 * MÉTODO RESPONSÁVEL POR SALVAR UM NOVO PRODUTO
	 * 
	 * @param produtoModel
	 */
	public void SalvarNovoRegistro(ProdutoModel produtoModel) {

		entityManager = Uteis.JpaEntityManager();
		produtoEntity = new ProdutoEntity();
		produtoEntity.setDescricao(produtoModel.getDescricao());

		UsuarioEntity usuarioEntity = entityManager.find(UsuarioEntity.class,
				produtoModel.getUsuarioModel().getCodigo());

		produtoEntity.setUsuarioEntity(usuarioEntity);

		entityManager.persist(produtoEntity);

	}

	/***
	 * MÉTODO PARA CONSULTAR UM PRODUTO
	 * 
	 * @return
	 */
	public List<ProdutoModel> GetProdutos() {

		List<ProdutoModel> produtosModel = new ArrayList<ProdutoModel>();

		entityManager = Uteis.JpaEntityManager();

		Query query = entityManager.createNamedQuery("ProdutoEntity.findAll");

		@SuppressWarnings("unchecked")
		Collection<ProdutoEntity> produtosEntity = (Collection<ProdutoEntity>) query
				.getResultList();

		ProdutoModel produtoModel = null;

		for (ProdutoEntity produtoEntity : produtosEntity) {

			produtoModel = new ProdutoModel();
			produtoModel.setCodigo(produtoEntity.getCodigo());
			produtoModel.setDescricao(produtoEntity.getDescricao());

			UsuarioEntity usuarioEntity = produtoEntity.getUsuarioEntity();

			UsuarioModel usuarioModel = new UsuarioModel();
			usuarioModel.setUsuario(usuarioEntity.getUsuario());

			produtoModel.setUsuarioModel(usuarioModel);

			produtosModel.add(produtoModel);
		}

		return produtosModel;

	}

	/***
	 * CONSULTA UM PRODUTO CADASTRADO PELO CÓDIGO
	 * 
	 * @param codigo
	 * @return
	 */
	private ProdutoEntity GetProduto(int codigo) {

		entityManager = Uteis.JpaEntityManager();

		return entityManager.find(ProdutoEntity.class, codigo);
	}

	/***
	 * CONSULTA UM PRODUTO CADASTRADO PELO CÓDIGO
	 * 
	 * @param l
	 * @return
	 */
	public ProdutoEntity GetProdutoConverter(long l) {

		entityManager = Uteis.JpaEntityManager();

		return entityManager.find(ProdutoEntity.class, l);
	}

	/***
	 * ALTERA UM REGISTRO CADASTRADO NO BANCO DE DADOS
	 * 
	 * @param produtoModel
	 */
	public void AlterarRegistro(ProdutoModel produtoModel) {

		entityManager = Uteis.JpaEntityManager();

		ProdutoEntity produtoEntity = this.GetProduto(produtoModel.getCodigo());

		produtoEntity.setDescricao(produtoModel.getDescricao());

		entityManager.merge(produtoEntity);
	}

	/***
	 * EXCLUI UM REGISTRO DO BANCO DE DADOS
	 * 
	 * @param codigo
	 */
	public void ExcluirRegistro(int codigo) {

		entityManager = Uteis.JpaEntityManager();

		ProdutoEntity produtoEntity = this.GetProduto(codigo);

		entityManager.remove(produtoEntity);
	}

}