package br.gov.df.emater.repositorio_principal.entidade.principal;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import br.gov.df.emater.repositorio_principal.entidade.base.EntidadeBase;
import br.gov.df.emater.repositorio_principal.entidade.base.Identificavel;
import br.gov.df.emater.repositorio_principal.entidade.comum.UnidadeMedida;
import br.gov.df.emater.repositorio_principal.entidade.produto.BemPatrimonial;
import br.gov.df.emater.repositorio_principal.entidade.produto.Composicao;
import br.gov.df.emater.repositorio_principal.entidade.produto.Marca;
import br.gov.df.emater.repositorio_principal.entidade.produto.Modelo;
import br.gov.df.emater.repositorio_principal.entidade.produto.ProdutoPessoa;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * The persistent class for the produto database table.
 * 
 */
@Entity
@Table(catalog = "principal")
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class Produto extends EntidadeBase implements Serializable, Identificavel {

	private static final long serialVersionUID = 1L;

	@OneToOne(mappedBy = "produto")
	private BemPatrimonial bemPatrimonial;

	@ManyToOne
	@JoinColumn(name = "modelo_id")
	@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
	@JsonIdentityReference(alwaysAsId = false)
	private Modelo modelo;

	@ManyToOne
	@JoinColumn(name = "marca_id")
	@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
	@JsonIdentityReference(alwaysAsId = false)
	private Marca marca;

	@Column(name = "numero_serie")
	private String numeroSerie;

	@OneToMany(mappedBy = "principal", fetch = FetchType.LAZY)
	private List<Composicao> composicaoList = new ArrayList<>();

	@Id
	private Integer id;

	private BigDecimal quantidade;

	@ManyToOne
	@JoinColumn(name = "unidade_medida_id")
	@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
	@JsonIdentityReference(alwaysAsId = false)
	private UnidadeMedida unidademedida;

	@ManyToOne
	@JoinColumn(name = "pessoa_id")
	@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
	@JsonIdentityReference(alwaysAsId = false)
	private Pessoa pessoa; // proprietario do bem

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "id")
	@MapsId
	private Recurso recurso;
	
	@OneToMany(mappedBy = "produto", fetch = FetchType.LAZY)
	private List<ProdutoPessoa> produtoPessoaList = new ArrayList<>();

}