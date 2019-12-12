package br.gov.df.emater.negocio.pessoa.pessoa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.frazao.cadeiaresponsabilidade.CadeiaSequencial;

@Component("PessoaIniciarCdSq")
public class PessoaIniciarCdSq extends CadeiaSequencial {

	@Autowired
	PessoaIniciarCdSq(final PessoaIniciarCmd c1) {
		super(c1);
	}

}
