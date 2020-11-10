package com.example.moviecatalogservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

@SpringBootApplication
/*
 * @EnableEurekaClient: Not mandatory if discovery server is running in it's default port 8761 
 * And eureka-client is in the class path (i.e. added to pom)
*/
@EnableEurekaClient 
public class MovieCatalogServiceApplication {

	@Bean
	@LoadBalanced // To make RestTemplate has client load balancing capabilities
	public RestTemplate grtRestTemplate() {
		return new RestTemplate();
	}
	
	@Bean
	public WebClient.Builder getWebClientBuilder(){
		return WebClient.builder();
	}
	
	public static void main(String[] args) {
		SpringApplication.run(MovieCatalogServiceApplication.class, args);
	}

}
