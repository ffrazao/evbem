package br.gov.df.emater.negocio.veiculo.viagem;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.frazao.cadeiaresponsabilidade.CadeiaSequenciada;

@Component("ViagemSalvarCdSq")
public class ViagemSalvarCdSq extends CadeiaSequenciada {

	@Autowired
	ViagemSalvarCdSq(ViagemSalvarCmd c1) {
		super(c1);
	}

}
