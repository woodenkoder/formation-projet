package com.nocturne.lanterne.securite;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

/** Spring Security est installé… et laisse tout passer. */
@Configuration
public class SecuriteConfig {

    @Bean
    SecurityFilterChain securite(HttpSecurity http) throws Exception {
        return http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(regles -> regles.anyRequest().permitAll())
                .httpBasic(Customizer.withDefaults())
                .build();
    }

    /** Des comptes pour essayer en local (mot de passe : « manoir »). Jamais en production. */
    @Bean
    UserDetailsService comptes() {
        PasswordEncoder encodeur = PasswordEncoderFactories.createDelegatingPasswordEncoder();
        String motDePasse = encodeur.encode("manoir");
        return new InMemoryUserDetailsManager(
                User.withUsername("ada").password(motDePasse).roles("CLIENT").build(),
                User.withUsername("linus").password(motDePasse).roles("CLIENT").build(),
                User.withUsername("root").password(motDePasse).roles("ADMIN").build());
    }
}
