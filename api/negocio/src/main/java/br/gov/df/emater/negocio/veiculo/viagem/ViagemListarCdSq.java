package br.gov.df.emater.negocio.veiculo.viagem;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.frazao.cadeiaresponsabilidade.impl.CadeiaSequencial;

@Component("ViagemListarCdSq")
public class ViagemListarCdSq extends CadeiaSequencial {

	@Autowired
	ViagemListarCdSq(final ViagemListarCmd c1) {
		super(c1);
	}

}
