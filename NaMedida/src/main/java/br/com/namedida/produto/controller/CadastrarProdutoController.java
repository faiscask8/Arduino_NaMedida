package br.com.namedida.produto.controller;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.namedida.model.ProdutoModel;
import br.com.namedida.repository.ProdutoRepository;
import br.com.namedida.usuario.controller.UsuarioController;
import br.com.namedida.uteis.Uteis;

@Named(value = "cadastrarProdutoController")
@RequestScoped
public class CadastrarProdutoController {

	@Inject
	ProdutoModel produtoModel;

	@Inject
	UsuarioController usuarioController;

	@Inject
	ProdutoRepository produtoRepository;

	public ProdutoModel getProdutoModel() {
		return produtoModel;
	}

	public void setProdutoModel(ProdutoModel produtoModel) {
		this.produtoModel = produtoModel;
	}

	/**
	 * SALVA UM NOVO REGISTRO
	 */
	public void SalvarNovoProduto() {

		produtoModel
				.setUsuarioModel(this.usuarioController.GetUsuarioSession());

		produtoRepository.SalvarNovoRegistro(this.produtoModel);

		this.produtoModel = null;

		Uteis.MensagemInfo("Produto cadastrado com sucesso");

	}

}