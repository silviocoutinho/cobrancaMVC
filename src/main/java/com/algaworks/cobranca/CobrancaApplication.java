package com.algaworks.cobranca;

import java.util.Locale;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.i18n.FixedLocaleResolver;


@SpringBootApplication
public class CobrancaApplication {

	public static void main(String[] args) {
		SpringApplication.run(CobrancaApplication.class, args);
	}
	
	@Bean
	public FixedLocaleResolver fixedLocaleResolver() {
	    FixedLocaleResolver resolver = new FixedLocaleResolver();
	    resolver.setDefaultLocale(new Locale("pt", "BR"));
	    return resolver;
	}
}
