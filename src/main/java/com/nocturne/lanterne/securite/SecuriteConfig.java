package com.nocturne.lanterne.securite;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

/** Deux clients de démonstration (mot de passe « manoir »), et une API réservée aux clients connectés. */
@Configuration
public class SecuriteConfig {

    @Bean
    SecurityFilterChain api(HttpSecurity http) throws Exception {
        return http.authorizeHttpRequests(a -> a.anyRequest().authenticated())
                .httpBasic(Customizer.withDefaults())
                .csrf(c -> c.disable())
                .build();
    }

    @Bean
    UserDetailsService clients() {
        return new InMemoryUserDetailsManager(
                User.withUsername("ada@exemple.test").password("{noop}manoir").roles("CLIENT").build(),
                User.withUsername("linus@exemple.test").password("{noop}manoir").roles("CLIENT").build());
    }
}
