package br.gov.df.emater.negocio.principal.produto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.frazao.cadeiaresponsabilidade.impl.CadeiaSequencial;

@Component
public class ProdutoAbrirCdSq extends CadeiaSequencial {

	@Autowired
	ProdutoAbrirCdSq(final ProdutoAbrirCmd c1, final ProdutoExecutarCmd c2, final ProdutoFecharCmd c3) {
		super(c1, c2, c3);
	}

}
