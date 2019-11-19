package br.gov.df.emater.negocio.veiculo.veiculo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.frazao.cadeiaresponsabilidade.teste.CadeiaSequenciada;

@Component("VeiculoExcluirCdSq")
public class VeiculoExcluirCdSq extends CadeiaSequenciada {

	@Autowired
	VeiculoExcluirCdSq(VeiculoExcluirCmd c1) {
		super(c1);
	}

}
