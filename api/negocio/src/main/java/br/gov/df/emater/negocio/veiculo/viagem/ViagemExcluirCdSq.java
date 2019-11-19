package br.gov.df.emater.negocio.veiculo.viagem;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.frazao.cadeiaresponsabilidade.teste.CadeiaSequenciada;

@Component("ViagemExcluirCdSq")
public class ViagemExcluirCdSq extends CadeiaSequenciada {

	@Autowired
	ViagemExcluirCdSq(ViagemExcluirCmd c1) {
		super(c1);
	}

}
