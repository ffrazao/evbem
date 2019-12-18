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
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import br.gov.df.emater.repositorio_principal.dominio.Confirmacao;
import br.gov.df.emater.repositorio_principal.entidade.base.Ativavel;
import br.gov.df.emater.repositorio_principal.entidade.base.EntidadeBase;
import br.gov.df.emater.repositorio_principal.entidade.base.NomeavelCodificavel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * The persistent class for the perfil database table.
 * 
 */
@Entity
@Table(catalog = "sistema")
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@SuppressWarnings("serial")
public class Perfil extends EntidadeBase implements NomeavelCodificavel, Ativavel {

	@Enumerated(EnumType.STRING)
	private Confirmacao administrador;

	@Enumerated(EnumType.STRING)
	private Confirmacao ativo;

	private String codigo;

	@Lob
	private String descricao;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	private String nome;

	@OneToMany(mappedBy = "perfil")
	private List<Privilegio> privilegioList = new ArrayList<>();

	public Perfil(Integer valor) {
		super(valor);
	}

	@Override
	public Perfil infoBasica() {
		Perfil result = (Perfil) super.infoBasica();
		result.setPrivilegioList(
				result.getPrivilegioList().stream().map(e -> e.infoBasica()).collect(Collectors.toList()));
		return result;
	}

}