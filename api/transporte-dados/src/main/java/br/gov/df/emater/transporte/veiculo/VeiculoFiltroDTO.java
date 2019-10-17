package br.gov.df.emater.transporte.veiculo;

import br.gov.df.emater.transporte.FiltroDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class VeiculoFiltroDTO extends FiltroDTO {
	
	private String pesq;

	private String produtoTipo;

	private String marca;

	private String modelo;

	private String numeroSerie;

}
