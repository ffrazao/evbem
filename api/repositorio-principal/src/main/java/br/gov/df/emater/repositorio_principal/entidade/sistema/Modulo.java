package br.gov.df.emater.repositorio_principal.entidade.sistema;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import br.gov.df.emater.repositorio_principal.entidade.EntidadeBase;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * The persistent class for the modulo database table.
 * 
 */
@Entity
@Table(catalog = "sistema")
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class Modulo extends EntidadeBase implements Serializable {
	private static final long serialVersionUID = 1L;

	private String ativo;

	private String codigo;

	// bi-directional many-to-one association to Configuracao
	@OneToMany(mappedBy = "modulo")
	private List<Configuracao> configuracaos;

	@Lob
	private String descricao;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	// bi-directional many-to-one association to ModuloFuncionalidadeAcao
	@OneToMany(mappedBy = "modulo")
	private List<ModuloFuncionalidadeAcao> moduloFuncionalidadeAcaos;

	private String nome;

}