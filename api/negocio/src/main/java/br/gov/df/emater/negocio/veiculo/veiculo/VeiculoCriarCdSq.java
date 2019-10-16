package br.gov.df.emater.negocio.veiculo.veiculo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.frazao.cadeiaresponsabilidade.CadeiaSequenciada;

@Component("VeiculoCriarCdSq")
public class VeiculoCriarCdSq extends CadeiaSequenciada {

	@Autowired
	VeiculoCriarCdSq(VeiculoCriarCmd c1) {
		super(c1);
	}

}
