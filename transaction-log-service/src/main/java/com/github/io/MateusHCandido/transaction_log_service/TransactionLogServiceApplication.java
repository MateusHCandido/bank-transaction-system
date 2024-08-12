package com.github.io.MateusHCandido.transaction_log_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class TransactionLogServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(TransactionLogServiceApplication.class, args);
	}

}
