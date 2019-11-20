package br.gov.df.emater.negocio.sistema.usuario;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.frazao.cadeiaresponsabilidade.CadeiaSequencial;

@Component("UsuarioIniciarCdSq")
public class UsuarioIniciarCdSq extends CadeiaSequencial {

	@Autowired
	UsuarioIniciarCdSq(UsuarioIniciarCmd c1) {
		super(c1);
	}

}
