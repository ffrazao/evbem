package br.gov.df.emater.negocio.pessoa.pessoa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.frazao.cadeiaresponsabilidade.impl.CadeiaSequencial;

@Component("PessoaExcluirCdSq")
public class PessoaExcluirCdSq extends CadeiaSequencial {

	@Autowired
	PessoaExcluirCdSq(final PessoaExcluirCmd c1) {
		super(c1);
	}

}
