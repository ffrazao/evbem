package br.gov.df.emater.rest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication(scanBasePackages = { "br.gov.df.emater", "br.com.frazao.cadeiaresponsabilidade" })
public class RestfulApplication extends SpringBootServletInitializer {

	public static void main(final String[] args) {
		SpringApplication.run(RestfulApplication.class, args);
	}

	@Override
	protected SpringApplicationBuilder configure(final SpringApplicationBuilder application) {
		return application.sources(RestfulApplication.class);
	}

}
