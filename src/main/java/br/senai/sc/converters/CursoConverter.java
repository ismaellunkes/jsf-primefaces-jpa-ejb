package br.senai.sc.converters;


import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.senai.sc.models.Curso;

@FacesConverter(value = "cursoConverter", forClass = Curso.class)
public class CursoConverter implements Converter{
	
	@Override
	public Curso getAsObject(FacesContext context, UIComponent component,
			String value) {
	
		if (value == null || value.trim().isEmpty()) {
	        return null;
	    }

	    return new Curso(Long.valueOf(value));		
		
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component,
			Object value) {
		if (value instanceof Curso) {
			Curso curso = (Curso) value;
			return curso.getIdCurso().toString();
		} else {
			return null;
		}
	}
}