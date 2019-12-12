package br.gov.df.emater.negocio.veiculo.veiculo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.frazao.cadeiaresponsabilidade.CadeiaSequencial;

@Component
public class VeiculoAbrirCdSq extends CadeiaSequencial {

	@Autowired
	VeiculoAbrirCdSq(final VeiculoAbrirCmd c1, final VeiculoExecutarCmd c2, final VeiculoFecharCmd c3) {
		super(c1, c2, c3);
	}

}
