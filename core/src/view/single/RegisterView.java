package view.single;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import controller.RegisterMenu;
import game.GWENT;

import java.util.Objects;

public class RegisterView extends View {
    private final Label registerMessage;
    private final TextField question;
    private final TextField username;
    private final TextField nickname;
    private final TextField password;
    private final TextField email;
    private final TextField answer;
    private final Image register;
    private final Image loginMenu;
    private final Image exit;

    public RegisterView(GWENT game) {
        super(game);
        menu = RegisterMenu.getInstance();
        Table registerTable = new Table();
        registerTable.setBounds(50, 50, 400, (float) (400 * 0.1458 * 3));
        registerTable.align(Align.center);
        VerticalGroup textGroup = new VerticalGroup();
        textGroup.setBounds(635, 170, 400, 400);
        textGroup.align(Align.center);
        textGroup.space(10);
        registerMessage = new Label("", label);
        username = new TextField("", textField);
        username.setMessageText("username");
        nickname = new TextField("", textField);
        nickname.setMessageText("nickname");
        email = new TextField("", textField);
        email.setMessageText("email");
        password = new TextField("", textField);
        password.setMessageText("password");
        password.setPasswordCharacter('*');
        password.setPasswordMode(true);
        TextField confirmPassword = new TextField("", textField);
        confirmPassword.setMessageText("confirm");
        confirmPassword.setPasswordMode(true);
        confirmPassword.setPasswordCharacter('*');
        question = new TextField("", textField);
        question.setMessageText("question");
        answer = new TextField("", textField);
        answer.setMessageText("answer");
        register = new Image(new Texture(Resource.REGISTER_OFF.address()));
        register.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                register.setDrawable(new Image(new Texture(Resource.REGISTER_CLICKED.address())).getDrawable());
                String response = ((RegisterMenu) menu).register(
                        username.getText(),
                        nickname.getText(),
                        email.getText(),
                        password.getText(),
                        confirmPassword.getText(),
                        question.getText(),
                        answer.getText());
                if (Objects.equals(response, "empty")) {
                    username.setText("");
                    nickname.setText("");
                    email.setText("");
                    password.setText("");
                    confirmPassword.setText("");
                    question.setText("");
                    answer.setText("");
                } else
                    registerMessage.setText(response.trim());
            }

            @Override
            public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
                register.setDrawable(new Image(new Texture(Resource.REGISTER_ON.address())).getDrawable());
            }

            @Override
            public void exit(InputEvent event, float x, float y, int pointer, Actor toActor) {
                register.setDrawable(new Image(new Texture(Resource.REGISTER_OFF.address())).getDrawable());
            }
        });
        loginMenu = new Image(new Texture(Resource.LOGIN_MENU_OFF.address()));
        loginMenu.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                loginMenu.setDrawable(new Image(new Texture(Resource.LOGIN_MENU_CLICKED.address())).getDrawable());
                game.changeScreen(new LoginView(game));
            }

            @Override
            public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
                loginMenu.setDrawable(new Image(new Texture(Resource.LOGIN_MENU_ON.address())).getDrawable());
            }

            @Override
            public void exit(InputEvent event, float x, float y, int pointer, Actor toActor) {
                loginMenu.setDrawable(new Image(new Texture(Resource.LOGIN_MENU_OFF.address())).getDrawable());
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
        registerTable.add(register);
        registerTable.row();
        registerTable.add(loginMenu);
        registerTable.row();
        registerTable.add(exit);
        textGroup.addActor(registerMessage);
        textGroup.addActor(username);
        textGroup.addActor(nickname);
        textGroup.addActor(email);
        textGroup.addActor(password);
        textGroup.addActor(confirmPassword);
        textGroup.addActor(question);
        textGroup.addActor(answer);
        stage.addActor(background);
        stage.addActor(registerTable);
        stage.addActor(textGroup);
    }

    @Override
    protected void backgroundLoader() {
        background = new Image(new Texture(Resource.REGISTER_BACKGROUND.address()));
    }
}
