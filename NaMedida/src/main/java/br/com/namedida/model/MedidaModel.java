package br.com.namedida.model;

import org.exolab.castor.types.DateTime;

public class MedidaModel {

	private Integer codigo;
	private DateTime data;
	private Integer quantidade;
	private String cartao;
	private int totalhoje;
	private String diahoje;

	public String getDiahoje() {
		return diahoje;
	}

	public void setDiahoje(String diahoje) {
		this.diahoje = diahoje;
	}

	public int getTotalhoje() {
		return totalhoje;
	}

	public void setTotalhoje(int totalhoje) {
		this.totalhoje = totalhoje;
	}

	public Integer getCodigo() {
		return codigo;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

	public DateTime getData() {
		return data;
	}

	public void setData(DateTime data) {
		this.data = data;
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}

	public String getCartao() {
		return cartao;
	}

	public void setCartao(String cartao) {
		this.cartao = cartao;
	}

}