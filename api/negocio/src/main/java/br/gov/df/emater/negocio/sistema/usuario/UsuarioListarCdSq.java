package br.gov.df.emater.negocio.sistema.usuario;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.frazao.cadeiaresponsabilidade.impl.CadeiaSequencial;

@Component("UsuarioListarCdSq")
public class UsuarioListarCdSq extends CadeiaSequencial {

	@Autowired
	UsuarioListarCdSq(final UsuarioListarCmd c1) {
		super(c1);
	}

}
