package demo.security.service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;

import demo.security.models.Users;

@Service
public class TokenService {

    @Value("${api.security.token.secret}")
    private String secret;

    public String generateToken(Users users){
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            String token = JWT.create()
            .withIssuer("person-manager-api")
            .withSubject(users.getLogin())
            .withExpiresAt(genExpirationDate())
            .sign(algorithm);

        return token;
        } catch (JWTCreationException e) {
            throw new RuntimeException("Erro ao gerar token", e);
        }
    }

    private Instant genExpirationDate(){
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
    }

    // public String getExpirationTime(String token) {
		 
    //         // Decodifica o token JWT
    //         DecodedJWT jwt =  JWT.decode(token);
            
    //         // Obtém o tempo de expiração do token
    //         Long expirationTimeInSeconds = jwt.getExpiresAt().getTime() / 1000; // Em segundos

    //         System.out.println("Tempo de expiração do token (em segundos): " + expirationTimeInSeconds);

    //         return expirationTimeInSeconds.toString();
        

	// }

    public String validadeToken(String token){
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            return JWT.require(algorithm)
                    .withIssuer("person-manager-api")
                    .build()
                    .verify(token)
                    .getSubject();

        } catch (JWTVerificationException e) {
            return "";
        }
    }
    
}
