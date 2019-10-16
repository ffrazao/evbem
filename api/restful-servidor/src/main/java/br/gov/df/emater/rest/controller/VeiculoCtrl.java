package br.gov.df.emater.rest.controller;

import java.net.URLDecoder;
import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.gov.df.emater.negocio.base.NegocioFacade;
import br.gov.df.emater.repositorio_principal.entidade.veiculo.Veiculo;
import br.gov.df.emater.transporte.veiculo.VeiculoFiltroDTO;

@SuppressWarnings("unchecked")
@RestController()
@RequestMapping("veiculo")
public class VeiculoCtrl {

	@Autowired
	protected NegocioFacade negocioFacade;

	@PostMapping()
	protected List<Veiculo> alterar(@RequestBody(required = true) List<Veiculo> registros, Principal usuario)
			throws Exception {
		return (List<Veiculo>) negocioFacade.executarComEscrita("VeiculoSalvarCdSq", registros, usuario);
	}

	@GetMapping(value = "/criar")
	protected Veiculo criar(@RequestParam(name = "modelo", required = false) String modeloStr, Principal usuario)
			throws Exception {
		Veiculo modelo = null;
		if (modeloStr != null) {
			ObjectMapper om = new ObjectMapper();
			modelo = om.readValue(URLDecoder.decode(modeloStr.replace("+", "%2B"), "UTF-8").replace("%2B", "+"),
					Veiculo.class);
		}
		return (Veiculo) negocioFacade.executarSomenteLeitura("VeiculoCriarCdSq", modelo, usuario);
	}

	@DeleteMapping(value = "/{ids}")
	protected void excluir(@PathVariable(name = "ids", required = true) List<Integer> ids, Principal usuario)
			throws Exception {
		negocioFacade.executarComEscrita("VeiculoExcluirCdSq", ids, usuario);
	}

	@PutMapping()
	protected List<Veiculo> incluir(@RequestBody(required = true) List<Veiculo> registros, Principal usuario)
			throws Exception {
		return (List<Veiculo>) negocioFacade.executarComEscrita("VeiculoSalvarCdSq", registros, usuario);
	}

	@GetMapping(value = "")
	protected List<Veiculo> listar(@RequestParam(name = "filtro", required = false) String filtroStr, Principal usuario)
			throws Exception {
		VeiculoFiltroDTO filtro = null;
		if (filtroStr != null) {
			ObjectMapper om = new ObjectMapper();
			filtro = om.readValue(URLDecoder.decode(filtroStr.replace("+", "%2B"), "UTF-8").replace("%2B", "+"),
					VeiculoFiltroDTO.class);
		}
		return (List<Veiculo>) negocioFacade.executarSomenteLeitura("VeiculoListarCdSq", filtro, usuario);
	}

	@PostMapping(value = "/salvar")
	protected List<Veiculo> salvar(@RequestBody(required = true) List<Veiculo> registros, Principal usuario)
			throws Exception {
		return (List<Veiculo>) negocioFacade.executarComEscrita("VeiculoSalvarCdSq", registros, usuario);
	}

	@GetMapping(value = "/{ids}")
	protected List<Veiculo> ver(@PathVariable(name = "ids", required = true) List<Integer> ids, Principal usuario)
			throws Exception {
		return (List<Veiculo>) negocioFacade.executarSomenteLeitura("VeiculoListarCdSq", ids, usuario);
	}
}
