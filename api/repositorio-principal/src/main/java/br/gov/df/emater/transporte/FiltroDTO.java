package br.gov.df.emater.transporte;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Sort.Direction;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class FiltroDTO implements DTO {

	// numero da página
	private Integer pag = 1;

	// quantidade de registros por pagina
	private Integer qtd = 1000;
	
	// campo de ordenação da página
	private List<String> ordem = new ArrayList<>();
	
	// direção de ordenação da página
	private Direction dir = Direction.ASC;

}
