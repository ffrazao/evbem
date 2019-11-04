package br.gov.df.emater.negocio.pessoa.pessoa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.frazao.cadeiaresponsabilidade.CadeiaSequenciada;

@Component("PessoaIniciarCdSq")
public class PessoaIniciarCdSq extends CadeiaSequenciada {

	@Autowired
	PessoaIniciarCdSq(PessoaIniciarCmd c1) {
		super(c1);
	}

}