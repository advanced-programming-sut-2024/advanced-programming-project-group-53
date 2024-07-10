package network;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;
import model.game.User;

import java.util.Date;

public class MyJWT {
    private static final long EXPIRATION_TIME = 15 * 60 * 1000;
    private static final String SUBJECT = "expireAfter15min";

    public static String generateToken(String username) {
        Algorithm algorithm = Algorithm.HMAC256(username);
        return JWT.create()
                .withSubject(SUBJECT)
                .withIssuedAt(new Date())
                .withExpiresAt(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .sign(algorithm);
    }

    public static boolean validateToken(String username) {
        String token = User.findUser(username).token();
        boolean result = false;
        if (token != null) {
            try {
                Algorithm algorithm = Algorithm.HMAC256(username);
                JWTVerifier verifier = JWT.require(algorithm).build();
                DecodedJWT jwt = verifier.verify(token);
                result = !jwt.getExpiresAt().before(new Date());
            } catch (Exception exception) {
                result = true;
                exception.printStackTrace();
            }
        }
        return result;
    }
}
