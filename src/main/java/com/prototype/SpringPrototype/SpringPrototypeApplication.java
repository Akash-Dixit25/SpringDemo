package com.prototype.SpringPrototype;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.session.jdbc.config.annotation.web.http.EnableJdbcHttpSession;

@SpringBootApplication
@EnableJdbcHttpSession
public class SpringPrototypeApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringPrototypeApplication.class, args);
	}

}
