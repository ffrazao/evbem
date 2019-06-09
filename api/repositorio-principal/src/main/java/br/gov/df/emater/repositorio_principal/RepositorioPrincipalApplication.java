package br.gov.df.emater.repositorio_principal;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import br.gov.df.emater.repositorio_principal.dao.comum.LocalizacaoTipoDAO;
import br.gov.df.emater.repositorio_principal.dao.sistema.UsuarioDAO;
import br.gov.df.emater.repositorio_principal.entidade.comum.LocalizacaoTipo;

@SpringBootApplication
@EnableAutoConfiguration
@ComponentScan("br.gov.df.emater")
@EntityScan("br.gov.df.emater.repositorio_principal.entidade")
@EnableJpaRepositories("br.gov.df.emater.repositorio_principal.dao")
public class RepositorioPrincipalApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(RepositorioPrincipalApplication.class, args);
	}

	@Autowired
	private LocalizacaoTipoDAO dao;
	
	@Autowired
	private UsuarioDAO uDao;

	@Override
	public void run(String... args) throws Exception {
		uDao.count();
		dao.deleteAll();
		if (dao.existsById(1)) {
			System.out.println("apagando");
			dao.deleteById(1);
		} else {
			System.out.println("incluindo");
			LocalizacaoTipo t = new LocalizacaoTipo();
			t.setId(1);
			t.setNome("Teste");
			dao.save(t);
		}
		Optional<LocalizacaoTipo> t = dao.findById(1);
		System.out.println(t);
	}

}
