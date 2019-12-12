package br.gov.df.emater.negocio.principal.produto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.frazao.cadeiaresponsabilidade.CadeiaSequencial;

@Component("ProdutoIniciarCdSq")
public class ProdutoIniciarCdSq extends CadeiaSequencial {

	@Autowired
	ProdutoIniciarCdSq(final ProdutoIniciarCmd c1) {
		super(c1);
	}

}
