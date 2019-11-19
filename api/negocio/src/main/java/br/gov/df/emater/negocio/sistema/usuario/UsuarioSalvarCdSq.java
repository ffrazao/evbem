package br.gov.df.emater.negocio.sistema.usuario;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.frazao.cadeiaresponsabilidade.teste.CadeiaSequenciada;

@Component("UsuarioSalvarCdSq")
public class UsuarioSalvarCdSq extends CadeiaSequenciada {

	@Autowired
	UsuarioSalvarCdSq(UsuarioSalvarCmd c1) {
		super(c1);
	}

}
