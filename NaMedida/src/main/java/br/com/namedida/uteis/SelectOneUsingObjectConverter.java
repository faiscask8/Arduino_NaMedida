package br.com.namedida.uteis;

import java.lang.reflect.Field;
import java.util.Collection;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

/**
 * 
 * @author Victor Lindberg
 *
 */
@FacesConverter("selectOneUsingObjectConverter")
public class SelectOneUsingObjectConverter implements Converter {
	@Override
	public Object getAsObject(FacesContext context, UIComponent component,
			String value) {
		if (value == null || value.equals(""))
			return null;
		try {
			Long codigo = Long.valueOf(value);
			Collection items = (Collection) component.getAttributes().get(
					"items");
			return findById(items, codigo);
		} catch (Exception ex) {
			throw new ConverterException(
					"Não foi possível aplicar conversão de item com valor ["
							+ value + "] no componente [" + component.getId()
							+ "]", ex);
		}
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component,
			Object value) {
		if (value == null)
			return "";
		return getIdByReflection(value).toString();
	}

	private Object findById(Collection collection, Long idToFind) {
		for (Object obj : collection) {
			Long codigo = getIdByReflection(obj);
			if (codigo == idToFind)
				return obj;
		}
		return null;
	}

	private Long getIdByReflection(Object bean) {
		try {
			Field idField = bean.getClass().getDeclaredField("codigo");
			idField.setAccessible(true);
			return (Long) idField.get(bean);
		} catch (Exception ex) {
			throw new RuntimeException(
					"Não foi possível obter a propriedade 'codigo' do item", ex);
		}
	}
}