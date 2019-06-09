package br.gov.df.emater.repositorio_principal.entidade.sistema;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import br.gov.df.emater.repositorio_principal.entidade.EntidadeBase;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * The persistent class for the acao database table.
 * 
 */
@Entity
@Table(catalog = "sistema")
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class Acao extends EntidadeBase implements Serializable {
	private static final long serialVersionUID = 1L;

	// bi-directional many-to-one association to Acao
	@ManyToOne
	@JoinColumn(name = "pai_id")
	private Acao acao;

	// bi-directional many-to-one association to Acao
	@OneToMany(mappedBy = "acao")
	private List<Acao> acaos;

	private String ativo;

	private String codigo;

	@Lob
	private String descricao;

	// bi-directional many-to-one association to FuncionalidadeAcao
	@OneToMany(mappedBy = "acao")
	private List<FuncionalidadeAcao> funcionalidadeAcaos;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	private String nome;

	private int ordem;

	private String tipo;

}