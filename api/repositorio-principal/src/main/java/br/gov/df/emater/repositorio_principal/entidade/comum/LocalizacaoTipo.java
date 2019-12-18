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

import br.gov.df.emater.repositorio_principal.entidade.base.EntidadeBase;
import br.gov.df.emater.repositorio_principal.entidade.base.PaiNomeavel;
import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * The persistent class for the localizacao_tipo database table.
 * 
 */
@Entity
@Table(catalog = "comum", name = "localizacao_tipo")
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@SuppressWarnings("serial")
public class LocalizacaoTipo extends EntidadeBase implements PaiNomeavel<LocalizacaoTipo> {

	@OneToMany(mappedBy = "pai", fetch = FetchType.LAZY)
	@Setter(AccessLevel.PRIVATE)
	private List<LocalizacaoTipo> filhos;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	private String nome;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "pai_id")
	// @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,
	// property = "id")
	// @JsonIdentityReference(alwaysAsId = false)
	private LocalizacaoTipo pai;

	public LocalizacaoTipo(Integer valor) {
		super(valor);
	}

	public LocalizacaoTipo(Integer id, String nome) {
		this(id);
		setNome(nome);
	}

	@Override
	public LocalizacaoTipo infoBasica() {
		return (LocalizacaoTipo) ((PaiNomeavel<LocalizacaoTipo>) this).copy();
	}

}