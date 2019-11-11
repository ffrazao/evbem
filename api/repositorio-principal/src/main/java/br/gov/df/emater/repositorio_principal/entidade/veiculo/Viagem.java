package br.gov.df.emater.repositorio_principal.entidade.veiculo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.data.geo.Point;

import br.gov.df.emater.repositorio_principal.entidade.funcional.UnidadeOrganizacional;
import br.gov.df.emater.repositorio_principal.entidade.principal.Pessoa;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * The persistent class for the Viagem database table.
 * 
 */
@Entity
@Table(catalog = "veiculo")
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class Viagem extends VeiculoEvento implements Serializable {

	private static final long serialVersionUID = 1L;

	@Column(name = "local_chegada")
	private Point localChegada;
	
	@Column(name = "local_chegada_descricao")
	private String localChegadaDescricao;
	
	@Column(name = "local_saida")
	private Point localSaida;
	
	@Column(name = "local_saida_descricao")
	private String localSaidaDescricao;
	
	@ManyToOne
	@JoinColumn(name = "pessoa_id")
	private Pessoa pessoa;
	
	@Column(name = "quilometragem_chegada")
	private BigDecimal quilometragemChegada;
	
	@Column(name = "quilometragem_saida")
	private BigDecimal quilometragemSaida;
	
	@OneToMany(mappedBy = "viagem", fetch = FetchType.LAZY)
	private List<Rota> rotaList;
	
	@ManyToOne
	@JoinColumn(name = "unidade_organizacional_id")
	private UnidadeOrganizacional unidadeOrganizacional;

}
