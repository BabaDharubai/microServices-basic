package com.bookapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableEurekaClient
public class BookappCloudGatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(BookappCloudGatewayApplication.class, args);
	}

	@Bean
	public RouteLocator customRoutes(RouteLocatorBuilder builder) {
		return builder.routes()
		//create individual routes - id,uri,predicates
		//for bookviewservice
		.route("bookviewService", ps->ps.path("/book-view/**").uri("lb://BOOK-VIEW-SERVICE:"))
		//for cart service
		.route("cartService",ps->ps.path("/cart-api/**").uri("lb://BOOK-CART-SERVICE:"))
		.build();
		
	}
	
}
