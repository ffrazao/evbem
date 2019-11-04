package br.gov.df.emater.negocio.principal.produto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.frazao.cadeiaresponsabilidade.CadeiaSequenciada;

@Component("ProdutoIniciarCdSq")
public class ProdutoIniciarCdSq extends CadeiaSequenciada {

	@Autowired
	ProdutoIniciarCdSq(ProdutoIniciarCmd c1) {
		super(c1);
	}

}