package br.gov.df.emater.repositorio_principal.entidade.sistema;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
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
 * The persistent class for the funcionalidade_acao database table.
 * 
 */
@Entity
@Table(catalog = "sistema", name="funcionalidade_acao")
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class FuncionalidadeAcao extends EntidadeBase implements Serializable {
	private static final long serialVersionUID = 1L;

	//bi-directional many-to-one association to Acao
	@ManyToOne
	private Acao acao;

	private String ativo;

	@Column(name="concede_acesso_a")
	private String concedeAcessoA;

	@Lob
	private String descricao;

	//bi-directional many-to-one association to Funcionalidade
	@ManyToOne
	private Funcionalidade funcionalidade;

	//bi-directional many-to-one association to FuncionalidadeAcao
	@ManyToOne
	@JoinColumn(name="pai_id")
	private FuncionalidadeAcao funcionalidadeAcao;

	//bi-directional many-to-one association to FuncionalidadeAcao
	@OneToMany(mappedBy="funcionalidadeAcao")
	private List<FuncionalidadeAcao> funcionalidadeAcaos;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;

	//bi-directional many-to-one association to ModuloFuncionalidadeAcao
	@OneToMany(mappedBy="funcionalidadeAcao")
	private List<ModuloFuncionalidadeAcao> moduloFuncionalidadeAcaos;

	//bi-directional many-to-one association to Privilegio
	@OneToMany(mappedBy="funcionalidadeAcao")
	private List<Privilegio> privilegios;

}