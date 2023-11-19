package com.maki.springCampsite;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.maki.springCampsite")
public class SpringCampsiteApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringCampsiteApplication.class, args);
	}

}
