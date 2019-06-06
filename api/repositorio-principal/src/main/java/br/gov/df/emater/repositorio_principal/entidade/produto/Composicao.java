package br.gov.df.emater.repositorio_principal.entidade.produto;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import br.gov.df.emater.repositorio_principal.entidade.EntidadeBase;


/**
 * The persistent class for the composicao database table.
 * 
 */
@Entity
@Table(catalog = "produto")
@NamedQuery(name="Composicao.findAll", query="SELECT c FROM Composicao c")
public class Composicao extends EntidadeBase implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;

	@Column(name="atualizado_em")
	private Timestamp atualizadoEm;

	@Column(name="atualizado_usuario_id")
	private int atualizadoUsuarioId;

	@Column(name="criado_em")
	private Timestamp criadoEm;

	@Column(name="criado_usuario_id")
	private int criadoUsuarioId;

	@Temporal(TemporalType.TIMESTAMP)
	private Date inicio;

	@Column(name="pai_id")
	private int paiId;

	@Column(name="produto_id")
	private int produtoId;

	@Temporal(TemporalType.TIMESTAMP)
	private Date termino;

	public Composicao() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
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

	public Date getInicio() {
		return this.inicio;
	}

	public void setInicio(Date inicio) {
		this.inicio = inicio;
	}

	public int getPaiId() {
		return this.paiId;
	}

	public void setPaiId(int paiId) {
		this.paiId = paiId;
	}

	public int getProdutoId() {
		return this.produtoId;
	}

	public void setProdutoId(int produtoId) {
		this.produtoId = produtoId;
	}

	public Date getTermino() {
		return this.termino;
	}

	public void setTermino(Date termino) {
		this.termino = termino;
	}

}