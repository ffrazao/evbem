package br.gov.df.emater.negocio.principal.produto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.frazao.cadeiaresponsabilidade.CadeiaSequencial;

@Component("ProdutoListarCdSq")
public class ProdutoListarCdSq extends CadeiaSequencial {

	@Autowired
	ProdutoListarCdSq(ProdutoListarCmd c1) {
		super(c1);
	}

}
