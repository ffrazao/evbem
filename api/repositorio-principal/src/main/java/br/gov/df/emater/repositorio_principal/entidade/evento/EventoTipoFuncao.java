package br.gov.df.emater.repositorio_principal.entidade.evento;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import br.gov.df.emater.repositorio_principal.entidade.base.EntidadeBase;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * The persistent class for the Tipo database table.
 * 
 */
@Entity
@Table(catalog = "evento", name = "evento_tipo_funcao")
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@SuppressWarnings("serial")
public class EventoTipoFuncao extends EntidadeBase {

	@ManyToOne
	@JoinColumn(name = "evento_tipo_id")
	// @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,
	// property = "id")
	// @JsonIdentityReference(alwaysAsId = false)
	private EventoTipo eventoTipo;

	@ManyToOne
	@JoinColumn(name = "funcao_id")
	// @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,
	// property = "id")
	// @JsonIdentityReference(alwaysAsId = false)
	private Funcao funcao;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	private Integer quantidade;

	public EventoTipoFuncao(Integer valor) {
		super(valor);
	}

	@Override
	public EventoTipoFuncao infoBasica() {
		EventoTipoFuncao result = (EventoTipoFuncao) super.infoBasica();
		if (result.getEventoTipo() != null) {
			result.setEventoTipo(result.getEventoTipo().infoBasica());
		}
		if (result.getFuncao() != null) {
			result.setFuncao(result.getFuncao().infoBasica());
		}
		return result;
	}

}