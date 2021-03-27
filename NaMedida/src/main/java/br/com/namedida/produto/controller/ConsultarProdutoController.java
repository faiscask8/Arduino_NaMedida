package br.com.namedida.produto.controller;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Produces;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.namedida.model.ProdutoModel;
import br.com.namedida.repository.ProdutoRepository;

@Named(value = "consultarProdutoController")
@ViewScoped
public class ConsultarProdutoController implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	transient private ProdutoModel produtoModel;

	@Produces
	private List<ProdutoModel> produtos;

	@Inject
	transient private ProdutoRepository produtoRepository;

	public List<ProdutoModel> getProdutos() {
		return produtos;
	}

	public void setProdutos(List<ProdutoModel> produtos) {
		this.produtos = produtos;
	}

	public ProdutoModel getProdutoModel() {
		return produtoModel;
	}

	public void setProdutoModel(ProdutoModel produtoModel) {
		this.produtoModel = produtoModel;
	}

	/***
	 * CARREGA INFORMAÇÕES DE UM PRODUTO PARA SER EDITADO
	 * 
	 * @param produtoModel
	 */
	public void Editar(ProdutoModel produtoModel) {

		this.produtoModel = produtoModel;

	}

	/***
	 * ATUALIZA O REGISTRO QUE FOI ALTERADO
	 */
	public void AlterarRegistro() {

		this.produtoRepository.AlterarRegistro(this.produtoModel);

		/* RECARREGA OS REGISTROS */
		this.init();
	}

	/***
	 * EXCLUINDO UM REGISTRO
	 * 
	 * @param produtoModel
	 */
	public void ExcluirProduto(ProdutoModel produtoModel) {

		// EXCLUI UM PRODUTO DO BANCO DE DADOS
		this.produtoRepository.ExcluirRegistro(produtoModel.getCodigo());

		// REMOVENDO O PRODUTO DA LISTA
		// ASSIM QUE É O PRODUTO É REMOVIDA DA LISTA O DATATABLE É ATUALIZADO
		this.produtos.remove(produtoModel);

	}

	/***
	 * CARREGA OS PRODUTOS NA INICIALIZAÇÃO
	 */
	@PostConstruct
	public void init() {

		// RETORNAR OS PRODUTOS CADASTRADOS
		this.produtos = produtoRepository.GetProdutos();
	}

}
