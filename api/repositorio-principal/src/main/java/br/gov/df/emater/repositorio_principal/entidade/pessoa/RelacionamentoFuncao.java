package br.gov.df.emater.repositorio_principal.entidade.pessoa;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import br.gov.df.emater.repositorio_principal.entidade.base.EntidadeBase;
import br.gov.df.emater.repositorio_principal.entidade.base.NomeavelCodificavel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * The persistent class for the relacionamento_funcao database table.
 * 
 */
@Entity
@Table(catalog = "pessoa", name = "relacionamento_funcao")
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@SuppressWarnings("serial")
public class RelacionamentoFuncao extends EntidadeBase implements NomeavelCodificavel {

	private String codigo;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	private String nome;

	@Column(name = "nome_se_feminino")
	private String nomeSeFeminino;

	public RelacionamentoFuncao(Integer valor) {
		super(valor);
	}

	@Override
	public RelacionamentoFuncao infoBasica() {
		RelacionamentoFuncao result = (RelacionamentoFuncao) super.infoBasica();
		return result;
	}

}