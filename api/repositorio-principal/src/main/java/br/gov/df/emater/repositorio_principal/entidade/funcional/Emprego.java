package br.gov.df.emater.repositorio_principal.entidade.funcional;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import br.gov.df.emater.repositorio_principal.entidade.pessoa.Relacionamento;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * The persistent class for the Emprego database table.
 * 
 */
@Entity
@Table(catalog = "funcional")
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@SuppressWarnings("serial")
public class Emprego extends Relacionamento {

	@ManyToOne
	@JoinColumn(name = "empregador_cargo_id")
	// @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,
	// property = "id")
	// @JsonIdentityReference(alwaysAsId = false)
	private EmpregadorCargo empregadorCargo;

	private String matricula;

	public Emprego(Integer valor) {
		super(valor);
	}

	@Override
	public Emprego infoBasica() {
		Emprego result = (Emprego) super.infoBasica();
		if (result.getEmpregadorCargo() != null) {
			result.setEmpregadorCargo(result.getEmpregadorCargo().infoBasica());
		}
		return result;
	}

}
