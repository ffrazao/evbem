package br.gov.df.emater.negocio.veiculo.veiculo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.frazao.cadeiaresponsabilidade.impl.CadeiaSequencial;

@Component("VeiculoExcluirCdSq")
public class VeiculoExcluirCdSq extends CadeiaSequencial {

	@Autowired
	VeiculoExcluirCdSq(final VeiculoExcluirCmd c1) {
		super(c1);
	}

}
