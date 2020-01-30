package br.gov.df.emater.negocio.sistema.usuario;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.frazao.cadeiaresponsabilidade.impl.CadeiaSequencial;

@Component
public class UsuarioAbrirCdSq extends CadeiaSequencial {

	@Autowired
	UsuarioAbrirCdSq(final UsuarioAbrirCmd c1, final UsuarioExecutarCmd c2, final UsuarioFecharCmd c3) {
		super(c1, c2, c3);
	}

}
