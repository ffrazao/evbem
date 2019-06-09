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
 * The persistent class for the funcionalidade database table.
 * 
 */
@Entity
@Table(catalog = "sistema")
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class Funcionalidade extends EntidadeBase implements Serializable {
	private static final long serialVersionUID = 1L;

	private String ativo;

	private String codigo;

	@Lob
	private String descricao;

	//bi-directional many-to-one association to Funcionalidade
	@ManyToOne
	@JoinColumn(name="pai_id")
	private Funcionalidade funcionalidade;

	//bi-directional many-to-one association to FuncionalidadeAcao
	@OneToMany(mappedBy="funcionalidade")
	private List<FuncionalidadeAcao> funcionalidadeAcaos;

	//bi-directional many-to-one association to Funcionalidade
	@OneToMany(mappedBy="funcionalidade")
	private List<Funcionalidade> funcionalidades;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;

	private String nome;

}