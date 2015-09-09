package com.ifce.Financeiros.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;
import com.ifce.Financeiros.model.Pessoa;
import com.ifce.Financeiros.repository.Pessoas;

@FacesConverter(forClass = Pessoa.class)
public class PessoasConverter implements Converter {
	
	@Inject
	private Pessoas pessoas;
	
	public Object getAsObject(FacesContext context,
				UIComponent component, String value){
		Pessoa retorno = null;
		if(value != null && !"".equals(value)){
			retorno = this.pessoas.porId(new Long(value));
		}
		return retorno;
	}
		
	public String getAsString(FacesContext context, 
			UIComponent component, Object value){
		if(value != null){
			return((Pessoa) value).getId().toString();
		}
		return null;			
	}
}
