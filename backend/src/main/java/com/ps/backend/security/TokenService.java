package com.ps.backend.security;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;


import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;


import com.ps.backend.model.Usuario;



@Service
public class TokenService {

    @Value("${api.security.token.secret}")
    private String secret;

    public String gerarToken(Usuario usuario) {
        return JWT.create()
            .withSubject(usuario.getEmail())
            .withClaim("role", usuario.getRole().name())
            .withExpiresAt(dataExpiracao())
            .sign(Algorithm.HMAC256(secret));
    }

    private Instant dataExpiracao() {
        return LocalDateTime.now()
            .plusHours(2)
            .toInstant(ZoneOffset.of("-03:00"));
    }

    public String validarToken(String token) {
        try {
            return JWT.require(Algorithm.HMAC256(secret))
                .build()
                .verify(token)
                .getSubject();
        } catch (Exception e) {
            return null;
        }
    }
}