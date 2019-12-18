package br.gov.df.emater.repositorio_principal.entidade.pessoa;

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
 * The persistent class for the relacionamento_configuracao database table.
 * 
 */
@Entity
@Table(catalog = "pessoa", name = "relacionamento_configuracao")
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@SuppressWarnings("serial")
public class RelacionamentoConfiguracao extends EntidadeBase {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@ManyToOne
	@JoinColumn(name = "relacionado_funcao_id")
	// @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,
	// property = "id")
	// @JsonIdentityReference(alwaysAsId = false)
	private RelacionamentoFuncao relacionadoFuncao;

	@ManyToOne
	@JoinColumn(name = "relacionador_funcao_id")
	// @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,
	// property = "id")
	// @JsonIdentityReference(alwaysAsId = false)
	private RelacionamentoFuncao relacionadorFuncao;

	@ManyToOne
	@JoinColumn(name = "relacionamento_tipo_id")
	// @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,
	// property = "id")
	// @JsonIdentityReference(alwaysAsId = false)
	private RelacionamentoTipo relacionamentoTipo;

	public RelacionamentoConfiguracao(Integer valor) {
		super(valor);
	}

	@Override
	public RelacionamentoConfiguracao infoBasica() {
		RelacionamentoConfiguracao result = (RelacionamentoConfiguracao) super.infoBasica();
		if (result.getRelacionadoFuncao() != null) {
			result.setRelacionadoFuncao(result.getRelacionadoFuncao().infoBasica());
		}
		if (result.getRelacionadorFuncao() != null) {
			result.setRelacionadorFuncao(result.getRelacionadorFuncao().infoBasica());
		}
		if (result.getRelacionamentoTipo() != null) {
			result.setRelacionamentoTipo(result.getRelacionamentoTipo().infoBasica());
		}
		return result;
	}

}