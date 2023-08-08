package com.hydatis.KycmicroserviceCQRS;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.hydatis.KycmicroserviceCQRS.*")
public class KycMicroserviceCqrsApplication {

	public static void main(String[] args) {

		SpringApplication.run(KycMicroserviceCqrsApplication.class, args);
	}

}
