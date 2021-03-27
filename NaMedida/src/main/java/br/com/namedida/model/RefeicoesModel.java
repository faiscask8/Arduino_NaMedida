package br.com.namedida.model;

import java.util.Date;

import org.exolab.castor.types.DateTime;

public class RefeicoesModel {

	private Integer codigo;
	private Integer totalalunos;
	private Date data;
	private UsuarioModel usuarioModel;
	private CardapioModel cardapioModel;

	public Integer getCodigo() {
		return codigo;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

	public Integer getTotalalunos() {
		return totalalunos;
	}

	public void setTotalalunos(Integer totalalunos) {
		this.totalalunos = totalalunos;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
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
