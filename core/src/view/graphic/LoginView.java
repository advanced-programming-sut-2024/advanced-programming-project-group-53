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
import controller.LoginMenu;
import game.GWENT;
import network.Command;
import network.Connector;
import network.Instruction;

import java.util.Objects;

public class LoginView extends View {
    private final VerticalGroup forgetPasswordGroup1;
    private final VerticalGroup forgetPasswordGroup2;
    private final Label loginMessage;
    private final Label forgetPasswordMessage;
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
    private final Image save;
    private boolean isOnForgetPassword1 = false;
    private boolean isOnForgetPassword2 = false;

    public LoginView(GWENT game) {
        super(game);
        menu = LoginMenu.getInstance();
        Table loginTable = new Table();
        loginTable.setBounds(50, 50, 400, (float) (400 * 0.1458 * 3));
        loginTable.align(Align.center);
        VerticalGroup textGroup = new VerticalGroup();
        textGroup.setBounds(700, 170, 400, 400);
        textGroup.align(Align.center);
        textGroup.space(10);
        forgetPasswordGroup1 = new VerticalGroup();
        forgetPasswordGroup1.setBounds(50, 350, 400, 400);
        forgetPasswordGroup1.align(Align.center);
        forgetPasswordGroup1.space(10);
        forgetPasswordGroup2 = new VerticalGroup();
        forgetPasswordGroup2.setBounds(50, 350, 400, 400);
        forgetPasswordGroup2.align(Align.center);
        forgetPasswordGroup2.space(10);
        loginMessage = new Label("", label);
        forgetPasswordMessage = new Label("", label);
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
                perform(new Connector().perform(new Instruction(Command.LOGIN, username.getText(), password.getText())));
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
                perform(new Connector().perform(new Instruction(Command.QUESTION)));
                if (isOnForgetPassword1) {
                    isOnForgetPassword1 = false;
                    forgetPasswordGroup1.clear();
                } else if (isOnForgetPassword2) {
                    isOnForgetPassword2 = false;
                    forgetPasswordGroup2.clear();
                } else {
                    isOnForgetPassword1 = true;
                    forgetPasswordGroup1.addActor(forgetPasswordMessage);
                    forgetPasswordGroup1.addActor(username);
                    forgetPasswordGroup1.addActor(save);
                }
            }

            @Override
            public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
                if (!isOnForgetPassword1)
                    forgetPassword.setDrawable(new Image(new Texture(Resource.FORGET_PASSWORD_ON.address())).getDrawable());
            }

            @Override
            public void exit(InputEvent event, float x, float y, int pointer, Actor toActor) {
                if (!isOnForgetPassword1)
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
        registerMenu.setPosition(574, 50);
        registerMenu.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                registerMenu.setDrawable(new Image(new Texture(Resource.REGISTER_MENU_CLICKED.address())).getDrawable());
             //   game.changeScreen(new RegisterView(game));
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
        question = new Label("", label);
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
        save = new Image(new Texture(Resource.SAVE_OFF.address()));
        save.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                save.setDrawable(new Image(new Texture(Resource.SAVE_CLICKED.address())).getDrawable());
                if (isOnForgetPassword1)
                    perform(new Connector().perform(new Instruction(Command.USERNAME_CHECK, username.getText())));
                else if (isOnForgetPassword2)
                    perform(new Instruction(Command.FORGET_PASSWORD,
                            answer.getText(),
                            newPassword.getText(),
                            newPasswordConfirm.getText(),
                            username.getText()));
            }

            @Override
            public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
                save.setDrawable(new Image(new Texture(Resource.SAVE_ON.address())).getDrawable());
            }

            @Override
            public void exit(InputEvent event, float x, float y, int pointer, Actor toActor) {
                save.setDrawable(new Image(new Texture(Resource.SAVE_OFF.address())).getDrawable());
            }
        });
        textGroup.addActor(loginMessage);
        textGroup.addActor(username);
        textGroup.addActor(password);
        loginTable.add(login);
        loginTable.row();
        loginTable.add(forgetPassword);
        loginTable.row();
        loginTable.add(exit);
        stage.addActor(background);
        stage.addActor(textGroup);
        stage.addActor(loginTable);
        stage.addActor(registerMenu);
        stage.addActor(forgetPasswordGroup1);
        stage.addActor(forgetPasswordGroup2);
    }

    @Override
    protected void backgroundLoader() {
        background = new Image(new Texture(Resource.LOGIN_BACKGROUND.address()));
    }

    @Override
    protected void perform(Instruction instruction) {
        System.out.println(instruction);
        String[] arguments = instruction.arguments();
        switch (instruction.command()) {
            case FORGET_PASSWORD_MESSAGE_USER:
                if (Objects.equals(arguments[0], "")) {
                    forgetPasswordGroup1.clear();
                    isOnForgetPassword1 = false;
                    perform(new Instruction(Command.QUESTION, username.getText()));
                    forgetPasswordGroup2.addActor(forgetPasswordMessage);
                    forgetPasswordGroup2.addActor(question);
                    forgetPasswordGroup2.addActor(answer);
                    forgetPasswordGroup2.addActor(newPassword);
                    forgetPasswordGroup2.addActor(newPasswordConfirm);
                    forgetPasswordGroup2.addActor(save);
                    isOnForgetPassword2 = true;
                }
                forgetPasswordMessage.setText(arguments[0]);
                username.setText("");
                break;
            case QUESTION_MESSAGE:
                question.setText(arguments[0]);
                break;
            case LOGIN_MESSAGE:
              /*  if (Objects.equals(arguments[0], "empty"))
                  //  game.changeScreen(new MainView(game, username.getText()));
                else
                    loginMessage.setText(arguments[0]);
                break;*/
        }
    }
}
