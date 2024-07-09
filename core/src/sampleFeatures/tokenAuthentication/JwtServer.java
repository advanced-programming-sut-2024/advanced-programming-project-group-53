package sampleFeatures.tokenAuthentication;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;

public class JwtServer {

    private static final String SECRET_KEY = "mySecretKey";
    private static final long EXPIRATION_TIME = 15 * 60 * 1000;

    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(12345)) {
            System.out.println("Server is listening on port 12345");

            while (true) {
                Socket socket = serverSocket.accept();
                System.out.println("New client connected");
                new Thread(new JwtClientHandler(socket)).start();
            }

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    // Generates a JWT token
    public static String generateToken(String subject) {
        Algorithm algorithm = Algorithm.HMAC256(SECRET_KEY);
        return JWT.create()
                .withSubject(subject)
                .withIssuedAt(new Date())
                .withExpiresAt(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .sign(algorithm);
    }

    // Validates a JWT token
    public static boolean validateToken(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(SECRET_KEY);
            JWTVerifier verifier = JWT.require(algorithm).build();
            DecodedJWT jwt = verifier.verify(token);
            return !jwt.getExpiresAt().before(new Date());
        } catch (JWTVerificationException exception) {
            exception.printStackTrace(); // Print stack trace for debugging
            return false;
        }
    }
}

class JwtClientHandler implements Runnable {
    private final Socket socket;

    public JwtClientHandler(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try (InputStream input = socket.getInputStream();
             OutputStream output = socket.getOutputStream();
             BufferedReader reader = new BufferedReader(new InputStreamReader(input));
             PrintWriter writer = new PrintWriter(output, true)) {
             while (true) {
                 String token = reader.readLine();
                 if (JwtServer.validateToken(token)) {
                     writer.println("Token is valid");
                 } else {
                     writer.println("Token is invalid or expired");
                     break;
                 }
             }

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}