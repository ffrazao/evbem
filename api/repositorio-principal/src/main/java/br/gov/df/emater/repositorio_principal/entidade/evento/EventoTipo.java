package br.gov.df.emater.repositorio_principal.entidade.evento;

import static br.gov.df.emater.comum.Constantes.JUNCAO_CAMPO_SET;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.apache.commons.lang.StringUtils;

import br.gov.df.emater.repositorio_principal.dominio.Confirmacao;
import br.gov.df.emater.repositorio_principal.dominio.principal.RecursoTipo;
import br.gov.df.emater.repositorio_principal.entidade.base.EntidadeBase;
import br.gov.df.emater.repositorio_principal.entidade.base.PaiNomeavelCodificavel;
import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * The persistent class for the Tipo database table.
 * 
 */
@Entity
@Table(catalog = "evento", name = "evento_tipo")
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@SuppressWarnings("serial")
public class EventoTipo extends EntidadeBase implements PaiNomeavelCodificavel<EventoTipo> {

	private String codigo;

	@OneToMany(mappedBy = "pai", fetch = FetchType.LAZY)
	@Setter(AccessLevel.PRIVATE)
	private List<EventoTipo> filhos = new ArrayList<>();

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	private String nome;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "pai_id")
	// @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,
	// property = "id")
	// @JsonIdentityReference(alwaysAsId = false)
	private EventoTipo pai;

	@Column(name = "recurso_tipo")
	private String recursoTipo;

	@Enumerated(EnumType.STRING)
	@Column(name = "uso_sistema")
	private Confirmacao usoSistema;

	public EventoTipo(Integer valor) {
		super(valor);
	}

	public Set<RecursoTipo> getRecursoTipo() {
		return (StringUtils.isBlank(this.recursoTipo)) ? Collections.emptySet()
				: Collections.unmodifiableSet(Arrays.stream(this.recursoTipo.split(JUNCAO_CAMPO_SET))
						.map(c -> RecursoTipo.valueOf(c)).sorted().collect(Collectors.toSet()));
	}

	@Override
	public EventoTipo infoBasica() {
		EventoTipo result = (EventoTipo) copy();
		result.setRecursoTipo(this.getRecursoTipo());
		result.setUsoSistema(this.getUsoSistema());
		return result;
	}

	public void setRecursoTipo(Set<RecursoTipo> recursoTipo) {
		this.recursoTipo = (recursoTipo == null) ? null
				: recursoTipo.stream().map(c -> c.toString()).sorted().collect(Collectors.joining(JUNCAO_CAMPO_SET));
	}

}