package br.gov.df.emater.repositorio_principal.entidade.comum;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import br.gov.df.emater.repositorio_principal.dominio.Confirmacao;
import br.gov.df.emater.repositorio_principal.entidade.base.EntidadeBase;
import br.gov.df.emater.repositorio_principal.entidade.base.PaiNomeavelCodificavel;
import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * The persistent class for the modelo database table.
 * 
 */
@Entity
@Table(catalog = "comum", name = "unidade_medida")
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@SuppressWarnings("serial")
public class UnidadeMedida extends EntidadeBase implements PaiNomeavelCodificavel<UnidadeMedida> {

	private String codigo;

	@OneToMany(mappedBy = "pai", fetch = FetchType.LAZY)
	@Setter(AccessLevel.PRIVATE)
	private List<UnidadeMedida> filhos = new ArrayList<>();

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	private String nome;

	@Enumerated(EnumType.STRING)
	@Column(name = "padrao_identificavel")
	private Confirmacao padraoIdentificavel;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "pai_id")
	// @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,
	// property = "id")
	// @JsonIdentityReference(alwaysAsId = false)
	private UnidadeMedida pai;

	private String sigla;

	@Enumerated(EnumType.STRING)
	@Column(name = "unidade_basica")
	private Confirmacao unidadeBasica;

	@Column(name = "valor_referencia")
	private BigDecimal valorReferencia;

	public UnidadeMedida(Integer id) {
		super(id);
	}

	@Override
	public UnidadeMedida infoBasica() {
		UnidadeMedida result = (UnidadeMedida) copy();
		result.setPadraoIdentificavel(this.padraoIdentificavel);
		result.setSigla(this.getSigla());
		result.setUnidadeBasica(this.unidadeBasica);
		result.setValorReferencia(this.valorReferencia);
		return result;
	}

}