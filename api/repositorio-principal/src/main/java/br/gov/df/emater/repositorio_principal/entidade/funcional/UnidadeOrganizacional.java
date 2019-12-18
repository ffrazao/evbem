package br.gov.df.emater.repositorio_principal.entidade.funcional;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import br.gov.df.emater.repositorio_principal.entidade.pessoa.GrupoSocial;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * The persistent class for the UnidadeOrganizacional database table.
 * 
 */
@Entity
@Table(catalog = "funcional")
@PrimaryKeyJoinColumn(name = "id", referencedColumnName = "id")
@DiscriminatorValue("UnidadeOrganizacional")
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@SuppressWarnings("serial")
public class UnidadeOrganizacional extends GrupoSocial {

	@ManyToOne
	@JoinColumn(name = "empregador_id")
	// @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,
	// property = "id")
	// @JsonIdentityReference(alwaysAsId = false)
	private Empregador empregador;

	@ManyToOne
	@JoinColumn(name = "unidade_organizacional_tipo_id")
	// @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,
	// property = "id")
	// @JsonIdentityReference(alwaysAsId = false)
	private UnidadeOrganizacionalTipo unidadeOrganizacionalTipo;

	public UnidadeOrganizacional(Integer valor) {
		super(valor);
	}

	@Override
	public UnidadeOrganizacional infoBasica() {
		UnidadeOrganizacional result = (UnidadeOrganizacional) super.infoBasica();
		if (result.getEmpregador() != null) {
			result.setEmpregador(result.getEmpregador().infoBasica());
		}
		if (result.getUnidadeOrganizacionalTipo() != null) {
			result.setUnidadeOrganizacionalTipo(result.getUnidadeOrganizacionalTipo().infoBasica());
		}
		return result;
	}

}
