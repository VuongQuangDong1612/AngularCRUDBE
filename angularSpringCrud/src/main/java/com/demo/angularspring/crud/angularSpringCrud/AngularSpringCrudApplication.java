package com.demo.angularspring.crud.angularSpringCrud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

@SpringBootApplication
@ImportResource("classpath:service-context.xml")
public class AngularSpringCrudApplication {

	public static void main(String[] args) {
		SpringApplication.run(AngularSpringCrudApplication.class, args);
	}

}
