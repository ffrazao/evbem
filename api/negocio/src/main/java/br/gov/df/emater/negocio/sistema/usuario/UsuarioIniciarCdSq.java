package br.gov.df.emater.negocio.sistema.usuario;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.frazao.cadeiaresponsabilidade.CadeiaSequenciada;

@Component("UsuarioIniciarCdSq")
public class UsuarioIniciarCdSq extends CadeiaSequenciada {

	@Autowired
	UsuarioIniciarCdSq(UsuarioIniciarCmd c1) {
		super(c1);
	}

}
