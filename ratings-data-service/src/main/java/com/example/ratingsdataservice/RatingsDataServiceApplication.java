package com.example.ratingsdataservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
/*
 * @EnableEurekaClient: Not mandatory if discovery server is running in it's default port 8761 
 * And eureka-client is in the class path (i.e. added to pom)
*/
@EnableEurekaClient
public class RatingsDataServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(RatingsDataServiceApplication.class, args);
	}

}
