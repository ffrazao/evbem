package br.gov.df.emater.repositorio_principal.entidade.veiculo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import br.gov.df.emater.repositorio_principal.entidade.evento.Evento;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * The persistent class for the Viagem database table.
 * 
 */
@Entity
@Table(catalog = "veiculo")
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class Viagem extends Evento implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Column(name = "quilometragem_inicio")
	private Integer quilometragemInicio;
	
	@Column(name = "quilometragem_fim")
	private Integer quilometragemFim;

}
