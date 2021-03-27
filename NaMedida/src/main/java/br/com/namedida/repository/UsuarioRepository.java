package br.com.namedida.repository;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.com.namedida.model.UsuarioModel;
import br.com.namedida.repository.entity.ProdutoEntity;
import br.com.namedida.repository.entity.UsuarioEntity;
import br.com.namedida.uteis.Uteis;

public class UsuarioRepository implements Serializable {

	@Inject
	UsuarioEntity usuarioEntity;

	EntityManager entityManager;

	private static final long serialVersionUID = 1L;

	public UsuarioEntity ValidaUsuario(UsuarioModel usuarioModel) {

		try {

			// QUERY QUE VAI SER EXECUTADA (UsuarioEntity.findUser)
			Query query = Uteis.JpaEntityManager().createNamedQuery(
					"UsuarioEntity.findUser");

			// PARÂMETROS DA QUERY
			query.setParameter("usuario", usuarioModel.getUsuario());
			query.setParameter("senha", usuarioModel.getSenha());

			// RETORNA O USUÁRIO SE FOR LOCALIZADO
			return (UsuarioEntity) query.getSingleResult();

		} catch (Exception e) {

			return null;
		}

	}

	/***
	 * MÉTODO RESPONSÁVEL POR SALVAR UM NOVO USUÁRIO
	 * 
	 * @param usuarioModel
	 */
	public void SalvarNovoRegistro(UsuarioModel usuarioModel) {

		entityManager = Uteis.JpaEntityManager();
		usuarioEntity = new UsuarioEntity();
		usuarioEntity.setUsuario(usuarioModel.getUsuario());
		usuarioEntity.setSenha(usuarioModel.getSenha());

		entityManager.persist(usuarioEntity);

	}

	/***
	 * MÉTODO PARA CONSULTAR UM USUARIO
	 * 
	 * @return
	 */
	public List<UsuarioModel> GetUsuarios() {

		List<UsuarioModel> usuariosModel = new ArrayList<UsuarioModel>();

		entityManager = Uteis.JpaEntityManager();

		Query query = entityManager.createNamedQuery("UsuarioEntity.findAll");

		@SuppressWarnings("unchecked")
		Collection<UsuarioEntity> usuariosEntity = (Collection<UsuarioEntity>) query
				.getResultList();

		UsuarioModel usuarioModel = null;

		for (UsuarioEntity usuarioEntity : usuariosEntity) {

			usuarioModel = new UsuarioModel();
			usuarioModel.setCodigo(usuarioEntity.getCodigo());
			usuarioModel.setUsuario(usuarioEntity.getUsuario());
			usuarioModel.setSenha(usuarioEntity.getSenha());

			usuariosModel.add(usuarioModel);
		}

		return usuariosModel;

	}

	/***
	 * CONSULTA UM USUARIO CADASTRADO PELO CÓDIGO
	 * 
	 * @param codigo
	 * @return
	 */
	private UsuarioEntity GetUsuario(int codigo) {

		entityManager = Uteis.JpaEntityManager();

		return entityManager.find(UsuarioEntity.class, codigo);
	}

	/***
	 * ALTERA UM REGISTRO CADASTRADO NO BANCO DE DADOS
	 * 
	 * @param usuarioModel
	 */
	public void AlterarRegistro(UsuarioModel usuarioModel) {

		entityManager = Uteis.JpaEntityManager();

		UsuarioEntity usuarioEntity = this.GetUsuario(usuarioModel.getCodigo());

		usuarioEntity.setUsuario(usuarioModel.getUsuario());
		usuarioEntity.setSenha(usuarioModel.getSenha());

		entityManager.merge(usuarioEntity);
	}

	/***
	 * EXCLUI UM REGISTRO DO BANCO DE DADOS
	 * 
	 * @param codigo
	 */
	public void ExcluirRegistro(int codigo) {

		entityManager = Uteis.JpaEntityManager();

		UsuarioEntity usuarioEntity = this.GetUsuario(codigo);

		entityManager.remove(usuarioEntity);
	}

}