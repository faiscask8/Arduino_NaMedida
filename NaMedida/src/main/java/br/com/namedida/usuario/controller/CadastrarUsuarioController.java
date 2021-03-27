package br.com.namedida.usuario.controller;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.namedida.model.UsuarioModel;
import br.com.namedida.repository.UsuarioRepository;
import br.com.namedida.uteis.Uteis;

@Named(value = "cadastrarUsuarioController")
@RequestScoped
public class CadastrarUsuarioController {

	@Inject
	UsuarioModel usuarioModel;

	@Inject
	UsuarioRepository usuarioRepository;

	public UsuarioModel getUsuarioModel() {
		return usuarioModel;
	}

	public void setUsuarioModel(UsuarioModel usuarioModel) {
		this.usuarioModel = usuarioModel;
	}

	/**
	 * SALVA UM NOVO REGISTRO
	 */
	public void SalvarNovoUsuario() {

		usuarioRepository.SalvarNovoRegistro(this.usuarioModel);

		this.usuarioModel = null;

		Uteis.MensagemInfo("Usuário cadastrado com sucesso");

	}

}