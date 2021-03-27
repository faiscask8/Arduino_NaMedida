package br.com.namedida.repository.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "tb_dia")
@NamedQueries({

		@NamedQuery(name = "DiaEntity.findAll", query = "SELECT p FROM DiaEntity p"),
		@NamedQuery(name = "DiaEntity.findCardapio", query = "SELECT p FROM DiaEntity p where p.descricao =:diasemana")

})
public class DiaEntity {

	@Id
	@GeneratedValue
	@Column(name = "id_dia")
	private Integer codigo;

	@Column(name = "descricao")
	private String descricao;

	@OneToOne
	@JoinColumn(name = "id_usuario_cadastro")
	private UsuarioEntity usuarioEntity;

	@OneToOne
	@JoinColumn(name = "id_cardapio")
	private CardapioEntity cardapioEntity;

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

	public UsuarioEntity getUsuarioEntity() {
		return usuarioEntity;
	}

	public void setUsuarioEntity(UsuarioEntity usuarioEntity) {
		this.usuarioEntity = usuarioEntity;
	}

	public CardapioEntity getCardapioEntity() {
		return cardapioEntity;
	}

	public void setCardapioEntity(CardapioEntity cardapioEntity) {
		this.cardapioEntity = cardapioEntity;
	}

}
