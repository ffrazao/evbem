package br.gov.df.emater.negocio.pessoa.pessoa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.frazao.cadeiaresponsabilidade.CadeiaSequenciada;

@Component
public class PessoaAbrirCdSq extends CadeiaSequenciada {

	@Autowired
	PessoaAbrirCdSq(PessoaAbrirCmd c1, PessoaExecutarCmd c2, PessoaFecharCmd c3) {
		super(c1, c2, c3);
	}

}
