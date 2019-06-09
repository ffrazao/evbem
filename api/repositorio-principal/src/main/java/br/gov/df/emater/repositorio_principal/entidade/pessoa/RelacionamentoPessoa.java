package br.gov.df.emater.repositorio_principal.entidade.pessoa;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import br.gov.df.emater.repositorio_principal.entidade.EntidadeBase;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * The persistent class for the relacionamento_pessoa database table.
 * 
 */
@Entity
@Table(catalog = "pessoa", name = "relacionamento_pessoa")
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class RelacionamentoPessoa extends EntidadeBase implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	@Column(name = "pessoa_id")
	private int pessoaId;

	// bi-directional many-to-one association to Relacionamento
	@ManyToOne
	private Relacionamento relacionamento;

	// bi-directional many-to-one association to RelacionamentoFuncao
	@ManyToOne
	@JoinColumn(name = "relacionamento_funcao_id")
	private RelacionamentoFuncao relacionamentoFuncao;

}