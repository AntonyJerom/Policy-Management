package com.policymanagement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class PolicyProApplication {

	public static void main(String[] args) {
		SpringApplication.run(PolicyProApplication.class, args);
	}

}
