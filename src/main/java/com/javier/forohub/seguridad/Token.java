package com.javier.forohub.seguridad;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.javier.forohub.dominio.usuario.Usuario;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class Token {

    private String secret="123456";

    public String generarToken(Usuario usuario){
        try{
            Algorithm algorithm = Algorithm.HMAC256(secret);
            return JWT.create()
                    .withIssuer("foro hub")
                    .withSubject(usuario.getLogin())
                    .withExpiresAt(LocalDateTime.now().plusHours(2)
                            .toInstant(ZoneOffset.of("-03:00")))
                    .sign(algorithm);
        }catch (JWTCreationException e){throw new RuntimeException();}
    }

    public String getSubject(String token){
        try{
            Algorithm algorithm = Algorithm.HMAC256(secret);
            return JWT.require(algorithm)
                    .withIssuer("foro hub")
                    .build()
                    .verify(token)
                    .getSubject();
        }catch (JWTVerificationException e){return null;}
    }
}