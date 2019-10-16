package br.gov.df.emater.negocio.veiculo.veiculo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.frazao.cadeiaresponsabilidade.CadeiaSequenciada;

@Component("VeiculoSalvarCdSq")
public class VeiculoSalvarCdSq extends CadeiaSequenciada {

	@Autowired
	VeiculoSalvarCdSq(VeiculoSalvarCmd c1) {
		super(c1);
	}

}
