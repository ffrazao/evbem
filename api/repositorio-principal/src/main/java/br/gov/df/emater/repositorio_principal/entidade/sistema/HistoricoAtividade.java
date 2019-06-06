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
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import br.gov.df.emater.repositorio_principal.entidade.EntidadeBase;


/**
 * The persistent class for the historico_atividade database table.
 * 
 */
@Entity
@Table(catalog = "sistema", name="historico_atividade")
@NamedQuery(name="HistoricoAtividade.findAll", query="SELECT h FROM HistoricoAtividade h")
public class HistoricoAtividade extends EntidadeBase implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;

	@Column(name="acao_id")
	private int acaoId;

	private int duracao;

	@Column(name="funcionalidade_id")
	private int funcionalidadeId;

	private Timestamp inicio;

	@Lob
	private String mensagem;

	@Column(name="modulo_id")
	private int moduloId;

	private String requisicao;

	private String resposta;

	private int status;

	private Timestamp termino;

	//bi-directional many-to-one association to Token
	@ManyToOne
	private Token token;

	public HistoricoAtividade() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getAcaoId() {
		return this.acaoId;
	}

	public void setAcaoId(int acaoId) {
		this.acaoId = acaoId;
	}

	public int getDuracao() {
		return this.duracao;
	}

	public void setDuracao(int duracao) {
		this.duracao = duracao;
	}

	public int getFuncionalidadeId() {
		return this.funcionalidadeId;
	}

	public void setFuncionalidadeId(int funcionalidadeId) {
		this.funcionalidadeId = funcionalidadeId;
	}

	public Timestamp getInicio() {
		return this.inicio;
	}

	public void setInicio(Timestamp inicio) {
		this.inicio = inicio;
	}

	public String getMensagem() {
		return this.mensagem;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}

	public int getModuloId() {
		return this.moduloId;
	}

	public void setModuloId(int moduloId) {
		this.moduloId = moduloId;
	}

	public String getRequisicao() {
		return this.requisicao;
	}

	public void setRequisicao(String requisicao) {
		this.requisicao = requisicao;
	}

	public String getResposta() {
		return this.resposta;
	}

	public void setResposta(String resposta) {
		this.resposta = resposta;
	}

	public int getStatus() {
		return this.status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public Timestamp getTermino() {
		return this.termino;
	}

	public void setTermino(Timestamp termino) {
		this.termino = termino;
	}

	public Token getToken() {
		return this.token;
	}

	public void setToken(Token token) {
		this.token = token;
	}

}