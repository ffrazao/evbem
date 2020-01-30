package br.gov.df.emater.negocio.sistema.usuario;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.frazao.cadeiaresponsabilidade.impl.CadeiaSequencial;

@Component("UsuarioExcluirCdSq")
public class UsuarioExcluirCdSq extends CadeiaSequencial {

	@Autowired
	UsuarioExcluirCdSq(final UsuarioExcluirCmd c1) {
		super(c1);
	}

}
