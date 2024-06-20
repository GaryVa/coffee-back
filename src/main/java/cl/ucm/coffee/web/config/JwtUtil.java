package cl.ucm.coffee.web.config;

import cl.ucm.coffee.persitence.entity.UserRoleEntity;
import cl.ucm.coffee.persitence.repository.UserRoleRepository;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@Component
public class JwtUtil {
    private static String SECRET_KEY = "Ucm-c0ff33";
    private static Algorithm ALGORITHM = Algorithm.HMAC256(SECRET_KEY);

    @Autowired
    private UserRoleRepository userRoleRepository;

    public String create(String username) {
        UserRoleEntity userRole = userRoleRepository.findByUsername(username);
        return JWT.create()
                .withSubject(username)
                .withIssuer("ucm-coffee")
                .withIssuedAt(new Date())
                .withExpiresAt(new Date(System.currentTimeMillis() + TimeUnit.DAYS.toMillis(15)))
                .withClaim("role", userRole.getRole())
                .sign(ALGORITHM);
    }

    public boolean isValid(String jwt) {
        try {
            JWT.require(ALGORITHM)
                    .build()
                    .verify(jwt);
            return true;
        } catch (JWTVerificationException e) {
            return false;
        }
    }

    public String getUsername(String jwt) {
        return JWT.require(ALGORITHM)
                .build()
                .verify(jwt)
                .getSubject();
    }
}
