package br.gov.df.emater.rest.config;

import java.io.Serializable;

import lombok.Data;

@Data
public class ErroPadrao implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer status;
	
	private String msg;
	
	private Long instante;

	public ErroPadrao(Integer status, String msg, Long instante) {
		super();
		this.status = status;
		this.msg = msg;
		this.instante = instante;
	}
	
}
