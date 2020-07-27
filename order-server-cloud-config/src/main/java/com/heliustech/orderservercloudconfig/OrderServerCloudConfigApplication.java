package com.heliustech.orderservercloudconfig;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@EnableConfigServer
@SpringBootApplication
public class OrderServerCloudConfigApplication {

	public static void main(String[] args) {
		SpringApplication.run(OrderServerCloudConfigApplication.class, args);
	}

}
