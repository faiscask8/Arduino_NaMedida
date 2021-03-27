package br.com.namedida.model;

public class ItemRefeicoesModel {

	private Integer id_itens;
	private Float qt_quant;
	private ProdutoModel produtoModel;
	private RefeicoesModel refeicoesModel;

	public Integer getId_itens() {
		return id_itens;
	}

	public void setId_itens(Integer id_itens) {
		this.id_itens = id_itens;
	}

	public Float getQt_quant() {
		return qt_quant;
	}

	public void setQt_quant(Float qt_quant) {
		this.qt_quant = qt_quant;
	}

	public ProdutoModel getProdutoModel() {
		return produtoModel;
	}

	public void setProdutoModel(ProdutoModel produtoModel) {
		this.produtoModel = produtoModel;
	}

	public RefeicoesModel getRefeicoesModel() {
		return refeicoesModel;
	}

	public void setRefeicoesModel(RefeicoesModel refeicoesModel) {
		this.refeicoesModel = refeicoesModel;
	}

}
