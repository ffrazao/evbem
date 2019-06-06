package br.gov.df.emater.repositorio_principal.entidade.pessoa;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import br.gov.df.emater.repositorio_principal.entidade.EntidadeBase;


/**
 * The persistent class for the grupo_social database table.
 * 
 */
@Entity
@Table(catalog = "pessoa", name="grupo_social")
@NamedQuery(name="GrupoSocial.findAll", query="SELECT g FROM GrupoSocial g")
public class GrupoSocial extends EntidadeBase implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;

	private String administrado;

	private String dinamico;

	@Temporal(TemporalType.TIMESTAMP)
	private Date inicio;

	@Lob
	private String sql;

	@Temporal(TemporalType.TIMESTAMP)
	private Date termino;

	public GrupoSocial() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAdministrado() {
		return this.administrado;
	}

	public void setAdministrado(String administrado) {
		this.administrado = administrado;
	}

	public String getDinamico() {
		return this.dinamico;
	}

	public void setDinamico(String dinamico) {
		this.dinamico = dinamico;
	}

	public Date getInicio() {
		return this.inicio;
	}

	public void setInicio(Date inicio) {
		this.inicio = inicio;
	}

	public String getSql() {
		return this.sql;
	}

	public void setSql(String sql) {
		this.sql = sql;
	}

	public Date getTermino() {
		return this.termino;
	}

	public void setTermino(Date termino) {
		this.termino = termino;
	}

}