package br.gov.df.emater.negocio.veiculo.viagem;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.frazao.cadeiaresponsabilidade.teste.CadeiaSequenciada;

@Component("ViagemListarCdSq")
public class ViagemListarCdSq extends CadeiaSequenciada {

	@Autowired
	ViagemListarCdSq(ViagemListarCmd c1) {
		super(c1);
	}

}
