package br.gov.df.emater.repositorio_principal.entidade.produto;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

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
@Table(catalog = "produto", name = "bem_patrimonial")
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class BemPatrimonial extends Produto implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Lob
	private String observacao;

	@Column(name = "identificacao_patrimonial")
	private String identificacaoPatrimonial;
	
	@ManyToOne
	@JoinColumn(name = "pessoa_responsavel_id")
	private Pessoa pessoaResponsavel;

}