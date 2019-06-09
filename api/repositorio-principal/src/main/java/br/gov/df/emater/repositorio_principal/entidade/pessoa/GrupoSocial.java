package br.gov.df.emater.repositorio_principal.entidade.pessoa;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import br.gov.df.emater.repositorio_principal.entidade.EntidadeBase;
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
public class GrupoSocial extends EntidadeBase implements Serializable {
	private static final long serialVersionUID = 1L;

	private String administrado;

	private String dinamico;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	@Temporal(TemporalType.TIMESTAMP)
	private Date inicio;

	@Lob
	private String sql;

	@Temporal(TemporalType.TIMESTAMP)
	private Date termino;

}