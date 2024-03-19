package com.sistema.blog.api.security;

import com.sistema.blog.api.exceptions.BlogAppException;
import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

// ESTA CLASE PERMITE CREA, VALIDAR Y T*DO LO RELACIONADO AL TOKEN
@Component
public class JwtTokenProvider {

    @Value("${jwt.expirationMs}")
    private int jwtExpirationMs;

    private Key secretKey;

    @PostConstruct
    protected void init() {
        // Inicializar la clave secreta al cargar la clase
        // Generar una clave segura para el algoritmo HS512
        this.secretKey = Keys.secretKeyFor(SignatureAlgorithm.HS512);

    }

    //Genera el token JWT
    public String generateToken(Authentication authentication) {
        String username = authentication.getName();
        Date currentDate = new Date();
        Date expirationDate = new Date(currentDate.getTime() + jwtExpirationMs);

        String token = Jwts.builder().setSubject(username).setIssuedAt(currentDate).setExpiration(expirationDate).signWith(SignatureAlgorithm.HS512, secretKey).compact();

        return token;
    }

    public String getUsernameByJwt(String token) {
        Claims claims = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody();
        return claims.getSubject();
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token);
            return true;
           /* Jwts.parserBuilder().setSigningKey(jwtSecret).build().parseCla
            imsJws(token);*/
        } catch (SignatureException ex) {
            throw new BlogAppException(HttpStatus.BAD_REQUEST, "Invalid JWT token");
        } catch (MalformedJwtException ex) {
            throw new BlogAppException(HttpStatus.BAD_REQUEST, "Invalid JWT token: {}", ex.getMessage());
        } catch (ExpiredJwtException ex) {
            throw new BlogAppException(HttpStatus.BAD_REQUEST, "JWT token is expired");
        } catch (UnsupportedJwtException ex) {
            throw new BlogAppException(HttpStatus.BAD_REQUEST, "JWT token is unsupported");
        } catch (IllegalArgumentException ex) {
            throw new BlogAppException(HttpStatus.BAD_REQUEST, "The string claims is empty");
        }
    }
}