package br.com.namedida.refeicoes.controller;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.namedida.model.ItemRefeicoesModel;
import br.com.namedida.model.RefeicoesModel;
import br.com.namedida.repository.ItemRefeicoesRepository;
import br.com.namedida.repository.RefeicoesRepository;
import br.com.namedida.usuario.controller.UsuarioController;
import br.com.namedida.uteis.Uteis;

@Named(value = "lancarRefeicoesController")
@RequestScoped
public class LancarRefeicoesController {

	@Inject
	RefeicoesModel refeicoesModel;

	@Inject
	ItemRefeicoesRepository itemRefeicoesRepository;

	@Inject
	ItemRefeicoesModel itemRefeicoesModel;

	@Inject
	RefeicoesRepository refeicoesRepository;

	@Inject
	UsuarioController usuarioController;

	public void Imprimir() {

		itemRefeicoesRepository.ImprimirItensRefeicoes();

		Uteis.MensagemInfo("Refeição do Dia Impressa com Sucesso!");

	}

	public void SalvarNovaRefeicao() {

		refeicoesModel.setUsuarioModel(this.usuarioController
				.GetUsuarioSession());

		refeicoesRepository.SalvarNovaRefeicao(this.refeicoesModel);

		itemRefeicoesRepository
				.SalvarNovoItemRefeicoes(this.itemRefeicoesModel);

		this.refeicoesModel = null;
		this.itemRefeicoesModel = null;

		Uteis.MensagemInfo("Refeição Gerada com Sucesso");

	}

}
