package br.gov.df.emater.rest.controller.base;

import org.springframework.beans.factory.annotation.Autowired;

import br.gov.df.emater.negocio.base.NegocioFacade;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;

@Data
public class BaseCtrl {

	@Setter(value = AccessLevel.NONE)
	private final String funcionalidade;
	
	@Autowired
	@Setter(value = AccessLevel.NONE)
	private NegocioFacade negocioFacade;
	
	public BaseCtrl(String funcionalidade) {
		this.funcionalidade = funcionalidade;
	}

}
