package br.gov.df.emater.repositorio_principal.entidade.veiculo;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import br.gov.df.emater.repositorio_principal.dominio.VeiculoEventoTipo;
import br.gov.df.emater.repositorio_principal.entidade.evento.Evento;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * The persistent class for the veiculo_evento database table.
 * 
 */
@Entity
@Table(catalog = "veiculo", name = "veiculo_evento")
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class VeiculoEvento extends Evento implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Transient
	private Veiculo veiculo;
	
	@Column(name = "veiculo_evento_tipo")
	private VeiculoEventoTipo veiculoEventoTipo;
	
	@Column(name = "quilometragem")
	private BigDecimal quilometragem;

}
