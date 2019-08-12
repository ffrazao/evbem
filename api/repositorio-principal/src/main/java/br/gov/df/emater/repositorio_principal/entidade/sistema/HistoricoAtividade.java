package br.gov.df.emater.repositorio_principal.entidade.sistema;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Map;

import javax.persistence.Convert;
import javax.persistence.Entity;
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
import br.gov.df.emater.repositorio_principal.entidade.EntidadeBase;
import br.gov.df.emater.repositorio_principal.entidade.Identificavel;
import br.gov.df.emater.repositorio_principal.entidade.Temporalizavel;
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
public class HistoricoAtividade extends EntidadeBase implements Serializable, Identificavel, Temporalizavel {

	private static final long serialVersionUID = 1L;

	@ManyToOne
	@JoinColumn(name = "acao_id")
	private Acao acao;

	private Integer duracao;

	@ManyToOne
	@JoinColumn(name = "funcionalidade_id")
	private Funcionalidade funcionalidade;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	@Temporal(TemporalType.TIMESTAMP)
	private Calendar inicio;

	@Lob
	private String mensagem;

	@ManyToOne
	@JoinColumn(name = "modulo_id")
	private Modulo modulo;

	@Lob
	@Convert(converter = JsonHashMapConverter.class)
	private Map<String, Object> requisicao;

	@Lob
	@Convert(converter = JsonHashMapConverter.class)
	private Map<String, Object> resposta;

	private Integer status;

	@Temporal(TemporalType.TIMESTAMP)
	private Calendar termino;

	@ManyToOne
	@JoinColumn(name = "token_id")
	private Token token;

}