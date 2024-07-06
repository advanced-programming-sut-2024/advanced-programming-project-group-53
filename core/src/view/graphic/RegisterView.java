package view.graphic;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import controller.RegisterMenu;
import game.GWENT;
import model.view.Message;
import network.Command;
import network.Connector;
import network.Instruction;
import model.view.Resource;

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
    private final Table emailAuthentication;
    private final Image save;
    private final TextField codeInput;
    private final Label error;
    private String code;

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
        emailAuthentication = new Table();
        emailAuthentication.setBounds(50, 250, 400, 400);
        emailAuthentication.align(Align.center);
        registerMessage = new Label("", skin);
        username = new TextField("", skin);
        username.setMessageText("username");
        nickname = new TextField("", skin);
        nickname.setMessageText("nickname");
        email = new TextField("", skin);
        email.setMessageText("email");
        password = new TextField("", skin);
        password.setMessageText("password");
        password.setPasswordCharacter('*');
        password.setPasswordMode(true);
        error = new Label("", skin);
        codeInput = new TextField("", skin);
        codeInput.setMessageText("code");
        TextField confirmPassword = new TextField("", skin);
        confirmPassword.setMessageText("confirm");
        confirmPassword.setPasswordMode(true);
        confirmPassword.setPasswordCharacter('*');
        question = new TextField("", skin);
        question.setMessageText("question");
        answer = new TextField("", skin);
        answer.setMessageText("answer");
        register = new Image(new Texture(Resource.REGISTER_OFF.address()));
        register.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                register.setDrawable(new Image(new Texture(Resource.REGISTER_CLICKED.address())).getDrawable());
                perform(new Connector().perform(new Instruction(Command.REGISTER_VALIDATION,
                        username.getText(),
                        nickname.getText(),
                        email.getText(),
                        password.getText())));
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
        save = new Image(new Texture(Resource.SAVE_OFF.address()));
        save.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                save.setDrawable(new Image(new Texture(Resource.SAVE_CLICKED.address())).getDrawable());
                if (codeInput.getText().equals(code))
                    perform(new Connector().perform(new Instruction(Command.REGISTER,
                            username.getText(),
                            nickname.getText(),
                            email.getText(),
                            password.getText(),
                            confirmPassword.getText(),
                            question.getText(),
                            answer.getText())));
                else
                    error.setText(Message.CODE.message());
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
        stage.addActor(emailAuthentication);
    }

    @Override
    protected void backgroundLoader() {
        background = new Image(new Texture(Resource.REGISTER_BACKGROUND.address()));
    }

    @Override
    protected void perform(Instruction instruction) {
        System.out.println(instruction);
        String[] arguments = instruction.arguments();
        StringBuilder builder = new StringBuilder();
        for (String string : instruction.arguments())
            builder.append(string).append(" ");
        String message = builder.toString().trim();
        switch (instruction.command()) {
            case REGISTER_MESSAGE:
                if (!Objects.equals(arguments[0], "empty"))
                    error.setText(message);
                else
                    codeInput.setText("");
                break;
            case EMAIL_VALIDATION_MESSAGE:
                code = arguments[0];
                break;
            case REGISTER_VALIDATION_MESSAGE:
                if (Objects.equals(arguments[0], "empty")) {
                    perform(new Connector().perform(new Instruction(Command.EMAIL_VALIDATION, username.getText(), email.getText())));
                    emailAuthentication.add(error);
                    emailAuthentication.row();
                    emailAuthentication.add(codeInput);
                    emailAuthentication.row();
                    emailAuthentication.add(save);
                }
                else
                    registerMessage.setText(message);
                break;
        }
    }
}
