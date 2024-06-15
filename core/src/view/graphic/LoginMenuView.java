package view.graphic;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import game.GWENT;
import controller.LoginMenu;

public class LoginMenuView extends MenuView {
    private final Table textTable;
    private final Table loginTable;
    private final Table registerTable;
    private final Label loginErrorMessage;
    private final TextField username;
    private final TextField password;
    private final Image login;
    private final Image forgetPassword;
    private final Image exitMenu;
    private final Image exitGame;
    private final Image registerMenu;

    public LoginMenuView(GWENT game) {
        super(game);
        menu = LoginMenu.getInstance();
        loginTable = new Table();
        loginTable.setBounds(50, 50, 400, 400 / 1.48f);
        loginTable.align(Align.center);
        textTable = new Table();
        textTable.setBounds(635, 170, 400, 400);
        textTable.align(Align.center);
        registerTable = new Table();
        registerTable.setBounds(574, 50, 400, 400 / 5.92f);
        registerTable.align(Align.center);
        loginErrorMessage = new Label("login error message", label);//TODO: handle message.
        username = new TextField("", textField);
        username.setMessageText("username");
        password = new TextField("", textField);
        password.setMessageText("password");
        password.setPasswordCharacter('*');
        password.setPasswordMode(true);
        login = new Image(new Texture("button/loginOn.png"));
        login.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                ((LoginMenu) menu).login(username.getText(), password.getText());
                username.setText("");
                password.setText("");
            }
        });
        forgetPassword = new Image(new Texture("button/forgetPasswordOn.png"));
        forgetPassword.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.changeScreen(new ForgetPasswordView(game));
            }
        });
        exitMenu = new Image(new Texture("button/exitMenuOn.png"));
        exitMenu.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                System.exit(0);
            }
        });
        exitGame = new Image(new Texture("button/exitGameOn.png"));
        exitGame.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                System.exit(0);
            }
        });
        registerMenu = new Image(new Texture("button/registerMenuOn.png"));
        registerMenu.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                System.exit(0);
            }
        });
        textTable.add(loginErrorMessage);
        textTable.row();
        textTable.add(username);
        textTable.row();
        textTable.add(password);
        textTable.row();
        loginTable.add(login);
        loginTable.row();
        loginTable.add(forgetPassword);
        loginTable.row();
        loginTable.add(exitMenu);
        loginTable.row();
        loginTable.add(exitGame);
        loginTable.row();
        registerTable.add(registerMenu);
        registerTable.row();
        stage.addActor(background);
        stage.addActor(textTable);
        stage.addActor(loginTable);
        stage.addActor(registerTable);
    }

    @Override
    protected void backgroundLoader() {
        background = new Image(new Texture("background/loginBackground.5.jpeg"));
    }
}
