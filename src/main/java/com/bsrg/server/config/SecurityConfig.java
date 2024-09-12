package com.bsrg.server.config;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.bsrg.server.models.UserRepository;
import com.bsrg.server.models.User;

@EnableWebSecurity
public class SecurityConfig {
	
	@Bean
	SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
		return http.authorizeHttpRequests(request -> request
				.anyRequest().authenticated())
		.formLogin(Customizer.withDefaults())
		.build();
	}
	
	@Bean
	UserDetailsService userDetailsService(UserRepository userRepo) {
		return username ->{
			User user = userRepo.findByUsername(username);
			if(user!=null) {
				return user;
			}else {
				throw new UsernameNotFoundException("User : " + username + " not found");
			}
			
		};
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	
	
}
