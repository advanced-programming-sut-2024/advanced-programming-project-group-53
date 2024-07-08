package controller;

import model.game.User;
import model.game.ValidationRegex;
import model.view.Message;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;
import java.util.Random;
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
        if (!password.equals(confirmPassword))
            return Message.PASSWORD_IS_NOT_THE_SAME.message();
        else {
            new User(username, nickname, email, password, question, answer);
            return "empty";
        }
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
            result.append("\n");
            result.append(password).append("@\n");
            result.append("\n");
            result.append(password).append("#\n");
        }
        return result.toString();
    }
    public String sendAuthorizationEmail(String username, String toEmail){
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
        return code;
    }
}
