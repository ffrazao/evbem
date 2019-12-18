package br.gov.df.emater.repositorio_principal.entidade.produto;

import java.util.Calendar;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import br.gov.df.emater.repositorio_principal.entidade.base.EntidadeBase;
import br.gov.df.emater.repositorio_principal.entidade.base.Temporalizavel;
import br.gov.df.emater.repositorio_principal.entidade.principal.Produto;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * The persistent class for the composicao database table.
 * 
 */
@Entity
@Table(catalog = "produto")
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@SuppressWarnings("serial")
public class Composicao extends EntidadeBase implements Temporalizavel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Temporal(TemporalType.TIMESTAMP)
	private Calendar inicio;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "principal_produto_id")
	// @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,
	// property = "id")
	// @JsonIdentityReference(alwaysAsId = false)
	private Produto principal;

	@ManyToOne
	@JoinColumn(name = "produto_id")
	// @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,
	// property = "id")
	// @JsonIdentityReference(alwaysAsId = false)
	private Produto produto;

	@Temporal(TemporalType.TIMESTAMP)
	private Calendar termino;

	public Composicao(Integer valor) {
		super(valor);
	}

	@Override
	public Composicao infoBasica() {
		Composicao result = (Composicao) super.infoBasica();
		if (result.getPrincipal() != null) {
			result.setPrincipal(result.getPrincipal().infoBasica());
		}
		if (result.getProduto() != null) {
			result.setProduto(result.getProduto().infoBasica());
		}
		return result;
	}

}