package br.gov.df.emater.repositorio_principal.entidade.sistema;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import br.gov.df.emater.repositorio_principal.entidade.EntidadeBase;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * The persistent class for the token database table.
 * 
 */
@Entity
@Table(catalog = "sistema")
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class Token extends EntidadeBase implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name = "criado_em")
	private Timestamp criadoEm;

	private String detalhe;

	@Column(name = "expira_em")
	private int expiraEm;

	// bi-directional many-to-one association to HistoricoAtividade
	@OneToMany(mappedBy = "token")
	private List<HistoricoAtividade> historicoAtividades;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	@Column(name = "invalidado_em")
	private Timestamp invalidadoEm;

	private String tipo;

	@Lob
	private String token;

	// bi-directional many-to-one association to Usuario
	@ManyToOne
	private Usuario usuario;

}