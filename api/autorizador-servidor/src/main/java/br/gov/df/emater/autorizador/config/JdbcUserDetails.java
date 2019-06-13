package br.gov.df.emater.autorizador.config;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

/**
 * Created by Frazão
 */
public class JdbcUserDetails implements UserDetailsService {

//	@Autowired
//	private UsuarioDAO dao;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//		Usuario usuario = dao.findByLogin(username);
//
//		if (usuario == null) {
//			throw new UsernameNotFoundException("Usuário " + username + " não encontrado!");
//		}
//
//		User user = new User(username, usuario.getSenha(), usuario.getAtivo() == Confirmacao.S, true, true, true,
//				new ArrayList<GrantedAuthority>());
//
//		return user;
		return null;
	}

	public static void main(String[] args) {
		org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder e = new org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder();
		System.out.println(e.encode(new String("df_rural_mobile")));
	}

}
