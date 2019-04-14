package ma.tiwtiw.bank.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.util.Date;
import lombok.extern.slf4j.Slf4j;
import ma.tiwtiw.bank.entity.User;
import ma.tiwtiw.bank.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

@Slf4j
public class JwtUtil {

  public static final long EXPIRATION_TIME = 864_000_000; // 10 days
  public static final String TOKEN_SECRET = "bank-management-secret";
  public static final String TOKEN_PREFIX = "Bearer";
  public static final String HEADER_STRING = "Authorization";

  public static String getToken(User user) {
    log.debug("getToken() :: user = {}", user);
    String token = Jwts.builder()
        .setSubject(user.getUsername())
        .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
        .signWith(SignatureAlgorithm.HS512, TOKEN_SECRET)
        .compact();

    log.debug("getToken() :: user = {}, token = {}", user, token);

    return token;
  }

  public static String parse(String token) {
    log.debug("parse() :: token = {}", token);
    Claims claims = Jwts.parser()
        .setSigningKey(TOKEN_SECRET)
        .parseClaimsJws(token.substring(TOKEN_PREFIX.length()))
        .getBody();

    return claims.getSubject();
  }
}
