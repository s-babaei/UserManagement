package com.example.userManagment;

import jakarta.validation.Validator;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.validation.beanvalidation.MethodValidationPostProcessor;

@SpringBootApplication
 @EnableJpaRepositories
public class UserManagmentApplication {

	public static void main(String[] args) {
		SpringApplication.run(UserManagmentApplication.class, args);
	}


	 @Bean
	 public Validator validator() {
		 return new LocalValidatorFactoryBean();
	 }

	 @Bean
	 public MethodValidationPostProcessor methodValidationPostProcessor() {
		 MethodValidationPostProcessor processor = new MethodValidationPostProcessor();
		 processor.setValidator(validator());
		 return processor;
	 }

}
