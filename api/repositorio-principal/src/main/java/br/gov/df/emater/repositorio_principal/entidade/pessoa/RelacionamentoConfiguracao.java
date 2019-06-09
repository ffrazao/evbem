package br.gov.df.emater.repositorio_principal.entidade.pessoa;

import java.io.Serializable;

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
 * The persistent class for the relacionamento_configuracao database table.
 * 
 */
@Entity
@Table(catalog = "pessoa", name = "relacionamento_configuracao")
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class RelacionamentoConfiguracao extends EntidadeBase implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	// bi-directional many-to-one association to RelacionamentoFuncao
	@ManyToOne
	@JoinColumn(name = "relacionador_funcao_id")
	private RelacionamentoFuncao relacionamentoFuncao1;

	// bi-directional many-to-one association to RelacionamentoFuncao
	@ManyToOne
	@JoinColumn(name = "relacionado_funcao_id")
	private RelacionamentoFuncao relacionamentoFuncao2;

	// bi-directional many-to-one association to RelacionamentoTipo
	@ManyToOne
	@JoinColumn(name = "relacionamento_tipo_id")
	private RelacionamentoTipo relacionamentoTipo;

}