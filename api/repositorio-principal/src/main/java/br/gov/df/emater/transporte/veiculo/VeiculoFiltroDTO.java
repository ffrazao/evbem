package br.gov.df.emater.transporte.veiculo;

import java.util.Optional;
import java.util.Set;

import br.gov.df.emater.repositorio_principal.dominio.veiculo.Combustivel;
import br.gov.df.emater.transporte.FiltroDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class VeiculoFiltroDTO extends FiltroDTO {
	
	private Set<Optional<String>> pesq;

	private Set<Optional<String>> produtoTipo;

	private Set<Optional<String>> marca;

	private Set<Optional<String>> modelo;

	private Set<Optional<String>> numeroSerie;
	
	private Set<Optional<String>> renavan;
	
	private Set<Optional<Integer>> anoFabricacao;

	private Set<Optional<Integer>> anoModelo;

	private Set<Optional<String>> cor;

	private Set<Optional<Combustivel>> combustivel;
	
	private Set<Optional<String>> placa;
	
	private Set<Optional<String>> identificacaoPatrimonial;

}
