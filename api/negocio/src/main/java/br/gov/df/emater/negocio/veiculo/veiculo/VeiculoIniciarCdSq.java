package br.gov.df.emater.negocio.veiculo.veiculo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.frazao.cadeiaresponsabilidade.CadeiaSequenciada;

@Component("VeiculoIniciarCdSq")
public class VeiculoIniciarCdSq extends CadeiaSequenciada {

	@Autowired
	VeiculoIniciarCdSq(VeiculoIniciarCmd c1) {
		super(c1);
	}

}
