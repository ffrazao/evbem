package br.gov.df.emater.negocio.veiculo.veiculo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.frazao.cadeiaresponsabilidade.CadeiaSequenciada;

@Component("VeiculoListarCdSq")
public class VeiculoListarCdSq extends CadeiaSequenciada {

	@Autowired
	VeiculoListarCdSq(VeiculoListarCmd c1) {
		super(c1);
	}

}
