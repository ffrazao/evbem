package br.gov.df.emater.repositorio_principal.entidade.pessoa;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import br.gov.df.emater.repositorio_principal.entidade.EntidadeBase;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;


/**
 * The persistent class for the relacionamento_tipo database table.
 * 
 */
@Entity
@Table(catalog = "pessoa", name="relacionamento_tipo")
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)

public class RelacionamentoTipo extends EntidadeBase implements Serializable {
	private static final long serialVersionUID = 1L;

	private String codigo;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;

	private String nome;

	//bi-directional many-to-one association to RelacionamentoConfiguracao
	@OneToMany(mappedBy="relacionamentoTipo")
	private List<RelacionamentoConfiguracao> relacionamentoConfiguracaos;

	//bi-directional many-to-one association to Relacionamento
	@OneToMany(mappedBy="relacionamentoTipo")
	private List<Relacionamento> relacionamentos;

	private String temporal;

}