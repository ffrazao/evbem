package br.gov.df.emater.negocio.veiculo.viagem;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.frazao.cadeiaresponsabilidade.impl.CadeiaSequencial;

@Component("ViagemExcluirCdSq")
public class ViagemExcluirCdSq extends CadeiaSequencial {

	@Autowired
	ViagemExcluirCdSq(final ViagemExcluirCmd c1) {
		super(c1);
	}

}
