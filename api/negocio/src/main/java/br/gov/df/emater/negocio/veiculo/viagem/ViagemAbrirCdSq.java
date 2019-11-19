package br.gov.df.emater.negocio.veiculo.viagem;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.frazao.cadeiaresponsabilidade.teste.CadeiaSequenciada;

@Component
public class ViagemAbrirCdSq extends CadeiaSequenciada {

	@Autowired
	ViagemAbrirCdSq(ViagemAbrirCmd c1, ViagemExecutarCmd c2, ViagemFecharCmd c3) {
		super(c1, c2, c3);
	}

}
