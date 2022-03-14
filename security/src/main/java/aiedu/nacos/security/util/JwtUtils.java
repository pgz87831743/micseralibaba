package aiedu.nacos.security.util;

import aiedu.nacos.security.properties.ConfigProperties;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.time.Duration;
import java.util.Date;
import java.util.stream.Collectors;

/**
 * @author pengmf
 * @since 2022/1/12
 */
@Component
@RequiredArgsConstructor
public class JwtUtils {

    private static final SignatureAlgorithm alg = SignatureAlgorithm.HS256;
    private static final Key KEY = Keys.secretKeyFor(alg);
    private final ConfigProperties configProperties;

    private String createToken(UserDetails userDetails, Duration duration) {
        long now = System.currentTimeMillis();
        return Jwts.builder()
                .claim("authorities", userDetails.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList()))
                .claim("username", userDetails.getUsername())
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(now))
                .setExpiration(new Date(duration.toMillis() + now))
                .signWith(alg,"securitysecuritysecuritysecurity".getBytes(StandardCharsets.UTF_8))
                .compact();
    }


    public String createAccessToken(UserDetails userDetails) {
        return createToken(userDetails, configProperties.getJwt().getAccessTokenExpires());
    }

    public String createRefreshToken(UserDetails userDetails) {
        return createToken(userDetails, configProperties.getJwt().getRefreshTokenExpires());
    }

    public Object parseToken(String token) {
        return Jwts
                .parserBuilder()
                .setSigningKey("securitysecuritysecuritysecurity".getBytes(StandardCharsets.UTF_8))
                .build()
                .parse(token)
                .getBody();

    }
}
