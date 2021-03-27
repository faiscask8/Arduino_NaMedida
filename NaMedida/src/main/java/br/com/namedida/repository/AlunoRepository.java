package br.com.namedida.repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.com.namedida.model.AlunoModel;
import br.com.namedida.model.UsuarioModel;
import br.com.namedida.repository.entity.AlunoEntity;
import br.com.namedida.repository.entity.UsuarioEntity;
import br.com.namedida.uteis.Uteis;

public class AlunoRepository {

	@Inject
	AlunoEntity alunoEntity;

	EntityManager entityManager;

	/***
	 * MÉTODO RESPONSÁVEL POR SALVAR UM NOVO ALUNO
	 * 
	 * @param alunoModel
	 */
	public void SalvarNovoRegistro(AlunoModel alunoModel) {

		entityManager = Uteis.JpaEntityManager();
		alunoEntity = new AlunoEntity();
		alunoEntity.setEmail(alunoModel.getEmail());
		alunoEntity.setCartao(alunoModel.getCartao());
		alunoEntity.setNome(alunoModel.getNome());
		alunoEntity.setDataCadastro(LocalDateTime.now());

		System.out.println(alunoEntity.getDataCadastro());

		UsuarioEntity usuarioEntity = entityManager.find(UsuarioEntity.class,
				alunoModel.getUsuarioModel().getCodigo());

		alunoEntity.setUsuarioEntity(usuarioEntity);

		entityManager.persist(alunoEntity);

	}

	/***
	 * MÉTODO PARA CONSULTAR A PESSOA
	 * 
	 * @return
	 */
	public List<AlunoModel> GetAlunos() {

		List<AlunoModel> alunosModel = new ArrayList<AlunoModel>();

		entityManager = Uteis.JpaEntityManager();

		Query query = entityManager.createNamedQuery("AlunoEntity.findAll");

		@SuppressWarnings("unchecked")
		Collection<AlunoEntity> alunosEntity = (Collection<AlunoEntity>) query
				.getResultList();

		AlunoModel alunoModel = null;

		for (AlunoEntity alunoEntity : alunosEntity) {

			alunoModel = new AlunoModel();
			alunoModel.setCodigo(alunoEntity.getCodigo());
			alunoModel.setDataCadastro(alunoEntity.getDataCadastro());
			alunoModel.setEmail(alunoEntity.getEmail());
			alunoModel.setNome(alunoEntity.getNome());
			alunoModel.setCartao(alunoEntity.getCartao());

			UsuarioEntity usuarioEntity = alunoEntity.getUsuarioEntity();

			UsuarioModel usuarioModel = new UsuarioModel();
			usuarioModel.setUsuario(usuarioEntity.getUsuario());

			alunoModel.setUsuarioModel(usuarioModel);

			alunosModel.add(alunoModel);
		}

		return alunosModel;

	}

	/***
	 * CONSULTA UM ALUNO CADASTRADO PELO CÓDIGO
	 * 
	 * @param codigo
	 * @return
	 */
	private AlunoEntity GetAluno(int codigo) {

		entityManager = Uteis.JpaEntityManager();

		return entityManager.find(AlunoEntity.class, codigo);
	}

	/***
	 * ALTERA UM REGISTRO CADASTRADO NO BANCO DE DADOS
	 * 
	 * @param pessoaModel
	 */
	public void AlterarRegistro(AlunoModel alunoModel) {

		entityManager = Uteis.JpaEntityManager();

		AlunoEntity alunoEntity = this.GetAluno(alunoModel.getCodigo());

		alunoEntity.setEmail(alunoModel.getEmail());
		alunoEntity.setNome(alunoModel.getNome());
		alunoEntity.setCartao(alunoModel.getCartao());

		entityManager.merge(alunoEntity);
	}

	/***
	 * EXCLUI UM REGISTRO DO BANCO DE DADOS
	 * 
	 * @param codigo
	 */
	public void ExcluirRegistro(int codigo) {

		entityManager = Uteis.JpaEntityManager();

		AlunoEntity alunoEntity = this.GetAluno(codigo);

		entityManager.remove(alunoEntity);
	}

}