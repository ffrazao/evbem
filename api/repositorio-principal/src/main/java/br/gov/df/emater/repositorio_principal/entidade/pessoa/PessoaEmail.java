package br.gov.df.emater.repositorio_principal.entidade.pessoa;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import br.gov.df.emater.repositorio_principal.dominio.Confirmacao;
import br.gov.df.emater.repositorio_principal.dominio.Visibilidade;
import br.gov.df.emater.repositorio_principal.entidade.base.EntidadeBase;
import br.gov.df.emater.repositorio_principal.entidade.base.Ordenavel;
import br.gov.df.emater.repositorio_principal.entidade.base.Priorizavel;
import br.gov.df.emater.repositorio_principal.entidade.base.Visivel;
import br.gov.df.emater.repositorio_principal.entidade.comum.Email;
import br.gov.df.emater.repositorio_principal.entidade.principal.Pessoa;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * The persistent class for the pessoa_email database table.
 * 
 */
@Entity
@Table(catalog = "pessoa", name = "pessoa_email")
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@SuppressWarnings("serial")
@ToString()
public class PessoaEmail extends EntidadeBase implements Ordenavel, Priorizavel, Visivel {

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "email_id")
	// @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,
	// property = "id")
	// @JsonIdentityReference(alwaysAsId = false)
	private Email email;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	private Integer ordem;

	@ManyToOne
	@JoinColumn(name = "pessoa_id")
	@JsonIgnore
	private Pessoa pessoa;

	@Enumerated(EnumType.STRING)
	private Confirmacao principal;

	@Enumerated(EnumType.STRING)
	private Visibilidade visibilidade = Visibilidade.PUBLICO;

	public PessoaEmail(Integer valor) {
		super(valor);
	}

	@Override
	public PessoaEmail infoBasica() {
		PessoaEmail result = (PessoaEmail) super.infoBasica();
		if (result.getPessoa() != null) {
			result.setPessoa(new Pessoa(result.getPessoa().getId()));
		}
		if (result.getEmail() != null) {
			result.setEmail(result.getEmail().infoBasica());
		}
		return result;
	}

}