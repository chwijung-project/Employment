package com.chwimong.project.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableMongoRepositories(basePackages = "com.chwimong.project")
@ComponentScan("com.chwimong.project")
@EnableScheduling
public class ChwiMongApplication {

	public static void main(String[] args) {
		SpringApplication.run(ChwiMongApplication.class, args);
	}

}
