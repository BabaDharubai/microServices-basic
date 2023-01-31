package com.bookapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class BookappEurekaRegistryApplication {

	public static void main(String[] args) {
		SpringApplication.run(BookappEurekaRegistryApplication.class, args);
	}

}
