package com.example.hazelcast.embedded;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@EnableCaching
@SpringBootApplication
public class HazelcastEmbeddedApplication {

	public static void main(String[] args) {
		SpringApplication.run(HazelcastEmbeddedApplication.class, args);
	}

}
