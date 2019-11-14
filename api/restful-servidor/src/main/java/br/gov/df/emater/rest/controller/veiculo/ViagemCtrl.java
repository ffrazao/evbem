package br.gov.df.emater.rest.controller.veiculo;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.gov.df.emater.repositorio_principal.entidade.veiculo.Viagem;
import br.gov.df.emater.rest.controller.base.BaseCrudCtrl;
import br.gov.df.emater.transporte.veiculo.ViagemFiltroDTO;

@RestController()
@RequestMapping("viagem")
public class ViagemCtrl extends BaseCrudCtrl<Viagem, ViagemFiltroDTO, Viagem> {
	
	public ViagemCtrl() {
		super("Viagem");
	}
	
}
