package br.com.namedida.uteis;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;

import br.com.namedida.repository.ProdutoRepository;
import br.com.namedida.repository.entity.ProdutoEntity;

@FacesConverter("produtoConverter")
public class LocalProdutoConverter implements Converter {

	@Inject
	private ProdutoRepository produtoRepository;

	@Override
	public Object getAsObject(FacesContext context, UIComponent componente,
			String valor) {
		try {
			return produtoRepository.GetProdutoConverter(Integer
					.parseInt(valor));
		} catch (RuntimeException e) {
			return null;
		}
	}

	@Override
	public String getAsString(FacesContext context, UIComponent componente,
			Object objeto) {
		try {
			ProdutoEntity produtoEntity = (ProdutoEntity) objeto;
			Integer codigo = produtoEntity.getCodigo();
			System.out.println("Converter Value: " + codigo);
			return codigo.toString();
		} catch (RuntimeException e) {
			return null;
		}

	}

}
