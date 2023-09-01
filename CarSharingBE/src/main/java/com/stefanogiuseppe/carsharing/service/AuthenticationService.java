package com.stefanogiuseppe.carsharing.service;

import com.stefanogiuseppe.carsharing.config.JwtTokenProvider;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.security.authentication.AuthenticationManager;


@Service
public class AuthenticationService {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;



    @Autowired
    private UserService userService;

    public String authenticateUser(String email, String password) {
        System.out.println("entro");
        org.springframework.security.core.Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(email, password)
        );
        return jwtTokenProvider.generateToken(authentication.getName(), (String) authentication.getCredentials());
    }

   /* public String generateAccessToken(Authentication authentication) {
        String username=authentication.getClass().getName();
        String password=authentication.getClass().;
        UserDetails userDetails = (UserDetails) authentication;
        return jwtTokenProvider.generateToken(userDetails.getUsername(), userDetails.getPassword());
    }*/
}
