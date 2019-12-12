package br.gov.df.emater.rest.controller.veiculo;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.gov.df.emater.repositorio_principal.entidade.veiculo.Veiculo;
import br.gov.df.emater.rest.controller.base.BaseCrudCtrl;
import br.gov.df.emater.transporte.veiculo.VeiculoFiltroDTO;

@RestController()
@RequestMapping("veiculo")
public class VeiculoCtrl extends BaseCrudCtrl<Veiculo, VeiculoFiltroDTO, Veiculo> {

	public VeiculoCtrl() {
		super("Veiculo");
	}

}
