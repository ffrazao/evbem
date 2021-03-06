package br.gov.df.emater.repositorio_principal.base;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
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
import br.gov.df.emater.repositorio_principal.dao.principal.RecursoDAO;
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
import br.gov.df.emater.repositorio_principal.entidade.principal.Recurso;
import br.gov.df.emater.repositorio_principal.entidade.produto.BemPatrimonial;
import br.gov.df.emater.repositorio_principal.entidade.produto.Composicao;
import br.gov.df.emater.repositorio_principal.entidade.produto.ProdutoPessoa;
import br.gov.df.emater.repositorio_principal.entidade.sistema.Usuario;
import br.gov.df.emater.repositorio_principal.entidade.sistema.UsuarioFormaAutenticacao;
import br.gov.df.emater.repositorio_principal.entidade.sistema.UsuarioPerfil;
import br.gov.df.emater.transporte.FiltroDTO;
import br.gov.df.emater.transporte.ListagemDTO;
import br.gov.df.emater.transporte.principal.PessoaFiltroDTO;

@Component
public class MapaDep {

	private final InstanciaBean instanciaBean;

	private final Set<Dep<?, ?, ?, ?>> mapa = new HashSet<>();

	// @formatter:off
	@Autowired
	MapaDep(final InstanciaBean instanciaBean) {
		this.instanciaBean = instanciaBean;

		// mapa pessoa
		this.mapa.add(
				Dep.of("pessoa", Pessoa.class, PessoaDAO.class, PessoaFiltroDTO.class, ListagemDTO.class,
					Dep.of("recurso", Recurso.class, RecursoDAO.class),
					Dep.of("arquivoList", PessoaArquivo.class, PessoaArquivoDAO.class,
							Dep.of("arquivo", Arquivo.class, ArquivoDAO.class)).setCampoPai("pessoa"),
					Dep.of("emailList", PessoaEmail.class, PessoaEmailDAO.class,
							Dep.of("email", Email.class, EmailDAO.class)).setCampoPai("pessoa"),
					Dep.of("enderecoList", PessoaEndereco.class, PessoaEnderecoDAO.class,
							Dep.of("endereco", Endereco.class, EnderecoDAO.class)).setCampoPai("pessoa"),
					Dep.of("fotoList", PessoaFoto.class, PessoaFotoDAO.class,
							Dep.of("foto", Foto.class, FotoDAO.class)).setCampoPai("pessoa"),
					Dep.of("relacionamentoList", PessoaRelacionamento.class, PessoaRelacionamentoDAO.class,
							Dep.of("relacionamento", Relacionamento.class, RelacionamentoDAO.class)).setCampoPai("pessoa"),
					Dep.of("telefoneList", PessoaTelefone.class, PessoaTelefoneDAO.class,
							Dep.of("telefone", Telefone.class, TelefoneDAO.class)).setCampoPai("pessoa")
				));

		// mapa usuario
		this.mapa.add(
				Dep.of("usuario", Usuario.class, UsuarioDAO.class,
						Dep.of("usuarioPerfilList", UsuarioPerfil.class, UsuarioPerfilDAO.class).setCampoPai("usuario"),
						Dep.of("usuarioFormaAutenticacaoList", UsuarioFormaAutenticacao.class, UsuarioFormaAutenticacaoDAO.class).setCampoPai("usuario")
				));

		// mapa produto
		this.mapa.add(
				Dep.of("produto", Produto.class, ProdutoDAO.class,
						Dep.of("bemPatrimonial", BemPatrimonial.class, BemPatrimonialDAO.class),
						Dep.of("composicaoList", Composicao.class, ComposicaoDAO.class).setCampoPai("produto"),
						Dep.of("produtoPessoaList", ProdutoPessoa.class, ProdutoPessoaDAO.class).setCampoPai("produto")
				));

		// instanciar os daos
		this.mapa.forEach((i) -> this.instanciaDao(i));

	}
	// @formatter:on

	public Optional<Dep<?, ?, ?, ?>> getDep(final String funcionalidadeCampo) {
		return this.mapa.stream().filter(d -> funcionalidadeCampo.equalsIgnoreCase(d.getFuncionalidadeCampo()))
				.findFirst();
	}

	private <E extends EntidadeBase, D extends JpaRepository<E, Integer>, F extends FiltroDTO, L extends ListagemDTO> void instanciaDao(
			final Dep<E, D, F, L> d) {
		d.setDao(this.instanciaBean.instanciarBean(d.getDaoClass()));
		d.getDependencias().ifPresent(c -> c.forEach(x -> this.instanciaDao(x)));
	}

}
