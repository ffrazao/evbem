package br.gov.df.emater.negocio.veiculo.veiculo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.frazao.cadeiaresponsabilidade.impl.CadeiaSequencial;

@Component("VeiculoIniciarCdSq")
public class VeiculoIniciarCdSq extends CadeiaSequencial {

	@Autowired
	VeiculoIniciarCdSq(final VeiculoIniciarCmd c1) {
		super(c1);
	}

}
