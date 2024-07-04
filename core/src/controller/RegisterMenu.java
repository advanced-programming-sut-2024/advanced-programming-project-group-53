package controller;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp;
import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.googleapis.json.GoogleJsonError;
import com.google.api.client.googleapis.json.GoogleJsonResponseException;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.client.util.Base64;
import com.google.api.client.util.store.FileDataStoreFactory;
import com.google.api.services.gmail.Gmail;
import com.google.api.services.gmail.GmailScopes;
import com.google.api.services.gmail.model.Draft;
import model.game.User;
import model.game.ValidationRegex;
import model.menu.MenuName;
import view.message.MenuMessage;
import view.message.Printer;
import view.terminal.TerminalRun;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Paths;
import java.util.Properties;
import java.util.Random;
import java.util.Set;
import java.util.regex.Matcher;

public class RegisterMenu extends Menu {
    private static RegisterMenu instance;
    private String validationCode; //this is for email validation.

    private RegisterMenu() {
    }

    public static RegisterMenu getInstance() {
        if (instance == null)
            instance = new RegisterMenu();
        return instance;
    }

    public String registerValidate(String username, String nickname, String email, String password) {
        String result = usernameValidation(username) +
                nicknameValidation(nickname) +
                emailValidation(email) +
                passwordValidation(password);
        if (result.isEmpty())
            return "empty";
        else
            return result;
    }

    public String register(String username, String nickname, String email, String password, String confirmPassword,String question, String answer) {
        String result = "";
        if (!password.equals(confirmPassword)) {
            result += Message.PASSWORD_IS_NOT_THE_SAME.message();
            return result;
        }
        result +=  registerValidate(username, nickname, email, password);
        if (result.equals("empty"))
            new User(username, nickname, email, password, question, answer);
        return result;
    }

    public String usernameValidation(String username) {
        Matcher matcher = ValidationRegex.Username.getMatcher(username);
        StringBuilder result = new StringBuilder();
        if (!matcher.find())
            result.append(Message.INVALID_USERNAME.message());
        return result.toString();
    }

    public String nicknameValidation(String nickname) {
        Matcher matcher = ValidationRegex.Nickname.getMatcher(nickname);
        StringBuilder result = new StringBuilder();
        if (!matcher.find())
            result.append(Message.INVALID_NICKNAME.message());
        return result.toString();
    }

    public String emailValidation(String email) {
        Matcher matcher = ValidationRegex.Email.getMatcher(email);
        StringBuilder result = new StringBuilder();
        if (!matcher.find())
            result.append(Message.INVALID_EMAIL.message());
        return result.toString();
    }

    public String passwordValidation(String password) {
        Matcher matcher = ValidationRegex.Password.getMatcher(password);
        StringBuilder result = new StringBuilder();
        if (!matcher.find()) {
            result.append(Message.WEAK_PASSWORD.message());
            //TODO: suggest a password.
        }
        return result.toString();
    }
    public static void sendAuthorizationEmail(String username, String toEmail){
        final String from = "hgp.master@gmail.com";
        final String password = "ygxh ztnj vsid bxqx";
        String host = "smtp.gmail.com";
        String port = "587";
        Properties properties = new Properties();
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port", port);
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.ssl.protocols", "TLSv1.3");
        String subject = "Gwent authorization for user : " + username;
        String code = ((Integer)(new Random().nextInt(9000) + 1000)).toString();
        String content = "YOUR AUTHORIZATION CODE: \n" + code;
        Session session = Session.getInstance(properties, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(from, password);
            }
        });
        try {
            javax.mail.Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(from));
            message.setRecipients(javax.mail.Message.RecipientType.TO, InternetAddress.parse(toEmail));
            message.setSubject(subject);
            message.setText(content);
            Transport.send(message);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    //This method for sending email needs to google cloud credential that it needs to living out of Iran, so we won't use it in project, but I store it for future and project view.
    public static class GMailer {
        private static Credential getCredentials(final NetHttpTransport httpTransport, GsonFactory jsonFactory)
                throws IOException {
            GoogleClientSecrets clientSecrets =
                    GoogleClientSecrets.load(jsonFactory
                            , new InputStreamReader(GMailer.class.getResourceAsStream("/...json")));

            // Build flow and trigger user authorization request.
            GoogleAuthorizationCodeFlow flow = new GoogleAuthorizationCodeFlow.Builder(
                    httpTransport, jsonFactory, clientSecrets, Set.of(GmailScopes.GMAIL_SEND))
                    .setDataStoreFactory(new FileDataStoreFactory(Paths.get("tokens").toFile()))
                    .setAccessType("offline")
                    .build();
            LocalServerReceiver receiver = new LocalServerReceiver.Builder().setPort(8888).build();
            //returns an authorized Credential object.
            return new AuthorizationCodeInstalledApp(flow, receiver).authorize("user");
        }

        public void sendMail(String subject, String msg) throws Exception {
            final NetHttpTransport httpTransport = GoogleNetHttpTransport.newTrustedTransport();
            GsonFactory jsonFactory = GsonFactory.getDefaultInstance();
            Gmail service = new Gmail.Builder(httpTransport, jsonFactory, getCredentials(httpTransport, jsonFactory))
                    .setApplicationName("GWENT authorization")
                    .build();

            // Encode as MIME message
            Properties props = new Properties();
            Session session = Session.getDefaultInstance(props, null);
            MimeMessage email = new MimeMessage(session);
            email.setFrom(new InternetAddress("hgp.master@gmail.com"));
            email.addRecipient(javax.mail.Message.RecipientType.TO,
                    new InternetAddress("safariamirparsa@gmail.com"));
            email.setSubject(subject);
            email.setText(msg);
            // Encode and wrap the MIME message into a gmail message
            ByteArrayOutputStream buffer = new ByteArrayOutputStream();
            email.writeTo(buffer);
            byte[] rawMessageBytes = buffer.toByteArray();
            String encodedEmail = Base64.encodeBase64URLSafeString(rawMessageBytes);
            com.google.api.services.gmail.model.Message message = new com.google.api.services.gmail.model.Message();
            message.setRaw(encodedEmail);

            try {
                // Create the draft message
                Draft draft = new Draft();
                draft.setMessage(message);
                draft = service.users().drafts().create("me", draft).execute();
                System.out.println("Draft id: " + draft.getId());
                System.out.println(draft.toPrettyString());
            } catch (GoogleJsonResponseException e) {
                // TODO(developer) - handle error appropriately
                GoogleJsonError error = e.getDetails();
                if (error.getCode() == 403) {
                    System.err.println("Unable to create draft: " + e.getDetails());
                } else {
                    throw e;
                }
            }
        }

        public static void main(String[] args) throws Exception {
            new GMailer().sendMail("subject", "msg");
        }
    }
}
