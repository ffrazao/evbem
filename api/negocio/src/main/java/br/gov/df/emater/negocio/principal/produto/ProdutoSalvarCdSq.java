package br.gov.df.emater.negocio.principal.produto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.frazao.cadeiaresponsabilidade.CadeiaSequencial;

@Component("ProdutoSalvarCdSq")
public class ProdutoSalvarCdSq extends CadeiaSequencial {

	@Autowired
	ProdutoSalvarCdSq(final ProdutoSalvarCmd c1) {
		super(c1);
	}

}
