package com.happysoul.home;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(value="com.happysoul.home")
public class HomeMondyApplication {

	public static void main(String[] args) {
		SpringApplication.run(HomeMondyApplication.class, args);
	}

}
