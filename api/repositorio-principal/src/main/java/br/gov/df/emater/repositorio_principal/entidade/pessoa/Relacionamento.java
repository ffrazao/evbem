package br.gov.df.emater.repositorio_principal.entidade.pessoa;

import java.util.Calendar;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonTypeInfo;

import br.gov.df.emater.repositorio_principal.dominio.Confirmacao;
import br.gov.df.emater.repositorio_principal.entidade.base.Ativavel;
import br.gov.df.emater.repositorio_principal.entidade.base.EntidadeBase;
import br.gov.df.emater.repositorio_principal.entidade.base.Temporalizavel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * The persistent class for the relacionamento database table.
 * 
 */
@Inheritance(strategy = InheritanceType.JOINED)
@Entity
@Table(catalog = "pessoa")
@JsonTypeInfo(use = JsonTypeInfo.Id.CLASS, include = JsonTypeInfo.As.PROPERTY, property = "@class")
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@SuppressWarnings("serial")
public class Relacionamento extends EntidadeBase implements Ativavel, Temporalizavel {

	@Enumerated(EnumType.STRING)
	private Confirmacao ativo;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Temporal(TemporalType.TIMESTAMP)
	private Calendar inicio;

	@ManyToOne
	@JoinColumn(name = "relacionamento_tipo_id")
	// @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,
	// property = "id")
	// @JsonIdentityReference(alwaysAsId = false)
	private RelacionamentoTipo relacionamentoTipo;

	@Temporal(TemporalType.TIMESTAMP)
	private Calendar termino;

	public Relacionamento(Integer valor) {
		super(valor);
	}

	@Override
	public Relacionamento infoBasica() {
		Relacionamento result = (Relacionamento) super.infoBasica();
		if (result.getRelacionamentoTipo() != null) {
			result.setRelacionamentoTipo(result.getRelacionamentoTipo().infoBasica());
		}
		return result;
	}
}