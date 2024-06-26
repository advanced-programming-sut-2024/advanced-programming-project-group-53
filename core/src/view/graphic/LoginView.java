package view.graphic;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import game.GWENT;
import controller.LoginMenu;

public class LoginView extends View {
    private final Table textTable;
    private final Table loginTable;
    private final Table registerTable;
    private final Table forgetPasswordTable;
    private final Table changePasswordTable;
    private final Label loginMessage;
    private final Label question;
    private final TextField username;
    private final TextField password;
    private final TextField answer;
    private final TextField newPassword;
    private final TextField newPasswordConfirm;
    private final Image login;
    private final Image forgetPassword;
    private final Image exit;
    private final Image registerMenu;
    private final Image savePassword;
    private final Image skip;//TODO: delete this.
    private boolean isOnForgetPassword = false;

    public LoginView(GWENT game) {
        super(game);
        menu = LoginMenu.getInstance();
        loginTable = new Table();
        loginTable.setBounds(50, 50, 400, (float) (400 * 0.1458 * 3));
        loginTable.align(Align.center);
        textTable = new Table();
        textTable.setBounds(700, 170, 400, 400);
        textTable.align(Align.center);
        registerTable = new Table();
        registerTable.setBounds(574, 50, 400, (float) (400 * 0.1458));
        registerTable.align(Align.center);
        forgetPasswordTable = new Table();
        forgetPasswordTable.setBounds(50, 350, 400, 400);
        forgetPasswordTable.align(Align.center);
        changePasswordTable = new Table();
        changePasswordTable.setBounds(50, 350, 400, (float) (400 * 0.1458));
        changePasswordTable.align(Align.center);
        loginMessage = new Label("login error message", label);//TODO: handle message.
        username = new TextField("", textField);
        username.setMessageText("username");
        password = new TextField("", textField);
        password.setMessageText("password");
        password.setPasswordCharacter('*');
        password.setPasswordMode(true);
        login = new Image(new Texture(Resource.LOGIN_OFF.address()));
        login.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                login.setDrawable(new Image(new Texture(Resource.LOGIN_CLICKED.address())).getDrawable());
                ((LoginMenu) menu).login(username.getText(), password.getText());
                username.setText("");
                password.setText("");
            }

            @Override
            public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
                login.setDrawable(new Image(new Texture(Resource.LOGIN_ON.address())).getDrawable());
            }

            @Override
            public void exit(InputEvent event, float x, float y, int pointer, Actor toActor) {
                login.setDrawable(new Image(new Texture(Resource.LOGIN_OFF.address())).getDrawable());
            }
        });
        forgetPassword = new Image(new Texture(Resource.FORGET_PASSWORD_OFF.address()));
        forgetPassword.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                forgetPassword.setDrawable(new Image(new Texture(Resource.FORGET_PASSWORD_CLICKED.address())).getDrawable());
                if (isOnForgetPassword) {
                    isOnForgetPassword = false;
                    forgetPasswordTable.clear();
                    changePasswordTable.clear();
                } else {
                    isOnForgetPassword = true;
                    forgetPasswordTable.add(question);
                    forgetPasswordTable.row();
                    forgetPasswordTable.add(answer);
                    forgetPasswordTable.row();
                    forgetPasswordTable.add(newPassword);
                    forgetPasswordTable.row();
                    forgetPasswordTable.add(newPasswordConfirm);
                    changePasswordTable.add(savePassword);
                }
            }

            @Override
            public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
                if (!isOnForgetPassword)
                    forgetPassword.setDrawable(new Image(new Texture(Resource.FORGET_PASSWORD_ON.address())).getDrawable());
            }

            @Override
            public void exit(InputEvent event, float x, float y, int pointer, Actor toActor) {
                if (!isOnForgetPassword)
                    forgetPassword.setDrawable(new Image(new Texture(Resource.FORGET_PASSWORD_OFF.address())).getDrawable());
            }
        });
        exit = new Image(new Texture(Resource.EXIT_OFF.address()));
        exit.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                exit.setDrawable(new Image(new Texture(Resource.EXIT_CLICKED.address())).getDrawable());
                menu.exitGame();
            }

            @Override
            public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
                exit.setDrawable(new Image(new Texture(Resource.EXIT_ON.address())).getDrawable());
            }

            @Override
            public void exit(InputEvent event, float x, float y, int pointer, Actor toActor) {
                exit.setDrawable(new Image(new Texture(Resource.EXIT_OFF.address())).getDrawable());
            }
        });
        registerMenu = new Image(new Texture(Resource.REGISTER_MENU_OFF.address()));
        registerMenu.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                registerMenu.setDrawable(new Image(new Texture(Resource.REGISTER_MENU_CLICKED.address())).getDrawable());
                game.changeScreen(new RegisterView(game));
            }

            @Override
            public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
                registerMenu.setDrawable(new Image(new Texture(Resource.REGISTER_MENU_ON.address())).getDrawable());
            }

            @Override
            public void exit(InputEvent event, float x, float y, int pointer, Actor toActor) {
                registerMenu.setDrawable(new Image(new Texture(Resource.REGISTER_MENU_OFF.address())).getDrawable());
            }
        });
        question = new Label("question", label);//TODO: handle question.
        answer = new TextField("", textField);
        answer.setMessageText("answer");
        newPassword = new TextField("", textField);
        newPassword.setMessageText("password");
        newPassword.setPasswordCharacter('*');
        newPassword.setPasswordMode(true);
        newPasswordConfirm = new TextField("", textField);
        newPasswordConfirm.setMessageText("confirm");
        newPasswordConfirm.setPasswordCharacter('*');
        newPasswordConfirm.setPasswordMode(true);
        savePassword = new Image(new Texture(Resource.SAVE_OFF.address()));
        savePassword.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                savePassword.setDrawable(new Image(new Texture(Resource.SAVE_CLICKED.address())).getDrawable());
                //TODO: fill it.
            }

            @Override
            public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
                savePassword.setDrawable(new Image(new Texture(Resource.SAVE_ON.address())).getDrawable());
            }

            @Override
            public void exit(InputEvent event, float x, float y, int pointer, Actor toActor) {
                savePassword.setDrawable(new Image(new Texture(Resource.SAVE_OFF.address())).getDrawable());
            }
        });
        skip = new Image(new Texture(Resource.MAIN_MENU_ON.address()));
        skip.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.changeScreen(new MainView(game));
            }
        });
        textTable.add(loginMessage);
        textTable.row();
        textTable.add(username);
        textTable.row();
        textTable.add(password);
        loginTable.add(login);
        loginTable.row();
        loginTable.add(forgetPassword);
        loginTable.row();
        loginTable.add(exit);
        registerTable.add(registerMenu);
        registerTable.row();
        registerTable.add(skip);
        stage.addActor(background);
        stage.addActor(textTable);
        stage.addActor(loginTable);
        stage.addActor(registerTable);
        stage.addActor(forgetPasswordTable);
        stage.addActor(changePasswordTable);
    }

    @Override
    protected void backgroundLoader() {
        background = new Image(new Texture(Resource.LOGIN_BACKGROUND.address()));
    }
}
