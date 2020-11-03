package by.bozhko.tg.bot.security;

import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.binary.Base64;
import org.springframework.stereotype.Component;

import java.time.Instant;

@Component
@Slf4j
public class JwtService {

    public String createToken(String login, String password, Instant expireAt) {

        String encodedPassword = new String(Base64.encodeBase64(password.getBytes()));

        return Jwts.builder()
            .setSubject(login)
            .signWith(SignatureAlgorithm.HS512, encodedPassword)
            .setExpiration(expireAt.)
            .compact();
    }

    public boolean isValid(String token, String password) {

        String encodedPassword = new String(Base64.encodeBase64(password.getBytes()));

        try {
            Jwts.parser().setSigningKey(encodedPassword).parseClaimsJws(token);
            return true;
        } catch (JwtException exception) {
            log.error(exception.getMessage());
            return false;
        }
    }

    public String getUsername(String token, String password) {
        String secret2 = new String(Base64.encodeBase64(secret.getBytes()));
        return Jwts.parser().setSigningKey(secret2).parseClaimsJws(token).getBody().getSubject();
    }
}
