package br.gov.df.emater.repositorio_principal.entidade.sistema;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import br.gov.df.emater.repositorio_principal.dominio.Confirmacao;
import br.gov.df.emater.repositorio_principal.entidade.base.Ativavel;
import br.gov.df.emater.repositorio_principal.entidade.base.EntidadeBase;
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
@EqualsAndHashCode(callSuper = true)
@SuppressWarnings("serial")
public class UsuarioPerfil extends EntidadeBase implements Ativavel {

	@Enumerated(EnumType.STRING)
	private Confirmacao ativo;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Enumerated(EnumType.STRING)
	private Confirmacao padrao;

	@ManyToOne
	@JoinColumn(name = "perfil_id")
	// @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,
	// property = "id")
	// @JsonIdentityReference(alwaysAsId = false)
	private Perfil perfil;

	@ManyToOne
	@JoinColumn(name = "usuario_id")
	@JsonIgnore
	private Usuario usuario;

	public UsuarioPerfil(Integer valor) {
		super(valor);
	}

	@Override
	public UsuarioPerfil infoBasica() {
		UsuarioPerfil result = (UsuarioPerfil) super.infoBasica();
		if (result.getPerfil() != null) {
			result.setPerfil(result.getPerfil().infoBasica());
		}
		if (result.getUsuario() != null) {
			result.setUsuario(result.getUsuario().infoBasica());
		}
		return result;
	}

}