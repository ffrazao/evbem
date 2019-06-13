package br.gov.df.emater.rest.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TesteCtrl {

//	@Autowired
//	private NegocioFacade negocioFacade;

	@GetMapping("/teste")
	private String teste() throws Exception {
//		return (String) negocioFacade.executarSomenteLeitura("UsuarioAbrirCdSq");
		return "Abcdef";
	}
}
