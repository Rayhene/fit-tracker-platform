package br.com.aps.fittracker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

//@EnableEurekaClient
@EnableDiscoveryClient
@SpringBootApplication
public class FitTrackerApplication {

	public static void main(String[] args) {
		SpringApplication.run(FitTrackerApplication.class, args);
	}

}
