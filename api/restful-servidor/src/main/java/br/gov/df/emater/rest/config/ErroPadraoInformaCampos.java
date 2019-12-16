package br.gov.df.emater.rest.config;

import java.io.Serializable;

import lombok.Data;

@Data
public class ErroPadraoInformaCampos implements Serializable {

	private static final long serialVersionUID = 1L;

	private String nomeCampo;

	private String msg;

	private Long instante;

	public ErroPadraoInformaCampos(String nomeCampo, String msg) {
		super();
		this.nomeCampo = nomeCampo;
		this.msg = msg;
	}

}
