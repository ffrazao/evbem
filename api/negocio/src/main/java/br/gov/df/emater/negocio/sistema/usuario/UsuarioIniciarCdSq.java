package br.gov.df.emater.negocio.sistema.usuario;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.frazao.cadeiaresponsabilidade.impl.CadeiaSequencial;

@Component("UsuarioIniciarCdSq")
public class UsuarioIniciarCdSq extends CadeiaSequencial {

	@Autowired
	UsuarioIniciarCdSq(final UsuarioIniciarCmd c1) {
		super(c1);
	}

}
