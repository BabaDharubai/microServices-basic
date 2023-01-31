package com.bookapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableEurekaClient
public class BookappBookviewServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(BookappBookviewServiceApplication.class, args);
	}
	
	@Bean
	//this is a load balancer client pick and choose the service
	@LoadBalanced
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}

}
