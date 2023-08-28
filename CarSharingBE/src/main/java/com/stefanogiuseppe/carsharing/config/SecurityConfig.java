package com.stefanogiuseppe.carsharing.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
@EnableWebSecurity
public class SecurityConfig{
    public SecurityFilterChain configure(HttpSecurity http) throws Exception{
        http
                .authorizeRequests((authz) -> authz
                        //.requestMatchers("/api/admin/**").hasRole("ADMIN")
                        .requestMatchers("/api/rental/create/**").fullyAuthenticated()// .hasRole("USER")
                        //.anyRequest().authenticated()
                       // .and().formLogin("/login")
                )
                .formLogin(Customizer.withDefaults());
        return http.build();
    }
}
