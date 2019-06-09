package br.gov.df.emater.repositorio_principal.entidade.principal;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import br.gov.df.emater.repositorio_principal.entidade.EntidadeBase;
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
@EqualsAndHashCode(callSuper = false)
public class Item extends EntidadeBase implements Serializable {
	private static final long serialVersionUID = 1L;

	private String ativo;

	@Column(name = "atualizado_em")
	private Timestamp atualizadoEm;

	@Column(name = "atualizado_usuario_id")
	private int atualizadoUsuarioId;

	@Column(name = "criado_em")
	private Timestamp criadoEm;

	@Column(name = "criado_usuario_id")
	private int criadoUsuarioId;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	@Lob
	private String observacao;

	// bi-directional one-to-one association to Pessoa
	@OneToOne(mappedBy = "item")
	private Pessoa pessoa;

	// bi-directional one-to-one association to Produto
	@OneToOne(mappedBy = "item")
	private Produto produto;

	// bi-directional one-to-one association to Servico
	@OneToOne(mappedBy = "item")
	private Servico servico;

	private String tipo;

}