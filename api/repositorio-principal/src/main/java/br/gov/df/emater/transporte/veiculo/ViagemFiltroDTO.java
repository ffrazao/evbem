package br.gov.df.emater.transporte.veiculo;

import java.util.Optional;
import java.util.Set;

import br.gov.df.emater.transporte.FiltroDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class ViagemFiltroDTO extends FiltroDTO {

	private Set<Optional<String>> pesq;

}
