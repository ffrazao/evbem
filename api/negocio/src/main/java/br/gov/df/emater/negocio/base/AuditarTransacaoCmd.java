package br.gov.df.emater.negocio.base;

import java.security.Principal;
import java.util.Arrays;
import java.util.Collection;
import java.util.function.Consumer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.frazao.cadeiaresponsabilidade.Comando;
import br.com.frazao.cadeiaresponsabilidade.Contexto;
import br.gov.df.emater.repositorio_principal.dao.sistema.UsuarioDAO;
import br.gov.df.emater.repositorio_principal.entidade.Auditavel;
import br.gov.df.emater.repositorio_principal.entidade.Identificavel;
import br.gov.df.emater.repositorio_principal.entidade.sistema.Usuario;

@Component
public class AuditarTransacaoCmd extends Comando {

	private Consumer<Object> auditarSePossivel = (obj) -> {
		if (obj instanceof Auditavel) {
			if (obj instanceof Identificavel) {
				if (((Identificavel) obj).getId() == null) {
					((Auditavel) obj).setCriadoUsuarioId(this.usuario.getId());
				}
			} else {
				((Auditavel) obj).setCriadoUsuarioId(this.usuario.getId());
			}
			((Auditavel) obj).setAtualizadoUsuarioId(this.usuario.getId());
		}
	};

	@Autowired
	private UsuarioDAO dao;

	private Usuario usuario;

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	protected void procedimento(Contexto<?, ?> contexto) throws Exception {
		if (contexto.get("usuario") != null) {
			usuario = dao.findByLogin(((Principal) contexto.get("usuario")).getName());
			Object req = contexto.getRequisicao();
			if (req instanceof Object[]) {
				Arrays.stream((Object[]) req).forEach(auditarSePossivel);
			} else if (req instanceof Collection) {
				((Collection) req).stream().forEach(auditarSePossivel);
			} else {
				auditarSePossivel.accept(req);
			}
		}
	}

}
