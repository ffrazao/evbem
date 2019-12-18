package br.gov.df.emater.repositorio_principal.entidade.sistema;

import java.util.Calendar;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Convert;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import br.gov.df.emater.repositorio_principal.conversor.JsonHashMapConverter;
import br.gov.df.emater.repositorio_principal.dominio.TokenTipo;
import br.gov.df.emater.repositorio_principal.entidade.base.EntidadeBase;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * The persistent class for the token database table.
 * 
 */
@Entity
@Table(catalog = "sistema")
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@SuppressWarnings("serial")
public class Token extends EntidadeBase {

	@Column(name = "criado_em")
	@Temporal(TemporalType.TIMESTAMP)
	private Calendar criadoEm;

	@Lob
	@Convert(converter = JsonHashMapConverter.class)
	private Map<String, Object> detalhe;

	@Column(name = "expira_em")
	private Integer expiraEm;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "invalidado_em")
	@Temporal(TemporalType.TIMESTAMP)
	private Calendar invalidadoEm;

	@Enumerated(EnumType.STRING)
	private TokenTipo tipo;

	@Lob
	private String token;

	@ManyToOne
	@JoinColumn(name = "usuario_id")
	// @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,
	// property = "id")
	// @JsonIdentityReference(alwaysAsId = false)
	private Usuario usuario;

	public Token(Integer valor) {
		super(valor);
	}

	@Override
	public Token infoBasica() {
		Token result = (Token) super.infoBasica();
		if (result.getUsuario() != null) {
			result.setUsuario(result.getUsuario().infoBasica());
		}
		return result;
	}

}