//# logging app
//logging:
//  level:
//    org:
//      springframework:
//        jdbc:
//          core:
//            JdbcTemplate: DEBUG
//            StatementCreatorUtils: TRACE
//          datasource: TRACE
//      hibernate:
//        SQL:
//          TRACE
//        type:
//          descriptor:
//            sql:
//              BasicBinder:
//                TRACE
//
//# org.springframework.jdbc.datasource
//## datasource
//spring:
//  jpa:
//    hibernate:
//      ddl-auto: none
//    properties:
//      hibernate:
//        show_sql: true
//        format_sql: true
//        use_sql_comments: true
//  datasource:
//    url: jdbc:mysql://localhost:3306?useSSL=false&useTimezone=true&serverTimezone=UTC
//    username: root
//    password: root
//    initialization-mode: never
//    continue-on-error: false

package br.gov.df.emater.repositorio_principal;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.yaml.snakeyaml.Yaml;

import br.gov.df.emater.repositorio_principal.dao.comum.LocalizacaoTipoDAO;
import br.gov.df.emater.repositorio_principal.dao.sistema.UsuarioDAO;
import br.gov.df.emater.repositorio_principal.entidade.comum.LocalizacaoTipo;

@SpringBootApplication
@EnableAutoConfiguration
@ComponentScan("br.gov.df.emater")
@EntityScan("br.gov.df.emater.repositorio_principal.entidade")
@EnableJpaRepositories("br.gov.df.emater.repositorio_principal.dao")
public class RepositorioPrincipalApplication implements CommandLineRunner {

	@SuppressWarnings("unchecked")
	public static void main(String[] args) throws IOException {

		Map<String, Object> applicationYml = null;
		try (InputStream inputStream = RepositorioPrincipalApplication.class.getClassLoader()
				.getResourceAsStream("application.yml")) {

			Yaml yaml = new Yaml();
			applicationYml = yaml.load(inputStream);

			Map<String, Object> no;

			// verificar o no spring
			no = (Map<String, Object>) applicationYml.get("spring");
			if (CollectionUtils.isEmpty(no)) {
				System.out.println("Dados de conexão ao banco de dados não definido...");
				applicationYml.put("spring", new HashMap<>());
				no = (Map<String, Object>) applicationYml.get("spring");
			}

			// verificar o subno datasource
			no = (Map<String, Object>) no.get("datasource");
			if (CollectionUtils.isEmpty(no)) {
				no = (Map<String, Object>) applicationYml.get("spring");
				no.put("datasource", new HashMap<>());
				no = (Map<String, Object>) no.get("datasource");

				System.out.println("informe os dados de conexão ao banco de dados");
				try (Scanner sc = new Scanner(System.in)) {
					sc.useDelimiter("\\s");
					String linha;
					do {
						System.out.println("url: ");
					} while (StringUtils.isEmpty(linha = sc.nextLine()));
					no.put("url", linha);
					do {
						System.out.println("username: ");
					} while (StringUtils.isEmpty(linha = sc.nextLine()));
					no.put("username", linha);
					do {
						System.out.println("password: ");
					} while (StringUtils.isEmpty(linha = sc.nextLine()));
					no.put("password", linha);
				}
				no.put("initialization-mode", "always");
				no.put("continue-on-error", false);

				System.out.format("url: [%s], username: [%s], password: [%s]", no.get("url"), no.get("username"),
						no.get("password"));
			}
			System.out.println(yaml.dump(applicationYml));
		}

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
