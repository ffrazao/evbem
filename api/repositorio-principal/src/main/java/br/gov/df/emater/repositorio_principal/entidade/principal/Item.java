package br.gov.df.emater.repositorio_principal.entidade.principal;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import br.gov.df.emater.repositorio_principal.entidade.EntidadeBase;


/**
 * The persistent class for the item database table.
 * 
 */
@Entity
@Table(catalog = "principal")
@NamedQuery(name="Item.findAll", query="SELECT i FROM Item i")
public class Item extends EntidadeBase implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;

	private String ativo;

	@Column(name="atualizado_em")
	private Timestamp atualizadoEm;

	@Column(name="atualizado_usuario_id")
	private int atualizadoUsuarioId;

	@Column(name="criado_em")
	private Timestamp criadoEm;

	@Column(name="criado_usuario_id")
	private int criadoUsuarioId;

	@Lob
	private String observacao;

	private String tipo;

	//bi-directional one-to-one association to Pessoa
	@OneToOne(mappedBy="item")
	private Pessoa pessoa;

	//bi-directional one-to-one association to Produto
	@OneToOne(mappedBy="item")
	private Produto produto;

	//bi-directional one-to-one association to Servico
	@OneToOne(mappedBy="item")
	private Servico servico;

	public Item() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAtivo() {
		return this.ativo;
	}

	public void setAtivo(String ativo) {
		this.ativo = ativo;
	}

	public Timestamp getAtualizadoEm() {
		return this.atualizadoEm;
	}

	public void setAtualizadoEm(Timestamp atualizadoEm) {
		this.atualizadoEm = atualizadoEm;
	}

	public int getAtualizadoUsuarioId() {
		return this.atualizadoUsuarioId;
	}

	public void setAtualizadoUsuarioId(int atualizadoUsuarioId) {
		this.atualizadoUsuarioId = atualizadoUsuarioId;
	}

	public Timestamp getCriadoEm() {
		return this.criadoEm;
	}

	public void setCriadoEm(Timestamp criadoEm) {
		this.criadoEm = criadoEm;
	}

	public int getCriadoUsuarioId() {
		return this.criadoUsuarioId;
	}

	public void setCriadoUsuarioId(int criadoUsuarioId) {
		this.criadoUsuarioId = criadoUsuarioId;
	}

	public String getObservacao() {
		return this.observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

	public String getTipo() {
		return this.tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public Pessoa getPessoa() {
		return this.pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}

	public Produto getProduto() {
		return this.produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public Servico getServico() {
		return this.servico;
	}

	public void setServico(Servico servico) {
		this.servico = servico;
	}

}