package br.gov.df.emater.repositorio_principal.entidade.produto;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import br.gov.df.emater.repositorio_principal.entidade.base.EntidadeBase;
import br.gov.df.emater.repositorio_principal.entidade.principal.Pessoa;
import br.gov.df.emater.repositorio_principal.entidade.principal.Produto;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * The persistent class for the bem_patrimonial database table.
 * 
 */
@Entity
@Table(catalog = "produto", name = "produto_pessoa")
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class ProdutoPessoa extends EntidadeBase implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	private Integer id;

	@ManyToOne
	@JoinColumn(name = "pessoa_id")
	@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
	@JsonIdentityReference(alwaysAsId = false)
	private Pessoa pessoa; // pode utilizar o bem

	@ManyToOne
	@JoinColumn(name = "produto_id")
	@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
	@JsonIdentityReference(alwaysAsId = false)
	private Produto produto;

}