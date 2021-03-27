package br.com.namedida.dia.controller;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.namedida.model.DiaModel;
import br.com.namedida.repository.DiaRepository;
import br.com.namedida.usuario.controller.UsuarioController;
import br.com.namedida.uteis.Uteis;

@Named(value = "cadastrarDiaController")
@RequestScoped
public class CadastrarDiaController {

	@Inject
	DiaModel diaModel;

	@Inject
	UsuarioController usuarioController;

	@Inject
	DiaRepository diaRepository;

	public DiaModel getDiaModel() {
		return diaModel;
	}

	public void setDiaModel(DiaModel diaModel) {
		this.diaModel = diaModel;
	}

	/**
	 * SALVA UM NOVO REGISTRO
	 */
	public void SalvarNovoDia() {

		diaModel.setUsuarioModel(this.usuarioController.GetUsuarioSession());

		diaRepository.SalvarNovoRegistro(this.diaModel);

		this.diaModel = null;

		Uteis.MensagemInfo("Dia cadastrado com sucesso");

	}

}