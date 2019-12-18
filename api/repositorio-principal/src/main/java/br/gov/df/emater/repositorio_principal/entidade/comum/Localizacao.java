package br.gov.df.emater.repositorio_principal.entidade.comum;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import br.gov.df.emater.repositorio_principal.entidade.base.PaiNomeavel;
import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * The persistent class for the localizacao database table.
 * 
 */
@Entity
@Table(catalog = "comum")
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@SuppressWarnings("serial")
public class Localizacao extends ReferenciaEspacial implements PaiNomeavel<Localizacao> {

	@OneToMany(mappedBy = "pai", fetch = FetchType.LAZY)
	@Setter(AccessLevel.PRIVATE)
	private List<Localizacao> filhos;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@ManyToOne
	@JoinColumn(name = "localizacao_tipo_id")
	// @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,
	// property = "id")
	// @JsonIdentityReference(alwaysAsId = false)
	private LocalizacaoTipo localizacaoTipo;

	private String nome;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "pai_id")
	// @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,
	// property = "id")
	// @JsonIdentityReference(alwaysAsId = false)
	private Localizacao pai;

	public Localizacao(Integer valor) {
		super(valor);
	}

	@Override
	public Localizacao infoBasica() {
		Localizacao result = (Localizacao) super.infoBasica();
		result.setFilhos(null);
		if (result.getLocalizacaoTipo() != null) {
			result.setLocalizacaoTipo((LocalizacaoTipo) result.getLocalizacaoTipo().copy());
		}
		if (result.getPai() != null) {
			result.setPai((Localizacao) result.getPai().copy());
		}
		return result;
	}

}