package br.gov.df.emater.transporte.principal;

import br.gov.df.emater.transporte.FiltroDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class ProdutoFiltroDTO extends FiltroDTO {

	private String email;

	private String login;

	private String nome;

	private String perfil;

}
