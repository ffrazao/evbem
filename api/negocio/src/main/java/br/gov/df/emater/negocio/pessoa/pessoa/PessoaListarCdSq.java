package br.gov.df.emater.negocio.pessoa.pessoa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.frazao.cadeiaresponsabilidade.CadeiaSequenciada;

@Component("PessoaListarCdSq")
public class PessoaListarCdSq extends CadeiaSequenciada {

	@Autowired
	PessoaListarCdSq(PessoaListarCmd c1) {
		super(c1);
	}

}
