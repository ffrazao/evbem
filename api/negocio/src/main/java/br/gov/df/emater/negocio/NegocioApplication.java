package br.gov.df.emater.negocio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = { "br.gov.df.emater", "br.com.frazao.cadeiaresponsabilidade" })
public class NegocioApplication implements ApplicationRunner {

	@Autowired
	private NegocioFacade negocio;

	public static void main(final String[] args) {
		SpringApplication.run(NegocioApplication.class, args);
	}

	@Override
	public void run(ApplicationArguments args) throws Exception {
		this.negocio.executarSomenteLeitura("veiculo");
	}

}
