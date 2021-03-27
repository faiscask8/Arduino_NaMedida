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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "tb_cardapio")
@NamedQueries({

@NamedQuery(name = "CardapioEntity.findAll", query = "SELECT p FROM CardapioEntity p")

})
public class CardapioEntity {

	@Id
	@GeneratedValue
	@Column(name = "id_cardapio")
	private Integer codigo;

	@Column(name = "nm_descricao")
	private String descricao;

	@OneToOne
	@JoinColumn(name = "id_usuario_cadastro")
	private UsuarioEntity usuarioEntity;

	@OneToMany(mappedBy = "cardapioEntity", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
	private List<ItemCardapioEntity> itemCardapio = new ArrayList<>();

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

	public List<ItemCardapioEntity> getItemCardapio() {
		return itemCardapio;
	}

	public void setItemCardapio(List<ItemCardapioEntity> itemCardapio) {
		this.itemCardapio = itemCardapio;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codigo == null) ? 0 : codigo.hashCode());
		result = prime * result
				+ ((descricao == null) ? 0 : descricao.hashCode());
		result = prime * result
				+ ((itemCardapio == null) ? 0 : itemCardapio.hashCode());
		result = prime * result
				+ ((usuarioEntity == null) ? 0 : usuarioEntity.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CardapioEntity other = (CardapioEntity) obj;
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals(other.codigo))
			return false;
		if (descricao == null) {
			if (other.descricao != null)
				return false;
		} else if (!descricao.equals(other.descricao))
			return false;
		if (itemCardapio == null) {
			if (other.itemCardapio != null)
				return false;
		} else if (!itemCardapio.equals(other.itemCardapio))
			return false;
		if (usuarioEntity == null) {
			if (other.usuarioEntity != null)
				return false;
		} else if (!usuarioEntity.equals(other.usuarioEntity))
			return false;
		return true;
	}

}
