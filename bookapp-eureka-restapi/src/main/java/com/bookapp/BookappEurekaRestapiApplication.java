package com.bookapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class BookappEurekaRestapiApplication {

	public static void main(String[] args) {
		SpringApplication.run(BookappEurekaRestapiApplication.class, args);
	}

}
