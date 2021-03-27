package br.com.namedida.aluno.controller;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.namedida.model.AlunoModel;
import br.com.namedida.repository.AlunoRepository;
import br.com.namedida.usuario.controller.UsuarioController;
import br.com.namedida.uteis.Uteis;

@Named(value = "cadastrarAlunoController")
@RequestScoped
public class CadastrarAlunoController {

	@Inject
	AlunoModel alunoModel;

	@Inject
	UsuarioController usuarioController;

	@Inject
	AlunoRepository alunoRepository;

	public AlunoModel getAlunoModel() {
		return alunoModel;
	}

	public void setAlunoModel(AlunoModel alunoModel) {
		this.alunoModel = alunoModel;
	}

	/**
	 * SALVA UM NOVO REGISTRO
	 */
	public void SalvarNovoAluno() {

		alunoModel.setUsuarioModel(this.usuarioController.GetUsuarioSession());

		alunoRepository.SalvarNovoRegistro(this.alunoModel);

		this.alunoModel = null;

		Uteis.MensagemInfo("Aluno cadastrado com sucesso");

	}

}