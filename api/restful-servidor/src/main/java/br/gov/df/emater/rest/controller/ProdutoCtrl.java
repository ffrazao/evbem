package br.gov.df.emater.rest.controller;

import java.net.URLDecoder;
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
import br.gov.df.emater.repositorio_principal.entidade.principal.Produto;
import br.gov.df.emater.transporte.principal.ProdutoFiltroDTO;

@SuppressWarnings("unchecked")
@RestController()
@RequestMapping("produto")
public class ProdutoCtrl {

	@Autowired
	protected NegocioFacade negocioFacade;

	@PostMapping()
	protected List<Produto> alterar(@RequestBody(required = true) List<Produto> registros) throws Exception {
		return (List<Produto>) negocioFacade.executarComEscrita("ProdutoSalvarCdSq", registros);
	}

	@GetMapping(value = "/criar")
	protected Produto criar(@RequestParam(name = "modelo", required = false) String modeloStr) throws Exception {
		Produto modelo = null;
		if (modeloStr != null) {
			ObjectMapper om = new ObjectMapper();
			modelo = om.readValue(URLDecoder.decode(modeloStr.replace("+", "%2B"), "UTF-8").replace("%2B", "+"),
					Produto.class);
		}
		return (Produto) negocioFacade.executarSomenteLeitura("ProdutoCriarCdSq", modelo);
	}

	@DeleteMapping(value = "/{ids}")
	protected void excluir(@PathVariable(name = "ids", required = true) List<Integer> ids) throws Exception {
		negocioFacade.executarComEscrita("ProdutoExcluirCdSq", ids);
	}

	@PutMapping()
	protected List<Produto> incluir(@RequestBody(required = true) List<Produto> registros) throws Exception {
		return (List<Produto>) negocioFacade.executarComEscrita("ProdutoSalvarCdSq", registros);
	}

	@GetMapping(value = "")
	protected List<Produto> listar(@RequestParam(name = "filtro", required = false) String filtroStr) throws Exception {
		ProdutoFiltroDTO filtro = null;
		if (filtroStr != null) {
			ObjectMapper om = new ObjectMapper();
			filtro = om.readValue(URLDecoder.decode(filtroStr.replace("+", "%2B"), "UTF-8").replace("%2B", "+"),
					ProdutoFiltroDTO.class);
		}
		return (List<Produto>) negocioFacade.executarSomenteLeitura("ProdutoListarCdSq", filtro);
	}

	@PostMapping(value = "/salvar")
	protected List<Produto> salvar(@RequestBody(required = true) List<Produto> registros) throws Exception {
		return (List<Produto>) negocioFacade.executarComEscrita("ProdutoSalvarCdSq", registros);
	}

	@GetMapping(value = "/{ids}")
	protected List<Produto> ver(@PathVariable(name = "ids", required = true) List<Integer> ids) throws Exception {
		return (List<Produto>) negocioFacade.executarSomenteLeitura("ProdutoListarCdSq", ids);
	}
}
