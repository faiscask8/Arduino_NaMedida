package br.com.namedida.aluno.controller;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Produces;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.namedida.model.AlunoModel;
import br.com.namedida.repository.AlunoRepository;

@Named(value = "consultarAlunoController")
@ViewScoped
public class ConsultarAlunoController implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	transient private AlunoModel alunoModel;

	@Produces
	private List<AlunoModel> alunos;

	@Inject
	transient private AlunoRepository alunoRepository;

	public List<AlunoModel> getAlunos() {
		return alunos;
	}

	public void setAlunos(List<AlunoModel> alunos) {
		this.alunos = alunos;
	}

	public AlunoModel getAlunoModel() {
		return alunoModel;
	}

	public void setAlunoModel(AlunoModel alunoModel) {
		this.alunoModel = alunoModel;
	}

	/***
	 * CARREGA INFORMAÇÕES DE UM ALUNO PARA SER EDITADO
	 * 
	 * @param alunoModel
	 */
	public void Editar(AlunoModel alunoModel) {

		this.alunoModel = alunoModel;

	}

	/***
	 * ATUALIZA O REGISTRO QUE FOI ALTERADO
	 */
	public void AlterarRegistro() {

		this.alunoRepository.AlterarRegistro(this.alunoModel);

		/* RECARREGA OS REGISTROS */
		this.init();
	}

	/***
	 * EXCLUINDO UM REGISTRO
	 * 
	 * @param alunoModel
	 */
	public void ExcluirAluno(AlunoModel alunoModel) {

		// EXCLUI UM ALUNO DO BANCO DE DADOS
		this.alunoRepository.ExcluirRegistro(alunoModel.getCodigo());

		// REMOVENDO O ALUNO DA LISTA
		// ASSIM QUE É O ALUNO É REMOVIDA DA LISTA O DATATABLE É ATUALIZADO
		this.alunos.remove(alunoModel);

	}

	/***
	 * CARREGA OS ALUNOS NA INICIALIZAÇÃO
	 */
	@PostConstruct
	public void init() {

		// RETORNAR OS ALUNOS CADASTRADOS
		this.alunos = alunoRepository.GetAlunos();
	}

}
