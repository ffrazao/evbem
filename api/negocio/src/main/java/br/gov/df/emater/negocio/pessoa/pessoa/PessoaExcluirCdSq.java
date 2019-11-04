package br.gov.df.emater.negocio.pessoa.pessoa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.frazao.cadeiaresponsabilidade.CadeiaSequenciada;

@Component("PessoaExcluirCdSq")
public class PessoaExcluirCdSq extends CadeiaSequenciada {

	@Autowired
	PessoaExcluirCdSq(PessoaExcluirCmd c1) {
		super(c1);
	}

}
