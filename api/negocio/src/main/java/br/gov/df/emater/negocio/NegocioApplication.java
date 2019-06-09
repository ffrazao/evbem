package br.gov.df.emater.negocio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import br.com.frazao.cadeiaresponsabilidade.ContextoBase;
import br.gov.df.emater.negocio.sistema.usuario.UsuarioAbrirCdSq;

@SpringBootApplication
@EnableAutoConfiguration
@ComponentScan("br.gov.df.emater")
public class NegocioApplication implements CommandLineRunner {

	@Autowired
	private UsuarioAbrirCdSq cadeia;

	public static void main(String[] args) {
		SpringApplication.run(NegocioApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		cadeia.executar(new ContextoBase<String, Object>());
	}

}
