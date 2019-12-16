package br.gov.df.emater.rest.config;

import java.util.ArrayList;
import java.util.List;

import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Setter;

@Data
@EqualsAndHashCode(callSuper = true)
public class ErroPadaroValidacao extends ErroPadrao {

	private static final long serialVersionUID = 1L;
	
	@Setter(value = AccessLevel.NONE)
	private List<ErroPadraoInformaCampos> erros = new ArrayList<>();

	public ErroPadaroValidacao(Integer status, String msg, Long instante) {
		super(status, msg, instante);
	}
	
	public void addMensagemCampo(String nomeCampo, String msg) {
		this.erros.add(new ErroPadraoInformaCampos(nomeCampo, msg));
	}

}
