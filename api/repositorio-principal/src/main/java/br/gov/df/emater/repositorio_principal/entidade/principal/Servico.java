package br.gov.df.emater.repositorio_principal.entidade.principal;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import br.gov.df.emater.repositorio_principal.entidade.base.EntidadeBase;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * The persistent class for the servico database table.
 * 
 */
@Entity
@Table(catalog = "principal")
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@SuppressWarnings("serial")
public class Servico extends EntidadeBase {

	@Id
	private Integer id;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "id")
	@MapsId
	@JsonIgnore
	private Recurso recurso;

	public Servico(Integer valor) {
		super(valor);
	}

	@Override
	public Servico infoBasica() {
		Servico result = (Servico) super.infoBasica();
		if (result.getRecurso() != null) {
			result.setRecurso(result.getRecurso().infoBasica());
		}
		return result;
	}

}