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
import javax.persistence.OneToOne;
import javax.persistence.Table;

import br.gov.df.emater.repositorio_principal.entidade.EntidadeBase;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * The persistent class for the localizacao database table.
 * 
 */
@Entity
@Table(catalog = "comum")
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class Localizacao extends EntidadeBase implements Serializable {
	private static final long serialVersionUID = 1L;

	// bi-directional many-to-one association to Endereco
	@OneToMany(mappedBy = "localizacao")
	private List<Endereco> enderecos;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	// bi-directional many-to-one association to Localizacao
	@ManyToOne
	@JoinColumn(name = "pai_id")
	private Localizacao localizacao;

	// bi-directional many-to-one association to Localizacao
	@OneToMany(mappedBy = "localizacao")
	private List<Localizacao> localizacaos;

	// bi-directional many-to-one association to LocalizacaoTipo
	@ManyToOne
	@JoinColumn(name = "localizacao_tipo_id")
	private LocalizacaoTipo localizacaoTipo;

	private String nome;

	// bi-directional one-to-one association to ReferenciaEspacial
	@OneToOne
	@JoinColumn(name = "id")
	private ReferenciaEspacial referenciaEspacial;

}