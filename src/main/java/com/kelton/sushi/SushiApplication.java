package com.kelton.sushi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.web.config.EnableSpringDataWebSupport;

@SpringBootApplication
@EnableSpringDataWebSupport
public class SushiApplication {

	public static void main(String[] args) {
		SpringApplication.run(SushiApplication.class, args);
	}

}
