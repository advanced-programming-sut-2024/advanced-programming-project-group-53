package sampleFeatures.tokenAuthentication;
import java.io.*;
import java.net.Socket;

public class JwtClient {

    public static void main(String[] args) {
        String token = JwtServer.generateToken("expireAfter15min");

        try (Socket socket = new Socket("localhost", 12345);
             OutputStream output = socket.getOutputStream();
             InputStream input = socket.getInputStream();
             PrintWriter writer = new PrintWriter(output, true);
             BufferedReader reader = new BufferedReader(new InputStreamReader(input))) {
             while (true) {
                 writer.println(token);
                 String response = reader.readLine();
                 System.out.println("Server response: " + response);
                 if (response.equalsIgnoreCase("Token is invalid or expired")) {
                     socket.close();
                     break;
                 }
             }

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
