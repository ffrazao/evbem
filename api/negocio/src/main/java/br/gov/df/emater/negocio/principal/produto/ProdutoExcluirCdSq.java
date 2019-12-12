package br.gov.df.emater.negocio.principal.produto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.frazao.cadeiaresponsabilidade.CadeiaSequencial;

@Component("ProdutoExcluirCdSq")
public class ProdutoExcluirCdSq extends CadeiaSequencial {

	@Autowired
	ProdutoExcluirCdSq(final ProdutoExcluirCmd c1) {
		super(c1);
	}

}
