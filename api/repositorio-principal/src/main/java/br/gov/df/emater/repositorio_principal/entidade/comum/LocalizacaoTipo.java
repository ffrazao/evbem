package br.gov.df.emater.repositorio_principal.entidade.comum;

import java.io.Serializable;
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

import br.gov.df.emater.repositorio_principal.entidade.EntidadeBase;
import br.gov.df.emater.repositorio_principal.entidade.Identificavel;
import br.gov.df.emater.repositorio_principal.entidade.Nomeavel;
import br.gov.df.emater.repositorio_principal.entidade.Pai;
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
@EqualsAndHashCode(callSuper = false)
public class LocalizacaoTipo extends EntidadeBase implements Serializable, Identificavel, Pai<LocalizacaoTipo>, Nomeavel {

	private static final long serialVersionUID = 1L;

	@OneToMany(mappedBy = "pai", fetch = FetchType.LAZY)
	@Setter(AccessLevel.PRIVATE)
	private List<LocalizacaoTipo> filhos;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	private String nome;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "pai_id")
	private LocalizacaoTipo pai;

}