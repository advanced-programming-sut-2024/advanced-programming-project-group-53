package authorization;

import controller.RegisterMenu;

public class testSendingEmail {
    public static void main(String[] args) {
        String toEmail = "safariamirparsa@gmail.com";
        String username = "safar";
        RegisterMenu.sendAuthorizationEmail(username, toEmail);
    }
}
