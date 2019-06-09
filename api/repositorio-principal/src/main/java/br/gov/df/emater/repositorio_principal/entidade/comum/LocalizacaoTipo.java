package br.gov.df.emater.repositorio_principal.entidade.comum;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import br.gov.df.emater.repositorio_principal.entidade.EntidadeBase;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * The persistent class for the localizacao_tipo database table.
 * 
 */
@Entity
@Table(catalog = "comum", name = "localizacao_tipo")
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class LocalizacaoTipo extends EntidadeBase implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	// bi-directional many-to-one association to Localizacao
	@OneToMany(mappedBy = "localizacaoTipo")
	private List<Localizacao> localizacaos;

	// bi-directional many-to-one association to LocalizacaoTipo
	@ManyToOne
	@JoinColumn(name = "pai_id")
	private LocalizacaoTipo localizacaoTipo;

	// bi-directional many-to-one association to LocalizacaoTipo
	@OneToMany(mappedBy = "localizacaoTipo")
	private List<LocalizacaoTipo> localizacaoTipos;

	private String nome;

}