package br.com.namedida.model;

public class ItemCardapioModel {

	private Integer id_itens;
	private Float qt_quant;
	private ProdutoModel produtoModel;
	private CardapioModel cardapioModel;
	private Integer id_cardapiodia;

	public Integer getId_cardapiodia() {
		return id_cardapiodia;
	}

	public void setId_cardapiodia(Integer id_cardapiodia) {
		this.id_cardapiodia = id_cardapiodia;
	}

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

	public CardapioModel getCardapioModel() {
		return cardapioModel;
	}

	public void setCardapioModel(CardapioModel cardapioModel) {
		this.cardapioModel = cardapioModel;
	}

}
