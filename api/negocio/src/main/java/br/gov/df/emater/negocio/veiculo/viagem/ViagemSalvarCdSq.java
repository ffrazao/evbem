package br.gov.df.emater.negocio.veiculo.viagem;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.frazao.cadeiaresponsabilidade.impl.CadeiaSequencial;

@Component("ViagemSalvarCdSq")
public class ViagemSalvarCdSq extends CadeiaSequencial {

	@Autowired
	ViagemSalvarCdSq(final ViagemSalvarCmd c1) {
		super(c1);
	}

}
