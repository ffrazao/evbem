package br.gov.df.emater.repositorio_principal.entidade.principal;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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

import br.gov.df.emater.repositorio_principal.entidade.base.EntidadeBase;
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
@EqualsAndHashCode(callSuper = true)
@SuppressWarnings("serial")
public class Produto extends EntidadeBase {

	public Produto(Integer valor) {
		super(valor);
	}

	@Override
	public Produto infoBasica() {
		Produto result = (Produto) super.infoBasica();
		if (result.getBemPatrimonial() != null) {
			result.setBemPatrimonial(result.getBemPatrimonial().infoBasica());
		}
		result.setComposicaoList(
				result.getComposicaoList().stream().map(e -> e.infoBasica()).collect(Collectors.toList()));
		if (result.getMarca() != null) {
			result.setMarca(result.getMarca().infoBasica());
		}
		if (result.getModelo() != null) {
			result.setModelo(result.getModelo().infoBasica());
		}
		if (result.getPessoa() != null) {
			result.setPessoa((Pessoa) result.getPessoa().copy());
		}
		result.setProdutoPessoaList(
				result.getProdutoPessoaList().stream().map(e -> e.infoBasica()).collect(Collectors.toList()));
		if (result.getRecurso() != null) {
			result.setRecurso(result.getRecurso().infoBasica());
		}
		if (result.getUnidadeMedida() != null) {
			result.setUnidadeMedida(result.getUnidadeMedida().infoBasica());
		}
		return result;
	}

	@OneToOne(mappedBy = "produto")
	private BemPatrimonial bemPatrimonial;

	@OneToMany(mappedBy = "principal", fetch = FetchType.LAZY)
	private List<Composicao> composicaoList = new ArrayList<>();

	@Id
	private Integer id;

	@ManyToOne
	@JoinColumn(name = "marca_id")
	// @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,
	// property = "id")
	// @JsonIdentityReference(alwaysAsId = false)
	private Marca marca;

	@ManyToOne
	@JoinColumn(name = "modelo_id")
	// @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,
	// property = "id")
	// @JsonIdentityReference(alwaysAsId = false)
	private Modelo modelo;

	@Column(name = "numero_serie")
	private String numeroSerie;

	@ManyToOne
	@JoinColumn(name = "pessoa_id")
	// @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,
	// property = "id")
	// @JsonIdentityReference(alwaysAsId = false)
	private Pessoa pessoa; // proprietario do bem

	@OneToMany(mappedBy = "produto", fetch = FetchType.LAZY)
	private List<ProdutoPessoa> produtoPessoaList = new ArrayList<>();

	private BigDecimal quantidade;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "id")
	@MapsId
	private Recurso recurso;

	@ManyToOne
	@JoinColumn(name = "unidade_medida_id")
	// @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,
	// property = "id")
	// @JsonIdentityReference(alwaysAsId = false)
	private UnidadeMedida unidadeMedida;

}