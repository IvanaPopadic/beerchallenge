package com.ivpo.beerchallenge;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
//@EnableAspectJAutoProxy
public class BeerChallengeApplication {

	public static void main(String[] args) {
		SpringApplication.run(BeerChallengeApplication.class, args);
	}

}
