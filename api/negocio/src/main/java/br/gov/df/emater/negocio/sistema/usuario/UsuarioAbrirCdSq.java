package br.gov.df.emater.negocio.sistema.usuario;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.frazao.cadeiaresponsabilidade.CadeiaSequencial;

@Component
public class UsuarioAbrirCdSq extends CadeiaSequencial {

	@Autowired
	UsuarioAbrirCdSq(UsuarioAbrirCmd c1, UsuarioExecutarCmd c2, UsuarioFecharCmd c3) {
		super(c1, c2, c3);
	}

}
