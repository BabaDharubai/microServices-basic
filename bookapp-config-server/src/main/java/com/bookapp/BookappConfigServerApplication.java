package com.bookapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@SpringBootApplication
@EnableConfigServer
public class BookappConfigServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(BookappConfigServerApplication.class, args);
	}

}
