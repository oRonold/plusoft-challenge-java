package br.com.fiap.challenge.sprint1.service;

import br.com.fiap.challenge.sprint1.model.usuario.Usuario;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.Instant;

@Service
public class TokenService {

    @Value("${api.token.secret}")
    private String senhaToken;

    public String criarToken(Usuario usuario){
        try {
            Algorithm algoritmo = Algorithm.HMAC256(senhaToken);
            return JWT.create()
                    .withIssuer("PLUSOFT API")
                    .withSubject(usuario.getEmail())
                    .withExpiresAt(Instant.now().plus(Duration.ofHours(2)))
                    .sign(algoritmo);
        } catch (JWTCreationException e){
            throw new RuntimeException("Erro ao gerar token JWT");
        }
    }

    public String getSubject(String token) {
        try {
            var algoritmo = Algorithm.HMAC256(token);
            System.out.println(token);
            return JWT.require(algoritmo)
                    .withIssuer("PLUSOFT API")
                    .build()
                    .verify(token)
                    .getSubject();
        } catch (JWTVerificationException exception){
            throw new RuntimeException("Token JWT inválido ou expirado");
        }
    }
}
