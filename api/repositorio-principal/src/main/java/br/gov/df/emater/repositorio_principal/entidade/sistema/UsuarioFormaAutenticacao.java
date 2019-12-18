package br.gov.df.emater.repositorio_principal.entidade.sistema;

import java.util.Map;

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

import br.gov.df.emater.repositorio_principal.conversor.JsonHashMapConverter;
import br.gov.df.emater.repositorio_principal.dominio.Confirmacao;
import br.gov.df.emater.repositorio_principal.entidade.base.Ativavel;
import br.gov.df.emater.repositorio_principal.entidade.base.EntidadeBase;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * The persistent class for the usuario_forma_autenticacao database table.
 * 
 */
@Entity
@Table(catalog = "sistema", name = "usuario_forma_autenticacao")
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@SuppressWarnings("serial")
public class UsuarioFormaAutenticacao extends EntidadeBase implements Ativavel {

	@Enumerated(EnumType.STRING)
	private Confirmacao ativo;

	@ManyToOne
	@JoinColumn(name = "forma_autenticacao_id")
	// @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,
	// property = "id")
	// @JsonIdentityReference(alwaysAsId = false)
	private FormaAutenticacao formaAutenticacao;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@ManyToOne
	@JoinColumn(name = "usuario_id")
	// @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,
	// property = "id")
	// @JsonIdentityReference(alwaysAsId = false)
	private Usuario usuario;

	@Lob
	@Convert(converter = JsonHashMapConverter.class)
	private Map<String, Object> valor;

	public UsuarioFormaAutenticacao(Integer valor) {
		super(valor);
	}

	@Override
	public UsuarioFormaAutenticacao infoBasica() {
		UsuarioFormaAutenticacao result = (UsuarioFormaAutenticacao) super.infoBasica();
		if (result.getUsuario() != null) {
			result.setUsuario(result.getUsuario().infoBasica());
		}
		if (result.getFormaAutenticacao() != null) {
			result.setFormaAutenticacao(result.getFormaAutenticacao().infoBasica());
		}
		return result;
	}

}