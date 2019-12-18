package br.gov.df.emater.repositorio_principal.entidade.evento;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.data.geo.Point;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import br.gov.df.emater.repositorio_principal.conversor.PointJsonDeserializer;
import br.gov.df.emater.repositorio_principal.conversor.PointJsonSerializer;
import br.gov.df.emater.repositorio_principal.conversor.TimestampJsonDeserializer;
import br.gov.df.emater.repositorio_principal.conversor.TimestampJsonSerializer;
import br.gov.df.emater.repositorio_principal.dominio.Confirmacao;
import br.gov.df.emater.repositorio_principal.entidade.base.EntidadeBase;
import br.gov.df.emater.repositorio_principal.entidade.base.Pai;
import br.gov.df.emater.repositorio_principal.entidade.base.Temporalizavel;
import br.gov.df.emater.repositorio_principal.entidade.principal.Recurso;
import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * The persistent class for the Evento database table.
 * 
 */
@Inheritance(strategy = InheritanceType.JOINED)
@Entity
@Table(catalog = "evento")
@JsonTypeInfo(use = JsonTypeInfo.Id.CLASS, include = JsonTypeInfo.As.PROPERTY, property = "@class")
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@SuppressWarnings("serial")
public class Evento extends EntidadeBase implements Temporalizavel, Pai<Evento> {

	@Lob
	private String descricao;

	@ManyToOne
	@JoinColumn(name = "evento_tipo_id ")
	// @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,
	// property = "id")
	// @JsonIdentityReference(alwaysAsId = false)
	private EventoTipo eventoTipo;

	@OneToMany(mappedBy = "evento", fetch = FetchType.LAZY)
	private List<Evidencia> evidenciaList = new ArrayList<>();

	@OneToMany(mappedBy = "pai", fetch = FetchType.LAZY)
	@Setter(AccessLevel.PRIVATE)
	private List<Evento> filhos = new ArrayList<>();

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
	@JsonSerialize(using = TimestampJsonSerializer.class)
	@JsonDeserialize(using = TimestampJsonDeserializer.class)
	private Calendar inicio;

	@JsonSerialize(using = PointJsonSerializer.class)
	@JsonDeserialize(using = PointJsonDeserializer.class)
	private Point local;

	@Column(name = "local_descricao")
	private String localDescricao;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "pai_id")
	// @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,
	// property = "id")
	// @JsonIdentityReference(alwaysAsId = false)
	private Evento pai;

	@Enumerated(EnumType.STRING)
	private Confirmacao planejamento;

	@ManyToOne
	@JoinColumn(name = "recurso_id ")
	// @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,
	// property = "id")
	// @JsonIdentityReference(alwaysAsId = false)
	private Recurso recurso;

	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
	@JsonSerialize(using = TimestampJsonSerializer.class)
	@JsonDeserialize(using = TimestampJsonDeserializer.class)
	private Calendar termino;

	public Evento(Integer valor) {
		super(valor);
	}

	@Override
	public Evento infoBasica() {
		Evento result = (Evento) super.infoBasica();
		if (result.getEventoTipo() != null) {
			result.setEventoTipo(result.getEventoTipo().infoBasica());
		}
		if (result.getEvidenciaList() != null) {
			result.setEvidenciaList(
					result.getEvidenciaList().stream().map(e -> e.infoBasica()).collect(Collectors.toList()));
		}
		if (result.getFilhos() != null) {
			result.setFilhos(result.getFilhos().stream().map(e -> new Evento(e.getId())).collect(Collectors.toList()));
		}
		if (result.getPai() != null) {
			result.setPai(new Evento(result.getPai().getId()));
		}
		if (result.getRecurso() != null) {
			result.setRecurso(result.getRecurso().infoBasica());
		}
		return result;
	}

}