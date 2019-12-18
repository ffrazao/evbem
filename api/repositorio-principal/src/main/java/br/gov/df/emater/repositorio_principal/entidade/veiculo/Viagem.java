package br.gov.df.emater.repositorio_principal.entidade.veiculo;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.data.geo.Point;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import br.gov.df.emater.repositorio_principal.conversor.PointJsonDeserializer;
import br.gov.df.emater.repositorio_principal.conversor.PointJsonSerializer;
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
@EqualsAndHashCode(callSuper = true)
@SuppressWarnings("serial")
public class Viagem extends VeiculoEvento {

	@Column(name = "local_saida")
	@JsonSerialize(using = PointJsonSerializer.class)
	@JsonDeserialize(using = PointJsonDeserializer.class)
	private Point localSaida;

	@Column(name = "local_saida_descricao")
	private String localSaidaDescricao;

	@ManyToOne
	@JoinColumn(name = "pessoa_id")
	private Pessoa pessoa;

	@Column(name = "quilometragem_saida")
	private BigDecimal quilometragemSaida;

	@OneToMany(mappedBy = "viagem", fetch = FetchType.LAZY)
	private List<Rota> rotaList = new ArrayList<>();

	@ManyToOne
	@JoinColumn(name = "unidade_organizacional_id")
	private UnidadeOrganizacional unidadeOrganizacional;

	public Viagem(Integer valor) {
		super(valor);
	}

	@Override
	public Viagem infoBasica() {
		Viagem result = (Viagem) super.infoBasica();
		if (result.getPessoa() != null) {
			result.setPessoa((Pessoa) result.getPessoa().copy());
		}
		if (result.getUnidadeOrganizacional() != null) {
			result.setUnidadeOrganizacional(result.getUnidadeOrganizacional().infoBasica());
		}
		result.setRotaList(result.getRotaList().stream().map(e -> e.infoBasica()).collect(Collectors.toList()));
		return result;
	}

}
