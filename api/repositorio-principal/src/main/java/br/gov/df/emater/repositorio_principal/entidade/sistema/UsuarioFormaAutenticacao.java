package br.gov.df.emater.repositorio_principal.entidade.sistema;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import br.gov.df.emater.repositorio_principal.entidade.EntidadeBase;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * The persistent class for the usuario_forma_autenticacao database table.
 * 
 */
@Entity
@Table(catalog = "sistema", name = "usuario_forma_autenticacao")
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class UsuarioFormaAutenticacao extends EntidadeBase implements Serializable {
	private static final long serialVersionUID = 1L;

	private String ativo;

	// bi-directional many-to-one association to FormaAutenticacao
	@ManyToOne
	@JoinColumn(name = "forma_autenticacao_id")
	private FormaAutenticacao formaAutenticacao;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	// bi-directional many-to-one association to Usuario
	@ManyToOne
	private Usuario usuario;

	private String valor;

}