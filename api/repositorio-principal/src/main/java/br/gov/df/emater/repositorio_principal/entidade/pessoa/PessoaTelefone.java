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
 * The persistent class for the pessoa_telefone database table.
 * 
 */
@Entity
@Table(catalog = "pessoa", name="pessoa_telefone")
@NamedQuery(name="PessoaTelefone.findAll", query="SELECT p FROM PessoaTelefone p")
public class PessoaTelefone extends EntidadeBase implements Serializable {
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

	private int ordem;

	@Column(name="pessoa_id")
	private int pessoaId;

	private String principal;

	@Column(name="telefone_id")
	private int telefoneId;

	private String tipo;

	public PessoaTelefone() {
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

	public int getTelefoneId() {
		return this.telefoneId;
	}

	public void setTelefoneId(int telefoneId) {
		this.telefoneId = telefoneId;
	}

	public String getTipo() {
		return this.tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

}