package br.gov.df.emater.repositorio_principal;

import java.io.IOException;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.gov.df.emater.repositorio_principal.dao.principal.PessoaDAO;
import br.gov.df.emater.repositorio_principal.entidade.principal.Pessoa;

@SpringBootApplication(scanBasePackages = "br.gov.df.emater")
public class RepositorioPrincipalApplication /* implements CommandLineRunner */ {

	public static void main(final String[] args) throws IOException {
		SpringApplication.run(RepositorioPrincipalApplication.class, args);

		// @formatter:off
		/*

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
		 */
		// @formatter:on
	}

	@Autowired
	private PessoaDAO dao;

	// @Override
	@Transactional
	public void run(final String... args) throws Exception {
		final Optional<Pessoa> t = this.dao.findById(1);
		System.out.println(new ObjectMapper().writeValueAsString(t.orElse(null)));
	}

}
