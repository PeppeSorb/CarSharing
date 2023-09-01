/*package com.stefanogiuseppe.carsharing.config;

import com.stefanogiuseppe.carsharing.service.UserLoginService;
import com.stefanogiuseppe.carsharing.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends SecurityConfigurerAdapter {
    @Autowired
    private UserLoginService userLoginService;

    @Autowired
    private PasswordEncoder passwordEncoder;


    @Bean
    public PasswordEncoder passwordEncoder() {
        return passwordEncoder();
    }


    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .requestMatchers("/api/**").permitAll(); // Consentire a tutti gli accessi a /api/**
                //.getClass("/api/rental") // Imposta la pagina di successo predefinita
                //.and()
                //.logout()
                //.logoutUrl("/logout")
                //logoutSuccessUrl("/login")
                //.and()
                //.csrf().disable(); // Disabilita CSRF per semplificare l'esempio

    }


    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userLoginService)
                .passwordEncoder(passwordEncoder);
    }
}*/

   /* @Autowired
    private UserService userService;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManagerBean(AuthenticationManagerBuilder builder) throws Exception {
        return builder
                .userDetailsService(userService.getAllUser().get(0)) // Imposta il tuo UserDetailsService personalizzato
                .passwordEncoder(passwordEncoder())
                .authenticationManager();
    }
}


/*
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
@EnableWebSecurity
public class SecurityConfig {
   /* @Bean
    public SecurityFilterChain configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests((authz) -> authz
                                .requestMatchers("/", "/home", "/public/**","/api",  "/api/**").permitAll()
                                //.requestMatchers("/api/admin/**").hasRole("ADMIN")
                                .requestMatchers("/api/rental").hasAnyRole("USER","ADMIN")
                                .requestMatchers("/api/vehicle").hasRole("ADMIN")
                                .anyRequest().authenticated()
                        // .and().formLogin("/login")
                );

        return http.build();
    }

   /* @Bean
    public UserDetailsService userDetailsService() {
        UserDetails user =
                User.withDefaultPasswordEncoder()
                        .username("user")
                        .password("password")
                        .roles("USER")
                        .build();

        UserDetails admin =
                User.withDefaultPasswordEncoder()
                        .username("admin")
                        .password("password")
                        .roles("ADMIN")
                        .build();

        return new InMemoryUserDetailsManager(user, admin);
    }
}

	/*	<dependency>
			<groupId>org.springframework.security</groupId>
			<artifactId>spring-security-web</artifactId>
		</dependency>
		<dependency>
			<groupId>org.springframework.security</groupId>
			<artifactId>spring-security-config</artifactId>
		</dependency>*/
