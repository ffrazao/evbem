package br.gov.df.emater.negocio.pessoa.pessoa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.frazao.cadeiaresponsabilidade.impl.CadeiaSequencial;

@Component
public class PessoaAbrirCdSq extends CadeiaSequencial {

	@Autowired
	PessoaAbrirCdSq(final PessoaAbrirCmd c1, final PessoaExecutarCmd c2, final PessoaFecharCmd c3) {
		super(c1, c2, c3);
	}

}
