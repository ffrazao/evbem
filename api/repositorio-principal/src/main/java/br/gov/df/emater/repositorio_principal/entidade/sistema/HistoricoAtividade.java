package br.gov.df.emater.repositorio_principal.entidade.sistema;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import br.gov.df.emater.repositorio_principal.entidade.EntidadeBase;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * The persistent class for the historico_atividade database table.
 * 
 */
@Entity
@Table(catalog = "sistema", name = "historico_atividade")
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class HistoricoAtividade extends EntidadeBase implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name = "acao_id")
	private int acaoId;

	private int duracao;

	@Column(name = "funcionalidade_id")
	private int funcionalidadeId;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	private Timestamp inicio;

	@Lob
	private String mensagem;

	@Column(name = "modulo_id")
	private int moduloId;

	private String requisicao;

	private String resposta;

	private int status;

	private Timestamp termino;

	// bi-directional many-to-one association to Token
	@ManyToOne
	private Token token;

}