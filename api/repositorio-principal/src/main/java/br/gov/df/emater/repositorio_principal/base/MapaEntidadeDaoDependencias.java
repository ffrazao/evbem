package br.gov.df.emater.repositorio_principal.base;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.springframework.stereotype.Component;

import br.gov.df.emater.repositorio_principal.dao.comum.ArquivoDAO;
import br.gov.df.emater.repositorio_principal.dao.comum.EmailDAO;
import br.gov.df.emater.repositorio_principal.dao.comum.EnderecoDAO;
import br.gov.df.emater.repositorio_principal.dao.comum.FotoDAO;
import br.gov.df.emater.repositorio_principal.dao.comum.TelefoneDAO;
import br.gov.df.emater.repositorio_principal.dao.pessoa.PessoaArquivoDAO;
import br.gov.df.emater.repositorio_principal.dao.pessoa.PessoaEmailDAO;
import br.gov.df.emater.repositorio_principal.dao.pessoa.PessoaEnderecoDAO;
import br.gov.df.emater.repositorio_principal.dao.pessoa.PessoaFotoDAO;
import br.gov.df.emater.repositorio_principal.dao.pessoa.PessoaRelacionamentoDAO;
import br.gov.df.emater.repositorio_principal.dao.pessoa.PessoaTelefoneDAO;
import br.gov.df.emater.repositorio_principal.dao.pessoa.RelacionamentoDAO;
import br.gov.df.emater.repositorio_principal.dao.principal.PessoaDAO;
import br.gov.df.emater.repositorio_principal.dao.principal.ProdutoDAO;
import br.gov.df.emater.repositorio_principal.dao.produto.BemPatrimonialDAO;
import br.gov.df.emater.repositorio_principal.dao.produto.ComposicaoDAO;
import br.gov.df.emater.repositorio_principal.dao.produto.ProdutoPessoaDAO;
import br.gov.df.emater.repositorio_principal.dao.sistema.UsuarioDAO;
import br.gov.df.emater.repositorio_principal.dao.sistema.UsuarioFormaAutenticacaoDAO;
import br.gov.df.emater.repositorio_principal.dao.sistema.UsuarioPerfilDAO;
import br.gov.df.emater.repositorio_principal.entidade.base.EntidadeBase;
import br.gov.df.emater.repositorio_principal.entidade.comum.Arquivo;
import br.gov.df.emater.repositorio_principal.entidade.comum.Email;
import br.gov.df.emater.repositorio_principal.entidade.comum.Endereco;
import br.gov.df.emater.repositorio_principal.entidade.comum.Foto;
import br.gov.df.emater.repositorio_principal.entidade.comum.Telefone;
import br.gov.df.emater.repositorio_principal.entidade.pessoa.PessoaArquivo;
import br.gov.df.emater.repositorio_principal.entidade.pessoa.PessoaEmail;
import br.gov.df.emater.repositorio_principal.entidade.pessoa.PessoaEndereco;
import br.gov.df.emater.repositorio_principal.entidade.pessoa.PessoaFoto;
import br.gov.df.emater.repositorio_principal.entidade.pessoa.PessoaRelacionamento;
import br.gov.df.emater.repositorio_principal.entidade.pessoa.PessoaTelefone;
import br.gov.df.emater.repositorio_principal.entidade.pessoa.Relacionamento;
import br.gov.df.emater.repositorio_principal.entidade.principal.Pessoa;
import br.gov.df.emater.repositorio_principal.entidade.principal.Produto;
import br.gov.df.emater.repositorio_principal.entidade.produto.BemPatrimonial;
import br.gov.df.emater.repositorio_principal.entidade.produto.Composicao;
import br.gov.df.emater.repositorio_principal.entidade.produto.ProdutoPessoa;
import br.gov.df.emater.repositorio_principal.entidade.sistema.Usuario;
import br.gov.df.emater.repositorio_principal.entidade.sistema.UsuarioFormaAutenticacao;
import br.gov.df.emater.repositorio_principal.entidade.sistema.UsuarioPerfil;

@Component
public class MapaEntidadeDaoDependencias {

	private final Set<Dep<?, ?>> mapa = new HashSet<>();

	// @formatter:off
	MapaEntidadeDaoDependencias() {

		// mapa pessoa
		this.mapa.add(Dep.of(Pessoa.class, PessoaDAO.class, new Dep[] {
				Dep.of(PessoaArquivo.class, PessoaArquivoDAO.class, Dep.of(Arquivo.class, ArquivoDAO.class)),
				Dep.of(PessoaEmail.class, PessoaEmailDAO.class, Dep.of(Email.class, EmailDAO.class)),
				Dep.of(PessoaEndereco.class, PessoaEnderecoDAO.class, Dep.of(Endereco.class, EnderecoDAO.class)),
				Dep.of(PessoaFoto.class, PessoaFotoDAO.class, Dep.of(Foto.class, FotoDAO.class)),
				Dep.of(PessoaRelacionamento.class, PessoaRelacionamentoDAO.class,
						Dep.of(Relacionamento.class, RelacionamentoDAO.class)),
				Dep.of(PessoaTelefone.class, PessoaTelefoneDAO.class, Dep.of(Telefone.class, TelefoneDAO.class)), }));

		// mapa usuario
		this.mapa.add(
				Dep.of(Usuario.class, UsuarioDAO.class, new Dep[] { Dep.of(UsuarioPerfil.class, UsuarioPerfilDAO.class),
						Dep.of(UsuarioFormaAutenticacao.class, UsuarioFormaAutenticacaoDAO.class), }));

		// mapa produto
		this.mapa.add(Dep.of(Produto.class, ProdutoDAO.class,
				new Dep[] { Dep.of(BemPatrimonial.class, BemPatrimonialDAO.class),
						Dep.of(Composicao.class, ComposicaoDAO.class),
						Dep.of(ProdutoPessoa.class, ProdutoPessoaDAO.class), }));

	}
	// @formatter:on

	public <E extends EntidadeBase> Optional<Dep<?, ?>> getDaoDependencias(final E entidade) {
		return this.mapa.stream().filter(d -> d.equals(entidade)).findFirst();
	}
}
