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
@Table(name = "tb_item_cardapio")
@NamedQueries({

		@NamedQuery(name = "ItemCardapioEntity.findAll", query = "SELECT p FROM ItemCardapioEntity p"),
		@NamedQuery(name = "ItemCardapioEntity.findItemCardapio", query = "SELECT p FROM ItemCardapioEntity p where p.cardapioEntity.codigo = :idcardapio")

})
public class ItemCardapioEntity {

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
	@JoinColumn(name = "id_cardapio", nullable = false)
	private CardapioEntity cardapioEntity;

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

	public CardapioEntity getCardapioEntity() {
		return cardapioEntity;
	}

	public void setCardapioEntity(CardapioEntity cardapioEntity) {
		this.cardapioEntity = cardapioEntity;
	}

}
