package br.gov.df.emater.negocio.veiculo.veiculo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.frazao.cadeiaresponsabilidade.teste.CadeiaSequenciada;

@Component
public class VeiculoAbrirCdSq extends CadeiaSequenciada {

	@Autowired
	VeiculoAbrirCdSq(VeiculoAbrirCmd c1, VeiculoExecutarCmd c2, VeiculoFecharCmd c3) {
		super(c1, c2, c3);
	}

}
