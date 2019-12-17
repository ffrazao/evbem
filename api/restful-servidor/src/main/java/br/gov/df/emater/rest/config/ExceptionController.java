package br.gov.df.emater.rest.config;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import br.gov.df.emater.negocio.base.NegocioException;
import br.gov.df.emater.negocio.base.NegocioIntegridadeDadosException;

@ControllerAdvice
public class ExceptionController {
	
	@ExceptionHandler(NegocioException.class)
	public ResponseEntity<ErroPadrao> erro(NegocioException e, HttpServletRequest r) {
		e.printStackTrace();
		ErroPadrao ep = new ErroPadrao(HttpStatus.BAD_REQUEST.value(), e.getMessage(), System.currentTimeMillis());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ep);
	}
	
	@ExceptionHandler(NegocioIntegridadeDadosException.class)
	public ResponseEntity<ErroPadrao> erro(NegocioIntegridadeDadosException e, HttpServletRequest r) {
		e.printStackTrace();
		ErroPadrao ep = new ErroPadrao(HttpStatus.BAD_REQUEST.value(), e.getMessage(), System.currentTimeMillis());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ep);
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ErroPadrao> erro(MethodArgumentNotValidException e, HttpServletRequest r) {
		e.printStackTrace();
		ErroPadaroValidacao ep = new ErroPadaroValidacao(HttpStatus.BAD_REQUEST.value(), "Erro de validação", System.currentTimeMillis());
		for (FieldError x: e.getBindingResult().getFieldErrors()) {
			ep.addMensagemCampo(x.getField(), x.getDefaultMessage());
		}
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ep);
	}

}
