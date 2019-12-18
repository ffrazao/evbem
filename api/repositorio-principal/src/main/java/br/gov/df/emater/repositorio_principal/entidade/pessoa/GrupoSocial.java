package br.gov.df.emater.repositorio_principal.entidade.pessoa;

import java.io.Serializable;
import java.util.Calendar;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Lob;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import br.gov.df.emater.repositorio_principal.dominio.Confirmacao;
import br.gov.df.emater.repositorio_principal.entidade.base.Temporalizavel;
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
@PrimaryKeyJoinColumn(name = "id", referencedColumnName = "id")
@DiscriminatorValue("GrupoSocial")
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@SuppressWarnings("serial")
public class GrupoSocial extends Pessoa implements Serializable, Temporalizavel {

	@Enumerated(EnumType.STRING)
	private Confirmacao administrado;

	@Enumerated(EnumType.STRING)
	private Confirmacao dinamico;

	@Temporal(TemporalType.TIMESTAMP)
	private Calendar inicio;

	@Lob
	private String sql;

	@Temporal(TemporalType.TIMESTAMP)
	private Calendar termino;

	public GrupoSocial(Integer valor) {
		super(valor);
	}

	@Override
	public GrupoSocial infoBasica() {
		GrupoSocial result = (GrupoSocial) super.infoBasica();
		return result;
	}

}