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

import br.gov.df.emater.repositorio_principal.dominio.Confirmacao;
import br.gov.df.emater.repositorio_principal.entidade.base.EntidadeBase;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * The persistent class for the privilegio database table.
 * 
 */
@Entity
@Table(catalog = "sistema")
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@SuppressWarnings("serial")
public class Privilegio extends EntidadeBase {

	@Enumerated(EnumType.STRING)
	private Confirmacao ativo;

	@ManyToOne
	@JoinColumn(name = "funcionalidade_acao_id")
	// @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,
	// property = "id")
	// @JsonIdentityReference(alwaysAsId = false)
	private FuncionalidadeAcao funcionalidadeAcao;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@ManyToOne
	@JoinColumn(name = "perfil_id")
	// @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,
	// property = "id")
	// @JsonIdentityReference(alwaysAsId = false)
	private Perfil perfil;

	public Privilegio(Integer valor) {
		super(valor);
	}

	@Override
	public Privilegio infoBasica() {
		Privilegio result = (Privilegio) super.infoBasica();
		if (result.getFuncionalidadeAcao() != null) {
			result.setFuncionalidadeAcao(result.getFuncionalidadeAcao().infoBasica());
		}
		if (result.getPerfil() != null) {
			result.setPerfil(result.getPerfil().infoBasica());
		}
		return result;
	}

}