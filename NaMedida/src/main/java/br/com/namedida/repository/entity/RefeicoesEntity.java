package br.com.namedida.repository.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.exolab.castor.types.DateTime;

@Entity
@Table(name = "refeicoes")
@NamedQueries({

@NamedQuery(name = "RefeicoesEntity.findAll", query = "SELECT p FROM RefeicoesEntity p"),

})
public class RefeicoesEntity {

	@Id
	@GeneratedValue
	@Column(name = "id_refeicao")
	private Integer codigo;

	@Column(name = "totalalunos")
	private Integer totalalunos;

	@Column(name = "data")
	private Date data;

	@OneToOne
	@JoinColumn(name = "id_usuario_cadastro")
	private UsuarioEntity usuarioEntity;

	@OneToOne
	@JoinColumn(name = "id_cardapio")
	private CardapioEntity cardapioEntity;

	// @OneToMany
	// (mappedBy = "cardapioEntity", cascade = CascadeType.ALL, orphanRemoval =
	// true, fetch = FetchType.LAZY)
	// private List <ItemCardapioEntity> itemCardapio = new ArrayList<>();

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

	public void setData(Date date) {
		this.data = date;
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
