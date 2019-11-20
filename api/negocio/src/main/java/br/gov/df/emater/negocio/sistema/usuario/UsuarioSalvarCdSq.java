package br.gov.df.emater.negocio.sistema.usuario;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.frazao.cadeiaresponsabilidade.CadeiaSequencial;

@Component("UsuarioSalvarCdSq")
public class UsuarioSalvarCdSq extends CadeiaSequencial {

	@Autowired
	UsuarioSalvarCdSq(UsuarioSalvarCmd c1) {
		super(c1);
	}

}
