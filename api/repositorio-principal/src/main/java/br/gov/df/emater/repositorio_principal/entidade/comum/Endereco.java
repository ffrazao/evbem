package br.gov.df.emater.repositorio_principal.entidade.comum;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * The persistent class for the endereco database table.
 * 
 */
@Entity
@Table(catalog = "comum")
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@SuppressWarnings("serial")
public class Endereco extends ReferenciaEspacial {

	private String cep;

	private String complemento;

	public Endereco(Integer valor) {
		super(valor);
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@ManyToOne
	@JoinColumn(name = "localizacao_id")
	// @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,
	// property = "id")
	// @JsonIdentityReference(alwaysAsId = false)
	private Localizacao localizacao;

	private String logradouro;

	private String numero;

	@Lob
	@Column(name = "ponto_referencia")
	private String pontoReferencia;

	@Override
	public Endereco infoBasica() {
		Endereco result = (Endereco) super.infoBasica();
		result.setLocalizacao((Localizacao) this.getLocalizacao().infoBasica());
		return result;
	}
}