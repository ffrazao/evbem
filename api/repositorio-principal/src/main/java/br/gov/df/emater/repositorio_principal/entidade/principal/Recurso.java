package br.gov.df.emater.repositorio_principal.entidade.principal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

import br.gov.df.emater.repositorio_principal.dominio.Confirmacao;
import br.gov.df.emater.repositorio_principal.dominio.principal.RecursoTipo;
import br.gov.df.emater.repositorio_principal.entidade.base.Ativavel;
import br.gov.df.emater.repositorio_principal.entidade.base.EntidadeBase;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * The persistent class for the item database table.
 * 
 */
@Entity
@Table(catalog = "principal")
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@SuppressWarnings("serial")
public class Recurso extends EntidadeBase implements Ativavel {

	@Enumerated(EnumType.STRING)
	private Confirmacao ativo = Confirmacao.S;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Lob
	private String observacao;

	@Enumerated(EnumType.STRING)
	@Column(name = "recurso_tipo")
	private RecursoTipo recursoTipo;

	public Recurso(Integer valor) {
		super(valor);
	}

	@Override
	public Recurso infoBasica() {
		Recurso result = (Recurso) super.infoBasica();
		return result;
	}

}