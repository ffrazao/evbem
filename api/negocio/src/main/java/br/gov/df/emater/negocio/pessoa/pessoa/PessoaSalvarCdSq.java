package br.gov.df.emater.negocio.pessoa.pessoa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.frazao.cadeiaresponsabilidade.CadeiaSequencial;

@Component("PessoaSalvarCdSq")
public class PessoaSalvarCdSq extends CadeiaSequencial {

	@Autowired
	PessoaSalvarCdSq(PessoaSalvarCmd c1) {
		super(c1);
	}

}
