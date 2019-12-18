package br.gov.df.emater.repositorio_principal.entidade.funcional;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import br.gov.df.emater.repositorio_principal.dominio.Confirmacao;
import br.gov.df.emater.repositorio_principal.entidade.base.Ativavel;
import br.gov.df.emater.repositorio_principal.entidade.base.EntidadeBase;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * The persistent class for the EmpregadorCargo database table.
 * 
 */
@Entity
@Table(catalog = "funcional")
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@SuppressWarnings("serial")
public class EmpregadorCargo extends EntidadeBase implements Ativavel {

	@Enumerated(EnumType.STRING)
	private Confirmacao ativo;

	@ManyToOne
	@JoinColumn(name = "cargo_id")
	// @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,
	// property = "id")
	// @JsonIdentityReference(alwaysAsId = false)
	private Cargo cargo;

	@ManyToOne
	@JoinColumn(name = "empregador_id")
	// @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,
	// property = "id")
	// @JsonIdentityReference(alwaysAsId = false)
	private Empregador empregador;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	public EmpregadorCargo(Integer valor) {
		super(valor);
	}

	@Override
	public EmpregadorCargo infoBasica() {
		EmpregadorCargo result = (EmpregadorCargo) super.infoBasica();
		if (result.getCargo() != null) {
			result.setCargo(result.getCargo().infoBasica());
		}
		if (result.getEmpregador() != null) {
			result.setEmpregador(result.getEmpregador().infoBasica());
		}
		return result;
	}

}
