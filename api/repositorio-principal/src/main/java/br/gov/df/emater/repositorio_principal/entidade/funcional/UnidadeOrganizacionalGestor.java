package br.gov.df.emater.repositorio_principal.entidade.funcional;

import java.util.Calendar;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import br.gov.df.emater.repositorio_principal.dominio.Confirmacao;
import br.gov.df.emater.repositorio_principal.entidade.base.EntidadeBase;
import br.gov.df.emater.repositorio_principal.entidade.base.Temporalizavel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * The persistent class for the UnidadeOrganizacionalGestor database table.
 * 
 */
@Entity
@Table(catalog = "funcional")
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@SuppressWarnings("serial")
public class UnidadeOrganizacionalGestor extends EntidadeBase implements Temporalizavel {

	@ManyToOne
	@JoinColumn(name = "empregado_id")
	// @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,
	// property = "id")
	// @JsonIdentityReference(alwaysAsId = false)
	private Empregado empregado;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Temporal(TemporalType.TIMESTAMP)
	private Calendar inicio;

	@Enumerated(EnumType.STRING)
	private Confirmacao substituto;

	@Temporal(TemporalType.TIMESTAMP)
	private Calendar termino;

	@ManyToOne
	@JoinColumn(name = "unidade_organizacional_id")
	// @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,
	// property = "id")
	// @JsonIdentityReference(alwaysAsId = false)
	private UnidadeOrganizacional unidadeOrganizacional;

	public UnidadeOrganizacionalGestor(Integer valor) {
		super(valor);
	}

	@Override
	public UnidadeOrganizacionalGestor infoBasica() {
		UnidadeOrganizacionalGestor result = (UnidadeOrganizacionalGestor) super.infoBasica();
		if (result.getEmpregado() != null) {
			result.setEmpregado(result.getEmpregado().infoBasica());
		}
		if (result.getUnidadeOrganizacional() != null) {
			result.setUnidadeOrganizacional(result.getUnidadeOrganizacional().infoBasica());
		}
		return result;
	}

}
