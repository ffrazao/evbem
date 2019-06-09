package br.gov.df.emater.repositorio_principal.entidade.sistema;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import br.gov.df.emater.repositorio_principal.entidade.EntidadeBase;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * The persistent class for the usuario_perfil database table.
 * 
 */
@Entity
@Table(catalog = "sistema", name = "usuario_perfil")
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class UsuarioPerfil extends EntidadeBase implements Serializable {
	private static final long serialVersionUID = 1L;

	private String ativo;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	private String padrao;

	// bi-directional many-to-one association to Perfil
	@ManyToOne
	private Perfil perfil;

	// bi-directional many-to-one association to Usuario
	@ManyToOne
	private Usuario usuario;

	// bi-directional many-to-one association to Usuario
	@OneToMany(mappedBy = "usuarioPerfil")
	private List<Usuario> usuarios;

}