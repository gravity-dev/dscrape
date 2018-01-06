package com.dscrape.app.war;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

import com.dscrape.engine.Engine;

@SpringBootApplication
public class Application extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(final ApplicationContext ctx) {

		return new CommandLineRunner() {

			@Override
			public void run(String... arg0) throws Exception {
				// Start engine by creating instance;
				Engine engine = Engine.getInstance();
			}
		};

	}
}