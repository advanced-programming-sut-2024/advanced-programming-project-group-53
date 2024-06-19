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

public class RegisterView extends View {
    private final Table registerTable;
    private final Table textTable;
    private final Label registerMessage;
    private final Label question;
    private final TextField username;
    private final TextField password;
    private final TextField answer;
    private final Image register;
    private final Image loginMenu;
    private final Image exitGame;

    public RegisterView(GWENT game) {
        super(game);
        menu = RegisterMenu.getInstance();
        registerTable = new Table();
        registerTable.setBounds(50, 50, 400, 400 / 5.92f * 3);
        registerTable.align(Align.center);
        textTable = new Table();
        textTable.setBounds(635, 170, 400, 400);
        textTable.align(Align.center);
        registerMessage = new Label("ohhhhhhhhhhh", label);//TODO: The bloody message.
        question = new Label("Question", label);//TODO: !.
        username = new TextField("", textField);
        username.setMessageText("username");
        password = new TextField("", textField);
        password.setMessageText("password");
        password.setPasswordCharacter('*');
        password.setPasswordMode(true);
        answer = new TextField("", textField);
        answer.setMessageText("answer");
        register = new Image(new Texture(Resource.REGISTER_OFF.address()));
        register.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                //TODO: fill it.
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
        exitGame = new Image(new Texture(Resource.EXIT_GAME_OFF.address()));
        exitGame.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                menu.exitGame();
            }

            @Override
            public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
                exitGame.setDrawable(new Image(new Texture(Resource.EXIT_GAME_ON.address())).getDrawable());
            }

            @Override
            public void exit(InputEvent event, float x, float y, int pointer, Actor toActor) {
                exitGame.setDrawable(new Image(new Texture(Resource.EXIT_GAME_OFF.address())).getDrawable());
            }
        });
        registerTable.add(register);
        registerTable.row();
        registerTable.add(loginMenu);
        registerTable.row();
        registerTable.add(exitGame);
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
}
