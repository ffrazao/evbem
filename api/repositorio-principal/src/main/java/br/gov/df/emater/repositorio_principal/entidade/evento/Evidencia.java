package br.gov.df.emater.repositorio_principal.entidade.evento;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import br.gov.df.emater.repositorio_principal.dominio.evento.EvidenciaTipo;
import br.gov.df.emater.repositorio_principal.entidade.base.EntidadeBase;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * The persistent class for the Evidencia database table.
 * 
 */
@Entity
@Table(catalog = "evento")
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@SuppressWarnings("serial")
public class Evidencia extends EntidadeBase {

	@Lob
	private byte[] conteudo;

	@Lob
	private String descricao;

	@ManyToOne
	@JoinColumn(name = "evento_id ")
	// @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,
	// property = "id")
	// @JsonIdentityReference(alwaysAsId = false)
	private Evento evento;

	@Enumerated(EnumType.STRING)
	@Column(name = "evidencia_tipo")
	private EvidenciaTipo evidenciaTipo;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Override
	public Evidencia infoBasica() {
		Evidencia result = (Evidencia) super.infoBasica();
		if (result.getEvento() != null) {
			result.setEvento(new Evento(result.getEvento().getId()));
		}
		return result;
	}

}