package network;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;

import java.util.Date;

public class MyJWT {
    private static final long EXPIRATION_TIME = 15 * 60 * 1000;
    private final String SECRET_KEY;

    public MyJWT(String username) {
        this.SECRET_KEY = username;
    }

    public String generateToken(String subject) {
        Algorithm algorithm = Algorithm.HMAC256(SECRET_KEY);
        return JWT.create()
                .withSubject(subject)
                .withIssuedAt(new Date())
                .withExpiresAt(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .sign(algorithm);
    }

    public boolean validateToken(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(SECRET_KEY);
            JWTVerifier verifier = JWT.require(algorithm).build();
            DecodedJWT jwt = verifier.verify(token);
            return !jwt.getExpiresAt().before(new Date());
        } catch (Exception exception) {
            exception.printStackTrace();
            return false;
        }
    }
}
