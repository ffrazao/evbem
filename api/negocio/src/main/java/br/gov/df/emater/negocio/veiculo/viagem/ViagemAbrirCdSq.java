package br.gov.df.emater.negocio.veiculo.viagem;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.frazao.cadeiaresponsabilidade.CadeiaSequencial;

@Component
public class ViagemAbrirCdSq extends CadeiaSequencial {

	@Autowired
	ViagemAbrirCdSq(final ViagemAbrirCmd c1, final ViagemExecutarCmd c2, final ViagemFecharCmd c3) {
		super(c1, c2, c3);
	}

}
