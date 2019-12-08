package br.gov.df.emater.repositorio_principal.entidade.pessoa;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import br.gov.df.emater.repositorio_principal.dominio.Confirmacao;
import br.gov.df.emater.repositorio_principal.dominio.Visibilidade;
import br.gov.df.emater.repositorio_principal.entidade.base.EntidadeBase;
import br.gov.df.emater.repositorio_principal.entidade.base.Identificavel;
import br.gov.df.emater.repositorio_principal.entidade.base.Ordenavel;
import br.gov.df.emater.repositorio_principal.entidade.base.Priorizavel;
import br.gov.df.emater.repositorio_principal.entidade.base.Visivel;
import br.gov.df.emater.repositorio_principal.entidade.comum.Telefone;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * The persistent class for the pessoa_telefone database table.
 * 
 */
@Entity
@Table(catalog = "pessoa", name = "pessoa_telefone")
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class PessoaTelefone extends EntidadeBase
		implements Serializable, Identificavel, Ordenavel, Priorizavel, Visivel {

	private static final long serialVersionUID = 1L;

	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "telefone_id")
	@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
	@JsonIdentityReference(alwaysAsId = false)
	private Telefone telefone;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	private Integer ordem;

	@Enumerated(EnumType.STRING)
	private Confirmacao principal;

	@Enumerated(EnumType.STRING)
	private Visibilidade visibilidade;

}