package com.nekiSkills.NekiSkills.domain.security;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nekiSkills.NekiSkills.domain.model.Usuario;

@Component
public class JwtUtil {

    // Le a propriedade jwt-secret a partir do application.properties
    @Value("${jwt_secret}")
    private String secret;

    // Le a propriedade jwt-subject a partir do application.properties
    @Value("${jwt-subject}")
    private String subject;

    // Le a propriedade jwt-company-project-name a partir do application.properties
    @Value("${jwt-company-project-name}")
    private String companyProjectName;

    // Metodo utilizado no login e para criar o JWT contendo alguns dados gerais e o
    // e-mail do usuario
    public String generateToken(String login)
            throws IllegalArgumentException, JWTCreationException {
        return JWT
                .create()
                .withSubject(subject)
                .withClaim("login", login)
                .withIssuedAt(new Date())
                .withIssuer(companyProjectName)
                .sign(Algorithm.HMAC256(secret));
    }

    // Metodo utilizado no login e para criar o JWT contendo todos os dados do
    // usuario, exceto sua senha
    public String generateTokenWithUserData(Usuario usuario)
            throws IllegalArgumentException, JWTCreationException {
        ObjectMapper mapper = new ObjectMapper();
        String usuarioJson = null;
        try {
            usuarioJson = mapper.writeValueAsString(usuario);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return JWT
                .create()
                .withSubject(subject)
                .withClaim("user", usuarioJson)
                .withIssuedAt(new Date())
                .withIssuer(companyProjectName)
                .sign(Algorithm.HMAC256(secret));
    }

    // Metodo para verificar o JWT e extrair as informacoes contidas no mesmo (no
    // payload)
    public String validateTokenAndRetrieveSubject(String token) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        JWTVerifier verifier = JWT
                .require(Algorithm.HMAC256(secret))
                .withSubject(subject)
                .withIssuer(companyProjectName)
                .build();
        DecodedJWT jwt = verifier.verify(token);

        // Pegando o email dentro da chave usuario (string json que contem os dados do
        // usuario)
        Usuario usuario = new Usuario();
        try {
            usuario = mapper.readValue(jwt.getClaim("usuario").asString(), Usuario.class);
        } catch (JsonProcessingException e) {
            throw new Exception(
                    "Ocorreu um erro e nao foi possivel converter o usario a partir da string json - " +
                            e);
        }
        return usuario.getLogin();
    }
}
