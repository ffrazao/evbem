package br.gov.df.emater.repositorio_principal.entidade.pessoa;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import br.gov.df.emater.repositorio_principal.entidade.base.EntidadeBase;
import br.gov.df.emater.repositorio_principal.entidade.principal.Pessoa;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * The persistent class for the pessoa_relacionamento database table.
 * 
 */
@Entity
@Table(catalog = "pessoa", name = "pessoa_relacionamento")
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@SuppressWarnings("serial")
public class PessoaRelacionamento extends EntidadeBase {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
	@JoinColumn(name = "pessoa_id")
	// @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,
	// property = "id")
	// @JsonIdentityReference(alwaysAsId = false)
	private Pessoa pessoa;

	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "relacionamento_id")
	// @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,
	// property = "id")
	// @JsonIdentityReference(alwaysAsId = false)
	private Relacionamento relacionamento;

	@ManyToOne
	@JoinColumn(name = "relacionamento_funcao_id")
	// @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,
	// property = "id")
	// @JsonIdentityReference(alwaysAsId = false)
	private RelacionamentoFuncao relacionamentoFuncao;

	public PessoaRelacionamento(Integer valor) {
		super(valor);
	}

	@Override
	public PessoaRelacionamento infoBasica() {
		PessoaRelacionamento result = (PessoaRelacionamento) super.infoBasica();
		if (result.getPessoa() != null) {
			result.setPessoa(new Pessoa(result.getPessoa().getId()));
		}
		if (result.getRelacionamento() != null) {
			result.setRelacionamento(result.getRelacionamento().infoBasica());
		}
		return result;
	}

}