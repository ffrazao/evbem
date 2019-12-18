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

import br.gov.df.emater.repositorio_principal.dominio.UnidadeOrganizacionalHierarquiaTipo;
import br.gov.df.emater.repositorio_principal.entidade.base.EntidadeBase;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * The persistent class for the UnidadeOrganizacionalHierarquia database table.
 * 
 */
@Entity
@Table(catalog = "funcional")
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@SuppressWarnings("serial")
public class UnidadeOrganizacionalHierarquia extends EntidadeBase {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Enumerated(EnumType.STRING)
	private UnidadeOrganizacionalHierarquiaTipo tipo;

	@ManyToOne
	@JoinColumn(name = "unidade_organizacional_id")
	// @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,
	// property = "id")
	// @JsonIdentityReference(alwaysAsId = false)
	private UnidadeOrganizacional unidadeOrganizacional;

	@ManyToOne
	@JoinColumn(name = "unidade_organizacional_principal_id")
	// @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,
	// property = "id")
	// @JsonIdentityReference(alwaysAsId = false)
	private UnidadeOrganizacional unidadeOrganizacionalPrincipal;

	public UnidadeOrganizacionalHierarquia(Integer valor) {
		super(valor);
	}

	@Override
	public UnidadeOrganizacionalHierarquia infoBasica() {
		UnidadeOrganizacionalHierarquia result = (UnidadeOrganizacionalHierarquia) super.infoBasica();
		if (result.getUnidadeOrganizacional() != null) {
			result.setUnidadeOrganizacional(result.getUnidadeOrganizacional().infoBasica());
		}
		if (result.getUnidadeOrganizacionalPrincipal() != null) {
			result.setUnidadeOrganizacionalPrincipal(result.getUnidadeOrganizacionalPrincipal().infoBasica());
		}

		return result;
	}

}
