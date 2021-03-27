package br.com.namedida.model;

public class DiaModel {
	private Integer codigo;
	private String descricao;
	private UsuarioModel usuarioModel;
	private CardapioModel cardapioModel;
	private String cardapiohoje;

	public String getCardapiohoje() {
		return cardapiohoje;
	}

	public void setCardapiohoje(String cardapiohoje) {
		this.cardapiohoje = cardapiohoje;
	}

	public Integer getCodigo() {
		return codigo;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public UsuarioModel getUsuarioModel() {
		return usuarioModel;
	}

	public void setUsuarioModel(UsuarioModel usuarioModel) {
		this.usuarioModel = usuarioModel;
	}

	public CardapioModel getCardapioModel() {
		return cardapioModel;
	}

	public void setCardapioModel(CardapioModel cardapioModel) {
		this.cardapioModel = cardapioModel;
	}

}
