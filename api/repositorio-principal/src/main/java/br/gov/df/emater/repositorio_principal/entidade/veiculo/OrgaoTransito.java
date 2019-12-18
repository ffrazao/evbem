package br.gov.df.emater.repositorio_principal.entidade.veiculo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

import br.gov.df.emater.repositorio_principal.entidade.base.EntidadeBase;
import br.gov.df.emater.repositorio_principal.entidade.base.Nomeavel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * The persistent class for the OrgaoTransito database table.
 * 
 */
@Entity
@Table(catalog = "veiculo")
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@SuppressWarnings("serial")
public class OrgaoTransito extends EntidadeBase implements Nomeavel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "nome")
	private String nome;

	@Column(name = "sigla")
	private String sigla;

	@Lob
	private String site;

	@Column(name = "unidade_federacao")
	private String unidadeFederacao;

	public OrgaoTransito(Integer valor) {
		super(valor);
	}

	@Override
	public OrgaoTransito infoBasica() {
		OrgaoTransito result = (OrgaoTransito) super.infoBasica();
		return result;
	}

}
