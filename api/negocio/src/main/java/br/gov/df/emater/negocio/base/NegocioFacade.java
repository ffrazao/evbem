package br.gov.df.emater.negocio.base;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.frazao.cadeiaresponsabilidade.Biblioteca;
import br.com.frazao.cadeiaresponsabilidade.BibliotecaSpring;
import br.com.frazao.cadeiaresponsabilidade.Comando;
import br.com.frazao.cadeiaresponsabilidade.Contexto;
import br.com.frazao.cadeiaresponsabilidade.ContextoBase;

@Service
public class NegocioFacade {

	private final BibliotecaSpring biblioteca;

	@Autowired
	public NegocioFacade(final BibliotecaSpring biblioteca) throws Exception {
		this.biblioteca = biblioteca;
		((Biblioteca) this.biblioteca).carregar(NegocioFacade.class);
	}

	private Object executar(final String comandoNome, final Object requisicao, final Principal usuario,
			Contexto<String, Object> contexto) throws NegocioException {
		Comando comando;
		try {
			comando = this.biblioteca.instanciar("crud", comandoNome);
			if (comando == null) {
				throw new NegocioException("Comando não reconhecido pelo sistema [%s]", comandoNome);
			}
			if (contexto == null) {
				contexto = new ContextoBase<>();
			}
			contexto.put("comando", comando.getClass().getName());
			contexto.setRequisicao(requisicao);
			contexto.put("usuario", usuario);

			comando.executar(contexto);
		} catch (final Exception e) {
			throw new NegocioException(e);
		}

		return contexto.getResposta();
	}

	@Transactional
	public Object executarComEscrita(final String comando) throws NegocioException {
		return this.executar(comando, null, null, null);
	}

	@Transactional
	public Object executarComEscrita(final String comando, final Object requisicao) throws NegocioException {
		return this.executar(comando, requisicao, null, null);
	}

	@Transactional
	public Object executarComEscrita(final String comando, final Object requisicao, final Principal usuario)
			throws NegocioException {
		return this.executar(comando, requisicao, usuario, null);
	}

	@Transactional
	public Object executarComEscrita(final String comando, final Object requisicao, final Principal usuario,
			final Contexto<String, Object> contexto) throws NegocioException {
		return this.executar(comando, requisicao, usuario, contexto);
	}

	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public Object executarComEscritaENovaTransacao(final String comando) throws NegocioException {
		return this.executar(comando, null, null, null);
	}

	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public Object executarComEscritaENovaTransacao(final String comando, final Object requisicao)
			throws NegocioException {
		return this.executar(comando, requisicao, null, null);
	}

	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public Object executarComEscritaENovaTransacao(final String comando, final Object requisicao,
			final Principal usuario) throws NegocioException {
		return this.executar(comando, requisicao, usuario, null);
	}

	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public Object executarComEscritaENovaTransacao(final String comando, final Object requisicao,
			final Principal usuario, final Contexto<String, Object> contexto) throws NegocioException {
		return this.executar(comando, requisicao, usuario, contexto);
	}

	@Transactional(readOnly = true, propagation = Propagation.REQUIRES_NEW)
	public Object executarSomenteLeitura(final String comando) throws NegocioException {
		return this.executar(comando, null, null, null);
	}

	@Transactional(readOnly = true, propagation = Propagation.REQUIRES_NEW)
	public Object executarSomenteLeitura(final String comando, final Object requisicao) throws NegocioException {
		return this.executar(comando, requisicao, null, null);
	}

	@Transactional(readOnly = true, propagation = Propagation.REQUIRES_NEW)
	public Object executarSomenteLeitura(final String comando, final Object requisicao, final Principal usuario)
			throws NegocioException {
		return this.executar(comando, requisicao, usuario, null);
	}

	@Transactional(readOnly = true, propagation = Propagation.REQUIRES_NEW)
	public Object executarSomenteLeitura(final String comando, final Object requisicao, final Principal usuario,
			final Contexto<String, Object> contexto) throws NegocioException {
		return this.executar(comando, requisicao, usuario, contexto);
	}

}