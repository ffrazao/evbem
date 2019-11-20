package br.gov.df.emater.negocio.sistema.usuario;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.frazao.cadeiaresponsabilidade.CadeiaSequencial;

@Component("UsuarioListarCdSq")
public class UsuarioListarCdSq extends CadeiaSequencial {

	@Autowired
	UsuarioListarCdSq(UsuarioListarCmd c1) {
		super(c1);
	}

}
