package br.gov.df.emater.repositorio_principal.entidade.evento;

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
import br.gov.df.emater.repositorio_principal.entidade.base.EntidadeBase;
import br.gov.df.emater.repositorio_principal.entidade.principal.Recurso;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * The persistent class for the Participacao database table.
 * 
 */
@Entity
@Table(catalog = "evento")
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@SuppressWarnings("serial")
public class Participacao extends EntidadeBase {

	@ManyToOne
	@JoinColumn(name = "evento_id ")
	// @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,
	// property = "id")
	// @JsonIdentityReference(alwaysAsId = false)
	private Evento evento;

	@ManyToOne
	@JoinColumn(name = "funcao_id ")
	// @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,
	// property = "id")
	// @JsonIdentityReference(alwaysAsId = false)
	private Funcao funcao;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Enumerated(EnumType.STRING)
	private Confirmacao principal;

	@ManyToOne
	@JoinColumn(name = "recurso_id ")
	// @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,
	// property = "id")
	// @JsonIdentityReference(alwaysAsId = false)
	private Recurso recurso;

	public Participacao(Integer valor) {
		super(valor);
	}

	@Override
	public Participacao infoBasica() {
		Participacao result = (Participacao) super.infoBasica();
		if (result.getEvento() != null) {
			result.setEvento(new Evento(result.getEvento().getId()));
		}
		if (result.getFuncao() != null) {
			result.setFuncao(result.getFuncao().infoBasica());
		}
		if (result.getRecurso() != null) {
			result.setRecurso(result.getRecurso().infoBasica());
		}
		return result;
	}

}