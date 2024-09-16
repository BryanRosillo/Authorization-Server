package com.bsrg.server.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import com.bsrg.server.models.UserRepository;
import com.bsrg.server.models.User;


@Configuration
public class SecurityConfig {
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public UserDetailsService userDetailsService(UserRepository userRepo) {
		return username ->{
			User user = userRepo.findByUsername(username);
			if(user!=null) {
				return user;
			}else {
				throw new UsernameNotFoundException("User : " + username + " not found");
			}
			
		};
	}
	
	/*IMPORTANT: ONLY FOR DEVELOPMENT THE CSRF TOKEN IS DISABLED.*/
	@Bean
	public SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
		return http.authorizeHttpRequests(request -> request
				.anyRequest().authenticated())
				.formLogin(Customizer.withDefaults())
				.csrf(csrf -> csrf
						.disable())
				.headers(headers -> headers
						.frameOptions(origin -> origin
								.sameOrigin()))
				.build();
	}
	


}
