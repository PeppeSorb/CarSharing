package com.stefanogiuseppe.carsharing.config;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;

public class JwtTokenProvider {

    private static final String SECRET_KEY = "segreto"; // Chiave segreta per firmare il token JWT

    public String generateToken(String userId, String role) {
        // Calcola la data di scadenza del token (es. 1 ora dopo l'emissione)
        Date expirationDate = new Date(System.currentTimeMillis() + 3600 * 1000);

        // Genera il token JWT utilizzando la libreria jjwt
        String token = Jwts.builder()
                .setSubject(userId)
                .claim("role", role)
                .setExpiration(expirationDate)
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
                .compact();

        return token;
    }
}
