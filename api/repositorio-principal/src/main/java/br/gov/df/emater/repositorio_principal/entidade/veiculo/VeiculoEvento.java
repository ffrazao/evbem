package br.gov.df.emater.repositorio_principal.entidade.veiculo;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
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
@EqualsAndHashCode(callSuper = true)
@SuppressWarnings("serial")
public class VeiculoEvento extends Evento {

	@Column(name = "quilometragem")
	private BigDecimal quilometragem;

	@Transient
	private Veiculo veiculo;

	@Column(name = "veiculo_evento_tipo")
	@Enumerated(EnumType.STRING)
	private VeiculoEventoTipo veiculoEventoTipo;

	public VeiculoEvento(Integer valor) {
		super(valor);
	}

	@Override
	public VeiculoEvento infoBasica() {
		VeiculoEvento result = (VeiculoEvento) super.infoBasica();
		if (result.getVeiculo() != null) {
			result.setVeiculo(result.getVeiculo().infoBasica());
		}
		return result;
	}

}
