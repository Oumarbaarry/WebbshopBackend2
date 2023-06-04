package com.example.orderservice.security;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import static org.springframework.security.config.Customizer.withDefaults;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeRequests((requests) -> requests
                        .requestMatchers("/orders/buy").hasRole("USER")
                        .anyRequest().permitAll())
                .httpBasic(withDefaults());
        return http.build();
    }


    @Bean
    public UserDetailsService userDetailsService() {
        UserDetails user = User.withUsername("user")
                .password("ThePassword")
                .roles("USER")
                .build();

        return new InMemoryUserDetailsManager(user);
    }
}