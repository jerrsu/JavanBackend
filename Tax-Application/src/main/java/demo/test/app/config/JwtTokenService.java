package demo.test.app.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

/**
 * @author js
 */
@Component
public class JwtTokenService implements Serializable {

    private static final long serialVersionUID = -2550185165626007488L;

    public static final long JWT_TOKEN_VALIDITY = 600; //persecond
    public static final String ROLES = "ROLES";

    @Value("${jwt.secret}")
    private String secret;

//    @Autowired
//    UserRepository userRepository;

    public String getUsernameFromToken(String token) {
     return getClaimFromToken(token, Claims::getSubject);
    }

    public Date getExpirationDateFromToken(String token) {
     return getClaimFromToken(token, Claims::getExpiration);
    }

    public List<String> getRoles(String token) {
     return getClaimFromToken(token, claims -> (List) claims.get(ROLES));
    }

    public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
     final Claims claims = getAllClaimsFromToken(token);
     return claimsResolver.apply(claims);
    }
    private Claims getAllClaimsFromToken(String token) {
     return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
    }

    private Boolean isTokenExpired(String token) {
     final Date expiration = getExpirationDateFromToken(token);
     return expiration.before(new Date());
    }

    //generate token for user
//    public Object generateToken(Authentication authentication) {
//     Map<String, Object> claims = new HashMap<>();
//     Map<String, Object> profile = new HashMap<>();
//     final UserDetails user = (UserDetails) authentication.getPrincipal();
//
//
//     final List<String> roles = authentication.getAuthorities()
//             .stream()
//             .map(GrantedAuthority::getAuthority)
//             .collect(Collectors.toList());
//
//     User tenantUser = userRepository.findByUsername(user.getUsername());
//
//     claims.put(ROLES, roles);
//     claims.put("username",user.getUsername());
//     claims.put("email", tenantUser.getEmail());
//
//     String token = generateToken(claims, user.getUsername());
//     claims.put("access_token",token);
//     return claims;
//    }
    
    private String generateToken(Map<String, Object> claims, String subject) {
     final long now = System.currentTimeMillis();
     return Jwts.builder()
             .setClaims(claims)
             .setSubject(subject)
             .setIssuedAt(new Date(now))
             .setExpiration(new Date(now + JWT_TOKEN_VALIDITY * 1000))
             .signWith(SignatureAlgorithm.HS512, secret).compact();
    }

    public Boolean validateToken(String token) {
     final String username = getUsernameFromToken(token);
     return username != null && !isTokenExpired(token);
    }
}
