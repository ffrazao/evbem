package br.gov.df.emater.repositorio_principal.entidade.sistema;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import br.gov.df.emater.repositorio_principal.dominio.Confirmacao;
import br.gov.df.emater.repositorio_principal.dominio.UsuarioTipo;
import br.gov.df.emater.repositorio_principal.entidade.base.Ativavel;
import br.gov.df.emater.repositorio_principal.entidade.base.EntidadeBase;
import br.gov.df.emater.repositorio_principal.entidade.base.Nomeavel;
import br.gov.df.emater.repositorio_principal.entidade.principal.Pessoa;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * The persistent class for the usuario database table.
 * 
 */
@Entity
@Table(catalog = "sistema")
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@SuppressWarnings("serial")
public class Usuario extends EntidadeBase implements Ativavel, Nomeavel {

	@Enumerated(EnumType.STRING)
	private Confirmacao ativo;

	private String email;

	@Lob
	private byte[] foto;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	private String login;

	private String nome;

	@ManyToOne
	@JoinColumn(name = "pessoa_id")
	// @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,
	// property = "id")
	// @JsonIdentityReference(alwaysAsId = false)
	private Pessoa pessoa;

	@Enumerated(EnumType.STRING)
	private UsuarioTipo tipo;

	@ManyToOne
	@JoinColumn(name = "ultimo_perfil_id")
	// @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,
	// property = "id")
	// @JsonIdentityReference(alwaysAsId = false)
	private Perfil ultimoPerfil;

	@OneToMany(mappedBy = "usuario")
	private List<UsuarioFormaAutenticacao> usuarioFormaAutenticacaoList = new ArrayList<>();

	@OneToMany(mappedBy = "usuario")
	private List<UsuarioPerfil> usuarioPerfilList = new ArrayList<>();

	public Usuario(Integer valor) {
		super(valor);
	}

	@Override
	public Usuario infoBasica() {
		Usuario result = (Usuario) super.infoBasica();
		if (result.getPessoa() != null) {
			result.setPessoa((Pessoa) result.getPessoa().copy());
		}
		if (result.getUltimoPerfil() != null) {
			result.setUltimoPerfil(result.getUltimoPerfil().infoBasica());
		}
		result.setUsuarioFormaAutenticacaoList(result.getUsuarioFormaAutenticacaoList().stream()
				.map(e -> e.infoBasica()).collect(Collectors.toList()));
		result.setUsuarioPerfilList(
				result.getUsuarioPerfilList().stream().map(e -> e.infoBasica()).collect(Collectors.toList()));
		return result;
	}

}