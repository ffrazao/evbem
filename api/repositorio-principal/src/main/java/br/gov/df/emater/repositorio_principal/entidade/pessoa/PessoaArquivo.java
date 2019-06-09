package br.gov.df.emater.repositorio_principal.entidade.pessoa;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import br.gov.df.emater.repositorio_principal.entidade.EntidadeBase;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * The persistent class for the pessoa_arquivo database table.
 * 
 */
@Entity
@Table(catalog = "pessoa", name = "pessoa_arquivo")
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class PessoaArquivo extends EntidadeBase implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name = "arquivo_id")
	private int arquivoId;

	@Column(name = "atualizado_em")
	private Timestamp atualizadoEm;

	@Column(name = "atualizado_usuario_id")
	private int atualizadoUsuarioId;

	@Column(name = "criado_em")
	private Timestamp criadoEm;

	@Column(name = "criado_usuario_id")
	private int criadoUsuarioId;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	private int ordem;

	@Column(name = "pessoa_id")
	private int pessoaId;

	private String principal;

	private String tipo;

}