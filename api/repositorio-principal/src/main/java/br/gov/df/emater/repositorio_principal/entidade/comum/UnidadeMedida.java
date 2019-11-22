package br.gov.df.emater.repositorio_principal.entidade.comum;

import java.io.Serializable;
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

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import br.gov.df.emater.repositorio_principal.dominio.Confirmacao;
import br.gov.df.emater.repositorio_principal.entidade.base.EntidadeBase;
import br.gov.df.emater.repositorio_principal.entidade.base.Identificavel;
import br.gov.df.emater.repositorio_principal.entidade.base.NomeavelCodificavel;
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
@EqualsAndHashCode(callSuper = false)
public class UnidadeMedida extends EntidadeBase
		implements Serializable, Identificavel, NomeavelCodificavel, PaiNomeavelCodificavel<UnidadeMedida> {

	private static final long serialVersionUID = 1L;

	private String nome;

	private String codigo;

	private String sigla;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Enumerated(EnumType.STRING)
	@Column(name = "padrao_identificavel")
	private Confirmacao padraoIdentificavel;

	@Enumerated(EnumType.STRING)
	@Column(name = "unidade_basica")
	private Confirmacao unidadeBasica;

	@Column(name = "valor_referencia")
	private BigDecimal valorReferencia;

	@OneToMany(mappedBy = "pai", fetch = FetchType.LAZY)
	@Setter(AccessLevel.PRIVATE)
	private List<UnidadeMedida> filhos = new ArrayList<>();

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "pai_id")
	@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
	@JsonIdentityReference(alwaysAsId = false)
	private UnidadeMedida pai;

}