package br.gov.df.emater.repositorio_principal.entidade.comum;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

import org.springframework.data.geo.Polygon;

import com.fasterxml.jackson.annotation.JsonTypeInfo;

import br.gov.df.emater.repositorio_principal.dominio.ReferenciaEspacialTipo;
import br.gov.df.emater.repositorio_principal.entidade.base.EntidadeBase;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * The persistent class for the referencia_espacial database table.
 * 
 */
@Inheritance(strategy = InheritanceType.JOINED)
@Entity
@Table(catalog = "comum", name = "referencia_espacial")
@JsonTypeInfo(use = JsonTypeInfo.Id.CLASS, include = JsonTypeInfo.As.PROPERTY, property = "@class")
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@SuppressWarnings("serial")
public class ReferenciaEspacial extends EntidadeBase {

	private Polygon area;

	public ReferenciaEspacial(Integer valor) {
		super(valor);
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Enumerated(EnumType.STRING)
	@Column(name = "referencia_espacial_tipo")
	private ReferenciaEspacialTipo referenciaEspacialTipo;

	@Override
	public ReferenciaEspacial infoBasica() {
		return (ReferenciaEspacial) super.infoBasica();
	}

}