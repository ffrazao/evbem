package br.gov.df.emater.repositorio_principal.entidade.veiculo;

import java.math.BigDecimal;
import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.data.geo.Point;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import br.gov.df.emater.repositorio_principal.conversor.PointJsonDeserializer;
import br.gov.df.emater.repositorio_principal.conversor.PointJsonSerializer;
import br.gov.df.emater.repositorio_principal.entidade.base.EntidadeBase;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * The persistent class for the Infracao database table.
 * 
 */
@Entity
@Table(catalog = "veiculo")
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@SuppressWarnings("serial")
public class Rota extends EntidadeBase {

	private BigDecimal altitude;

	private BigDecimal direcao;

	@Id
	private Integer id;

	@Temporal(TemporalType.TIMESTAMP)
	private Calendar momento;

	@JsonSerialize(using = PointJsonSerializer.class)
	@JsonDeserialize(using = PointJsonDeserializer.class)
	private Point ponto;

	private BigDecimal precisao;

	@Column(name = "precisao_altitude")
	private BigDecimal precisaoAltitude;

	private BigDecimal velocidade;

	@ManyToOne
	@JoinColumn(name = "viagem_id")
	// @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,
	// property = "id")
	// @JsonIdentityReference(alwaysAsId = false)
	private Viagem viagem;

	public Rota(Integer valor) {
		super(valor);
	}

	@Override
	public Rota infoBasica() {
		Rota result = (Rota) super.infoBasica();
		if (result.getViagem() != null) {
			result.setViagem(result.getViagem().infoBasica());
		}
		return result;
	}

}
