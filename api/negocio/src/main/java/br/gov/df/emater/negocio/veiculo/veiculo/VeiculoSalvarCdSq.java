package br.gov.df.emater.negocio.veiculo.veiculo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.frazao.cadeiaresponsabilidade.impl.CadeiaSequencial;

@Component("VeiculoSalvarCdSq")
public class VeiculoSalvarCdSq extends CadeiaSequencial {

	@Autowired
	VeiculoSalvarCdSq(final VeiculoSalvarCmd c1) {
		super(c1);
	}

}
