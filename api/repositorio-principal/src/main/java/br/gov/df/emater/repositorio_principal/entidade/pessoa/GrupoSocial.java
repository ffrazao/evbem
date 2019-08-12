package br.gov.df.emater.repositorio_principal.entidade.pessoa;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import br.gov.df.emater.repositorio_principal.dominio.Confirmacao;
import br.gov.df.emater.repositorio_principal.entidade.Temporalizavel;
import br.gov.df.emater.repositorio_principal.entidade.principal.Pessoa;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * The persistent class for the grupo_social database table.
 * 
 */
@Entity
@Table(catalog = "pessoa", name = "grupo_social")
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class GrupoSocial extends Pessoa implements Serializable, Temporalizavel {
	
	private static final long serialVersionUID = 1L;

	@Enumerated(EnumType.STRING)
	private Confirmacao administrado;

	@Enumerated(EnumType.STRING)
	private Confirmacao dinamico;

	@Temporal(TemporalType.TIMESTAMP)
	private Timestamp inicio;

	@Lob
	private String sql;

	@Temporal(TemporalType.TIMESTAMP)
	private Timestamp termino;

}