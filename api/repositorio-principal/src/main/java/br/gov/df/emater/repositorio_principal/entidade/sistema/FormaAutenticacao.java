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
 * The persistent class for the forma_autenticacao database table.
 * 
 */
@Entity
@Table(catalog = "sistema", name = "forma_autenticacao")
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class FormaAutenticacao extends EntidadeBase implements Serializable {
	private static final long serialVersionUID = 1L;

	private String ativo;

	private String codigo;

	private String config;

	@Lob
	private String descricao;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	private String nome;

	private int ordem;

	private String padrao;

	private String tipo;

	// bi-directional many-to-one association to UsuarioFormaAutenticacao
	@OneToMany(mappedBy = "formaAutenticacao")
	private List<UsuarioFormaAutenticacao> usuarioFormaAutenticacaos;

}