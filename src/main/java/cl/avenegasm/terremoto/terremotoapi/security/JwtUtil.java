package cl.avenegasm.terremoto.terremotoapi.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Function;

/**
 * @author Alejandro Venegas
 * @version 1.0.0
 * @since 1.0.0
 */
@Component
public class JwtUtil {

    @Value("${terremoto.sign-jwt}")
    private String signingKey;

    private static final long TOKEN_VALIDITY = 60 * 60;

    /**
     *
     * @param jwt
     * @return
     */
    public String getUsernameFromJwt(String jwt) {
        return (String) getClaim(jwt, (Claims c)-> c.get("rutUsuario"));
    }

    /**
     *
     * @param jwt
     * @return
     */
    public UserData parseToUserData(String jwt) {
        Claims claims = getClaims(jwt);
        Function<Claims, UserData> function = UserDataTokenParser.buildUserDataFunction();
        return function.apply(claims);
    }

    /**
     *
     * @param token
     * @param claimsTFunction
     * @param <T>
     * @return
     */
    private <T> T getClaim(String token, Function<Claims, T> claimsTFunction) {
        Claims claims = getClaims(token);
        return claimsTFunction.apply(claims);
    }

    /**
     *
     * @param token
     * @return
     */
    private Claims getClaims(String token) {
        Jws<Claims> claims = Jwts.parser().setSigningKey(signingKey).parseClaimsJws(token);
        return claims.getBody();
    }

    /**
     *
     * @param token
     * @return
     */
    private Boolean isTokenExpired(String token) {
        Date expirationDate = getClaim(token, Claims::getExpiration);
        return expirationDate.before(new Date());
    }

    /**
     *
     * @param user
     * @return
     */
    public String generateNewToken(SessionUser user) {

        Map<String, Object> claims = new HashMap<>();
        Consumer<Map<String, Object>> consumer = UserDataTokenParser.setTokenClaims(user);
        consumer.accept(claims);

        return generateToken(claims,user.getDato());
    }

    /**
     *
     * @param claims
     * @param subject
     * @return
     */
    private String generateToken(Map<String, Object> claims, String subject) {
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(subject)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + TOKEN_VALIDITY * 1000))
                .signWith(SignatureAlgorithm.HS512, signingKey)
                .compact();
    }

    /**
     *
     * @param token
     * @param userDetails
     * @return
     */
    public Boolean validateToken(String token, UserDetails userDetails) {
        String username = getUsernameFromJwt(token);
        return username.equals(userDetails.getUsername());
    }

}
