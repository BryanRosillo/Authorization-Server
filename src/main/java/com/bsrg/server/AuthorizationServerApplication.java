package com.bsrg.server;

import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.bsrg.server.models.User;
import com.bsrg.server.models.UserRepository;


@SpringBootApplication(scanBasePackages= {"com.bsrg.server.config"})
public class AuthorizationServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(AuthorizationServerApplication.class, args);
	}
	
	@Bean
	public ApplicationRunner dataLoader(UserRepository userRepo, PasswordEncoder passwordEncoder) {
		return args ->{
			userRepo.save(new User("admin01",passwordEncoder.encode("password"), "ROLE_ADMIN"));
			userRepo.save(new User("admin02",passwordEncoder.encode("password"), "ROLE_ADMIN"));
		};
	}
	
	

}
