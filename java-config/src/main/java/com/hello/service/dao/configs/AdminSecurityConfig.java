package com.hello.service.dao.configs;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity

public class AdminSecurityConfig {
	@Bean
	PasswordEncoder encoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	SecurityFilterChain homeFilter(HttpSecurity http) throws Exception {

		http.securityMatcher("/").authorizeHttpRequests(request -> {
			request.anyRequest().permitAll();
		});
		return http.build();
	}

	@Bean
	SecurityFilterChain resourceFilter(HttpSecurity http) throws Exception {

		http.securityMatcher("/resources/**").authorizeHttpRequests(request -> {
			request.anyRequest().permitAll();
		});
		return http.build();
	}

	@Bean
	SecurityFilterChain pathFilter(HttpSecurity http) throws Exception {

		http.authorizeHttpRequests(request -> {
			request.requestMatchers("/admin/**").hasAuthority("Admin");
			request.anyRequest().denyAll();
		});
		http.formLogin(Customizer.withDefaults());
		http.logout(Customizer.withDefaults());
		return http.build();
	}

	@Bean
	AuthenticationManager manager(HttpSecurity http) {
		var builder = http.getSharedObject(AuthenticationManagerBuilder.class);
		builder.authenticationProvider(provider(encoder()));

		return builder.getOrBuild();
	}

	@Bean
	AuthenticationProvider provider(PasswordEncoder encoder) {
		var provider = new DaoAuthenticationProvider(encoder);
		provider.setUserDetailsService(inMemoryUserDetailsManager());
		provider.setPasswordEncoder(encoder);
		return provider;

	}

	InMemoryUserDetailsManager inMemoryUserDetailsManager() {

		var user = new InMemoryUserDetailsManager(List.of(User.withUsername("admin@gmail.com")
				.password("$2a$10$C.45wHXdZ2A9LJb1CEeEO.wuHs83RVfC9zjGNQEvaxq/J0bEiQR5K").authorities("Admin")
				.build()));
		return user;

	}
}
