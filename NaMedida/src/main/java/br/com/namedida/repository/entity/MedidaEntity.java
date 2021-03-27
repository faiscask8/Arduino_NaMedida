package br.com.namedida.repository.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import org.exolab.castor.types.DateTime;

@Entity
@Table(name = "medida")
@NamedQueries({

@NamedQuery(name = "MedidaEntity.findAll", query = "SELECT p FROM MedidaEntity p"),
// @NamedQuery(name = "MedidaEntity.findData", query=
// "SELECT med.data, SUM(med.quantidade) as Total FROM MedidaEntity med where med.data = CURRENT_DATE group by med.data")

})
public class MedidaEntity {

	@Id
	@GeneratedValue
	@Column(name = "id_medida")
	private Integer codigo;

	@Column(name = "quant")
	private Integer quantidade;

	@Column(name = "data")
	private DateTime data;

	@Column(name = "cartao")
	private String cartao;

	public Integer getCodigo() {
		return codigo;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}

	public DateTime getData() {
		return data;
	}

	public void setData(DateTime data) {
		this.data = data;
	}

	public String getCartao() {
		return cartao;
	}

	public void setCartao(String cartao) {
		this.cartao = cartao;
	}

}
