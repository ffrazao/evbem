package br.gov.df.emater.rest.config;

import java.io.Serializable;

import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Setter;

@Data
@EqualsAndHashCode(callSuper = false)
public class ErroPadrao implements Serializable {

	private static final long serialVersionUID = 1L;

	@Setter(value = AccessLevel.NONE)
	private Integer status;
	
	@Setter(value = AccessLevel.NONE)
	private String msg;
	
	@Setter(value = AccessLevel.NONE)
	private Long instante;

	public ErroPadrao(Integer status, String msg, Long instante) {
		super();
		this.status = status;
		this.msg = msg;
		this.instante = instante;
	}
	
}
