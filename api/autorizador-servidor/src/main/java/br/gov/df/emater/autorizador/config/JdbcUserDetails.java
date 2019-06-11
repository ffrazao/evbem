package br.gov.df.emater.autorizador.config;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import br.gov.df.emater.repositorio_principal.dao.sistema.UsuarioDAO;
import br.gov.df.emater.repositorio_principal.dominio.Confirmacao;
import br.gov.df.emater.repositorio_principal.entidade.sistema.Usuario;

/**
 * Created by Frazão
 */
public class JdbcUserDetails implements UserDetailsService {

	@Autowired
	private UsuarioDAO dao;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Usuario usuario = dao.findByLogin(username);

		if (usuario == null) {
			throw new UsernameNotFoundException("Usuário " + username + " não encontrado!");
		}

		User user = new User(username, usuario.getSenha(), usuario.getAtivo() == Confirmacao.S, true, true, true,
				new ArrayList<GrantedAuthority>());

		return user;
	}

	public static void main(String[] args) {
		org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder e = new org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder();
		System.out.println(e.encode(new String("df_rural_mobile")));
	}

}
