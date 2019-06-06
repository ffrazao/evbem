package br.gov.df.emater.repositorio_principal.entidade.pessoa;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import br.gov.df.emater.repositorio_principal.entidade.EntidadeBase;


/**
 * The persistent class for the pessoa_foto database table.
 * 
 */
@Entity
@Table(catalog = "pessoa", name="pessoa_foto")
@NamedQuery(name="PessoaFoto.findAll", query="SELECT p FROM PessoaFoto p")
public class PessoaFoto extends EntidadeBase implements Serializable {
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

	@Column(name="foto_id")
	private int fotoId;

	private int ordem;

	@Column(name="pessoa_id")
	private int pessoaId;

	private String principal;

	private String tipo;

	public PessoaFoto() {
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

	public int getFotoId() {
		return this.fotoId;
	}

	public void setFotoId(int fotoId) {
		this.fotoId = fotoId;
	}

	public int getOrdem() {
		return this.ordem;
	}

	public void setOrdem(int ordem) {
		this.ordem = ordem;
	}

	public int getPessoaId() {
		return this.pessoaId;
	}

	public void setPessoaId(int pessoaId) {
		this.pessoaId = pessoaId;
	}

	public String getPrincipal() {
		return this.principal;
	}

	public void setPrincipal(String principal) {
		this.principal = principal;
	}

	public String getTipo() {
		return this.tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

}