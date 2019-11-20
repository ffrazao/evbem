package br.gov.df.emater.negocio.veiculo.viagem;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.frazao.cadeiaresponsabilidade.CadeiaSequencial;

@Component("ViagemIniciarCdSq")
public class ViagemIniciarCdSq extends CadeiaSequencial {

	@Autowired
	ViagemIniciarCdSq(ViagemIniciarCmd c1) {
		super(c1);
	}

}
