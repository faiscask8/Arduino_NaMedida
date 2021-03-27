package br.com.namedida.repository.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "tb_item_refeicoes")
@NamedQueries({

		@NamedQuery(name = "ItemRefeicoesEntity.findAll", query = "SELECT p FROM ItemRefeicoesEntity p"),
		@NamedQuery(name = "ItemRefeicoesEntity.findItemRefeicoes", query = "SELECT p FROM ItemRefeicoesEntity p where p.refeicoesEntity.codigo = :idrefeicoes")

})
public class ItemRefeicoesEntity {

	@Id
	@GeneratedValue
	@Column(name = "id_itens")
	private Integer id_itens;

	@Column(name = "qt_quant")
	private float qt_quant;

	@ManyToOne
	@JoinColumn(name = "id_produto", nullable = false)
	private ProdutoEntity produtoEntity;

	@ManyToOne
	@JoinColumn(name = "id_refeicoes", nullable = false)
	private RefeicoesEntity refeicoesEntity;

	public Integer getId_itens() {
		return id_itens;
	}

	public void setId_itens(Integer id_itens) {
		this.id_itens = id_itens;
	}

	public float getQt_quant() {
		return qt_quant;
	}

	public void setQt_quant(float qt_quant) {
		this.qt_quant = qt_quant;
	}

	public ProdutoEntity getProdutoEntity() {
		return produtoEntity;
	}

	public void setProdutoEntity(ProdutoEntity produtoEntity) {
		this.produtoEntity = produtoEntity;
	}

	public RefeicoesEntity getRefeicoesEntity() {
		return refeicoesEntity;
	}

	public void setRefeicoesEntity(RefeicoesEntity refeicoesEntity) {
		this.refeicoesEntity = refeicoesEntity;
	}

}
