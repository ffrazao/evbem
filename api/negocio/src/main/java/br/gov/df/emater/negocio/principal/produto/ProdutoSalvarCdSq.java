package br.gov.df.emater.negocio.principal.produto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.frazao.cadeiaresponsabilidade.CadeiaSequenciada;

@Component("ProdutoSalvarCdSq")
public class ProdutoSalvarCdSq extends CadeiaSequenciada {

	@Autowired
	ProdutoSalvarCdSq(ProdutoSalvarCmd c1) {
		super(c1);
	}

}
