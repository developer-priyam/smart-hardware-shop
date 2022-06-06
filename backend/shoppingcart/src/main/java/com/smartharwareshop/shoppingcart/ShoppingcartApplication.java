package com.smartharwareshop.shoppingcart;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class ShoppingcartApplication {
	public static void main(String[] args) {
		SpringApplication.run(ShoppingcartApplication.class, args);
	}
}
