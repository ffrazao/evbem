package br.gov.df.emater.negocio.pessoa.pessoa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.frazao.cadeiaresponsabilidade.CadeiaSequencial;

@Component("PessoaListarCdSq")
public class PessoaListarCdSq extends CadeiaSequencial {

	@Autowired
	PessoaListarCdSq(PessoaListarCmd c1) {
		super(c1);
	}

}
