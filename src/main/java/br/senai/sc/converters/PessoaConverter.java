package br.senai.sc.converters;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;
import br.senai.sc.models.Pessoa;

@FacesConverter(value = "pessoaConverter", forClass = Pessoa.class)
public class PessoaConverter implements Converter {

	@Override
	public Pessoa getAsObject(FacesContext context,
	        UIComponent component, String value)
	        throws ConverterException {

		if (value == null || value.trim().isEmpty()) {
	        return null;
	    }

	    return new Pessoa(Long.valueOf(value));	
	}

	@Override
	public String getAsString(FacesContext context,
	        UIComponent component, Object value)
	        throws ConverterException {

		if (value instanceof Pessoa) {
			Pessoa pessoa = (Pessoa) value;
			return pessoa.getIdPessoa().toString();
		} else {
			return null;
		}
	}

}
