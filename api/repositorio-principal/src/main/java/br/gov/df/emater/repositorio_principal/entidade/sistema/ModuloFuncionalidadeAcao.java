package br.gov.df.emater.repositorio_principal.entidade.sistema;

import javax.persistence.Column;
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
import br.gov.df.emater.repositorio_principal.entidade.base.Ativavel;
import br.gov.df.emater.repositorio_principal.entidade.base.EntidadeBase;
import br.gov.df.emater.repositorio_principal.entidade.base.Ordenavel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * The persistent class for the modulo_funcionalidade_acao database table.
 * 
 */
@Entity
@Table(catalog = "sistema", name = "modulo_funcionalidade_acao")
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@SuppressWarnings("serial")
public class ModuloFuncionalidadeAcao extends EntidadeBase implements Ativavel, Ordenavel {

	@Enumerated(EnumType.STRING)
	private Confirmacao ativo;

	@Column(name = "exibir_menu_principal")
	@Enumerated(EnumType.STRING)
	private Confirmacao exibirMenuPrincipal;

	@ManyToOne
	@JoinColumn(name = "funcionalidade_acao_id")
	// @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,
	// property = "id")
	// @JsonIdentityReference(alwaysAsId = false)
	private FuncionalidadeAcao funcionalidadeAcao;

	@Column(name = "grupo_menu")
	private String grupoMenu;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@ManyToOne
	@JoinColumn(name = "modulo_id")
	// @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,
	// property = "id")
	// @JsonIdentityReference(alwaysAsId = false)
	private Modulo modulo;

	private Integer ordem;

	public ModuloFuncionalidadeAcao(Integer valor) {
		super(valor);
	}

	@Override
	public ModuloFuncionalidadeAcao infoBasica() {
		ModuloFuncionalidadeAcao result = (ModuloFuncionalidadeAcao) super.infoBasica();
		if (result.getFuncionalidadeAcao() != null) {
			result.setFuncionalidadeAcao(result.getFuncionalidadeAcao().infoBasica());
		}
		if (result.getModulo() != null) {
			result.setModulo(result.getModulo().infoBasica());
		}
		return result;
	}

}