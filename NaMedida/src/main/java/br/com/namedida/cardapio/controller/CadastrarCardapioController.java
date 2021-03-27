package br.com.namedida.cardapio.controller;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.namedida.model.CardapioModel;
import br.com.namedida.repository.CardapioRepository;
import br.com.namedida.usuario.controller.UsuarioController;
import br.com.namedida.uteis.Uteis;

@Named(value = "cadastrarCardapioController")
@RequestScoped
public class CadastrarCardapioController {

	@Inject
	CardapioModel cardapioModel;

	@Inject
	UsuarioController usuarioController;

	@Inject
	CardapioRepository cardapioRepository;

	public CardapioModel getCardapioModel() {
		return cardapioModel;
	}

	public void setCardapioModel(CardapioModel cardapioModel) {
		this.cardapioModel = cardapioModel;
	}

	/**
	 * SALVA UM NOVO REGISTRO
	 */
	public void SalvarNovoCardapio() {

		cardapioModel.setUsuarioModel(this.usuarioController
				.GetUsuarioSession());
		System.out.println("Usuario logado: "
				+ this.usuarioController.GetUsuarioSession());

		cardapioRepository.SalvarNovoRegistro(this.cardapioModel);

		this.cardapioModel = null;

		Uteis.MensagemInfo("Cardápio cadastrado com sucesso");

	}

}