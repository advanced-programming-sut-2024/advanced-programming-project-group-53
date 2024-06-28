package view.graphic;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import controller.RegisterMenu;
import game.GWENT;
import network.Command;
import network.Connector;
import network.Instruction;

import java.util.Objects;

public class RegisterView extends View {
    private final Table registerTable;
    private final Table textTable;
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
        registerTable = new Table();
        registerTable.setBounds(50, 50, 400, (float) (400 * 0.1458 * 3));
        registerTable.align(Align.center);
        textTable = new Table();
        textTable.setBounds(635, 170, 400, 400);
        textTable.align(Align.center);
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
        question = new TextField("", textField);
        question.setMessageText("question");
        answer = new TextField("", textField);
        answer.setMessageText("answer");
        register = new Image(new Texture(Resource.REGISTER_OFF.address()));
        register.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                register.setDrawable(new Image(new Texture(Resource.REGISTER_CLICKED.address())).getDrawable());
                Connector connector = new Connector();
                Instruction response = connector.perform(new Instruction(Command.REGISTER,
                        username.getText(),
                        nickname.getText(),
                        email.getText(),
                        password.getText(),
                        question.getText(),
                        answer.getText()));
                perform(response);
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
        textTable.add(registerMessage);
        textTable.row();
        textTable.add(username);
        textTable.row();
        textTable.add(password);
        textTable.row();
        textTable.add(question);
        textTable.row();
        textTable.add(answer);
        stage.addActor(background);
        stage.addActor(registerTable);
        stage.addActor(textTable);
    }

    @Override
    protected void backgroundLoader() {
        background = new Image(new Texture(Resource.REGISTER_BACKGROUND.address()));
    }

    @Override
    protected void perform(Instruction instruction) {
        if (instruction.command() == Command.REGISTER_MESSAGE) {
            StringBuilder builder = new StringBuilder();
            for (String string: instruction.arguments())
                builder.append(string);
            registerMessage.setText(builder.toString());
            username.setText("");
            nickname.setText("");
            email.setText("");
            password.setText("");
            question.setText("");
            answer.setText("");
        }
    }
}
