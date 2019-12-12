package br.gov.df.emater.transporte.principal;

import java.util.Optional;
import java.util.Set;

import br.gov.df.emater.transporte.FiltroDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class PessoaFiltroDTO extends FiltroDTO {

	private Set<Optional<String>> cnpj;

	private Set<Optional<String>> cpf;

	private Set<Optional<String>> nome;

	private Set<Optional<String>> pesq;

	private Set<Optional<String>> siglaApelido;

}
