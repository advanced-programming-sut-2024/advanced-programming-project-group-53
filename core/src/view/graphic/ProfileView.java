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
import controller.LoginMenu;
import game.GWENT;

public class ProfileView extends View {
    private final Table rightTable;
    private final Table middleTable;
    private final Table leftTable;
    private final Table historyTable;
    private final Table usernameTable;
    private final Table passwordTable;
    private final Table nicknameTable;
    private final Table emailTable;
    private final Table saveTable;
    private final Label profileMessage;
    private final Label historyMessage;
    private final TextField username;
    private final TextField password;
    private final TextField email;
    private final TextField nickname;
    private final Image changeUsername;
    private final Image changeNickname;
    private final Image changeEmail;
    private final Image changePassword;
    private final Image history;
    private final Image exitGame;
    private final Image save;
    private final Image mainMenu;
    private boolean isOnChangeUsername = false;
    private boolean isOnChangeNickname = false;
    private boolean isOnChangeEmail = false;
    private boolean isOnChangePassword = false;
    private boolean isOnHistory = false;

    public ProfileView(GWENT game) {
        super(game);
        menu = LoginMenu.getInstance();
        rightTable = new Table();
        rightTable.setBounds(574, 50, 400, 400 / 2.96f);
        rightTable.align(Align.center);
        middleTable = new Table();
        middleTable.setBounds(312, 850, 400, 400 / 2.96f);
        middleTable.align(Align.center);
        leftTable = new Table();
        leftTable.setBounds(50, 50, 400, 400 / 2.96f);
        leftTable.align(Align.center);
        historyTable = new Table();
        historyTable.setBounds(600, 600, 400, 400);
        historyTable.align(Align.center);
        usernameTable = new Table();
        usernameTable.setBounds(312, 400, 400, 400 / 5.92f);
        usernameTable.align(Align.center);
        emailTable = new Table();
        emailTable.setBounds(312, 400, 400, 400 / 5.92f);
        emailTable.align(Align.center);
        nicknameTable = new Table();
        nicknameTable.setBounds(312, 400, 400, 400 / 5.92f);
        nicknameTable.align(Align.center);
        passwordTable = new Table();
        passwordTable.setBounds(312, 400, 400, 400 / 5.92f);
        passwordTable.align(Align.center);
        saveTable = new Table();
        saveTable.setBounds(312, 300, 400, 400 / 5.92f);
        saveTable.align(Align.center);
        profileMessage = new Label("profile message", label);//TODO: handle message.
        historyMessage = new Label("history message", label);
        username = new TextField("", textField);
        username.setMessageText("username");
        password = new TextField("", textField);
        password.setMessageText("password");
        password.setPasswordCharacter('*');
        password.setPasswordMode(true);
        email = new TextField("", textField);
        email.setMessageText("email");
        nickname = new TextField("", textField);
        nickname.setMessageText("nickname");
        changeUsername = new Image(new Texture(Resource.CHANGE_USERNAME_OFF.address()));
        changeUsername.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                changeUsername.setDrawable(new Image(new Texture(Resource.CHANGE_USERNAME_CLICKED.address())).getDrawable());
                if (isOnChangeUsername) {
                    isOnChangeUsername = false;
                    usernameTable.clear();
                    saveTable.clear();
                } else {
                    isOnChangeUsername = true;
                    isOnChangeNickname = false;
                    isOnChangeEmail = false;
                    isOnChangePassword = false;
                    changeNickname.setDrawable(new Image(new Texture(Resource.CHANGE_NICKNAME_OFF.address())).getDrawable());
                    changeEmail.setDrawable(new Image(new Texture(Resource.CHANGE_EMAIL_OFF.address())).getDrawable());
                    changePassword.setDrawable(new Image(new Texture(Resource.CHANGE_PASSWORD_OFF.address())).getDrawable());
                    usernameTable.add(profileMessage);
                    usernameTable.row();
                    usernameTable.add(username);
                    nicknameTable.clear();
                    emailTable.clear();
                    passwordTable.clear();
                    saveTable.add(save);
                }
            }

            @Override
            public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
                if (!isOnChangeUsername)
                    changeUsername.setDrawable(new Image(new Texture(Resource.CHANGE_USERNAME_ON.address())).getDrawable());
            }

            @Override
            public void exit(InputEvent event, float x, float y, int pointer, Actor toActor) {
                if (!isOnChangeUsername)
                    changeUsername.setDrawable(new Image(new Texture(Resource.CHANGE_USERNAME_OFF.address())).getDrawable());
            }
        });
        changeNickname = new Image(new Texture(Resource.CHANGE_NICKNAME_OFF.address()));
        changeNickname.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                changeNickname.setDrawable(new Image(new Texture(Resource.CHANGE_NICKNAME_CLICKED.address())).getDrawable());
                if (isOnChangeNickname) {
                    isOnChangeNickname = false;
                    nicknameTable.clear();
                    saveTable.clear();
                } else {
                    isOnChangeUsername = false;
                    isOnChangeNickname = true;
                    isOnChangeEmail = false;
                    isOnChangePassword = false;
                    changeUsername.setDrawable(new Image(new Texture(Resource.CHANGE_USERNAME_OFF.address())).getDrawable());
                    changeEmail.setDrawable(new Image(new Texture(Resource.CHANGE_EMAIL_OFF.address())).getDrawable());
                    changePassword.setDrawable(new Image(new Texture(Resource.CHANGE_PASSWORD_OFF.address())).getDrawable());
                    nicknameTable.add(profileMessage);
                    nicknameTable.row();
                    nicknameTable.add(nickname);
                    usernameTable.clear();
                    emailTable.clear();
                    passwordTable.clear();
                    saveTable.add(save);
                }
            }

            @Override
            public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
                if (!isOnChangeNickname)
                    changeNickname.setDrawable(new Image(new Texture(Resource.CHANGE_NICKNAME_ON.address())).getDrawable());
            }

            @Override
            public void exit(InputEvent event, float x, float y, int pointer, Actor toActor) {
                if (!isOnChangeNickname)
                    changeNickname.setDrawable(new Image(new Texture(Resource.CHANGE_NICKNAME_OFF.address())).getDrawable());
            }
        });
        changeEmail = new Image(new Texture(Resource.CHANGE_EMAIL_OFF.address()));
        changeEmail.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                changeEmail.setDrawable(new Image(new Texture(Resource.CHANGE_EMAIL_CLICKED.address())).getDrawable());
                if (isOnChangeEmail) {
                    isOnChangeEmail = false;
                    emailTable.clear();
                    saveTable.clear();
                } else {
                    isOnChangeUsername = false;
                    isOnChangeNickname = false;
                    isOnChangeEmail = true;
                    isOnChangePassword = false;
                    changeUsername.setDrawable(new Image(new Texture(Resource.CHANGE_USERNAME_OFF.address())).getDrawable());
                    changeNickname.setDrawable(new Image(new Texture(Resource.CHANGE_NICKNAME_OFF.address())).getDrawable());
                    changePassword.setDrawable(new Image(new Texture(Resource.CHANGE_PASSWORD_OFF.address())).getDrawable());
                    emailTable.add(profileMessage);
                    emailTable.row();
                    emailTable.add(email);
                    usernameTable.clear();
                    nicknameTable.clear();
                    passwordTable.clear();
                    saveTable.add(save);
                }
            }

            @Override
            public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
                if (!isOnChangeEmail)
                    changeEmail.setDrawable(new Image(new Texture(Resource.CHANGE_EMAIL_ON.address())).getDrawable());
            }

            @Override
            public void exit(InputEvent event, float x, float y, int pointer, Actor toActor) {
                if (!isOnChangeEmail)
                    changeEmail.setDrawable(new Image(new Texture(Resource.CHANGE_EMAIL_OFF.address())).getDrawable());
            }
        });
        changePassword = new Image(new Texture(Resource.CHANGE_PASSWORD_OFF.address()));
        changePassword.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                changePassword.setDrawable(new Image(new Texture(Resource.CHANGE_PASSWORD_CLICKED.address())).getDrawable());
                if (isOnChangePassword) {
                    isOnChangePassword = false;
                    passwordTable.clear();
                    saveTable.clear();
                } else {
                    isOnChangeUsername = false;
                    isOnChangeNickname = false;
                    isOnChangeEmail = false;
                    isOnChangePassword = true;
                    changeUsername.setDrawable(new Image(new Texture(Resource.CHANGE_USERNAME_OFF.address())).getDrawable());
                    changeNickname.setDrawable(new Image(new Texture(Resource.CHANGE_NICKNAME_OFF.address())).getDrawable());
                    changeEmail.setDrawable(new Image(new Texture(Resource.CHANGE_EMAIL_OFF.address())).getDrawable());
                    passwordTable.add(profileMessage);
                    passwordTable.row();
                    passwordTable.add(password);
                    usernameTable.clear();
                    nicknameTable.clear();
                    emailTable.clear();
                    saveTable.add(save);
                }
            }

            @Override
            public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
                if (!isOnChangePassword)
                    changePassword.setDrawable(new Image(new Texture(Resource.CHANGE_PASSWORD_ON.address())).getDrawable());
            }

            @Override
            public void exit(InputEvent event, float x, float y, int pointer, Actor toActor) {
                if (!isOnChangePassword)
                    changePassword.setDrawable(new Image(new Texture(Resource.CHANGE_PASSWORD_OFF.address())).getDrawable());
            }
        });
        history = new Image(new Texture(Resource.HISTORY_OFF.address()));
        history.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                history.setDrawable(new Image(new Texture(Resource.HISTORY_CLICKED.address())).getDrawable());
                if (isOnHistory) {
                    isOnHistory = false;
                    historyTable.clear();
                } else {
                    isOnHistory = true;
                    historyTable.add(historyMessage);//TODO: handle message.
                }
            }

            @Override
            public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
                if (!isOnHistory)
                    history.setDrawable(new Image(new Texture(Resource.HISTORY_ON.address())).getDrawable());
            }

            @Override
            public void exit(InputEvent event, float x, float y, int pointer, Actor toActor) {
                if (!isOnHistory)
                    history.setDrawable(new Image(new Texture(Resource.HISTORY_OFF.address())).getDrawable());
            }
        });
        mainMenu = new Image(new Texture(Resource.MAIN_MENU_OFF.address()));
        mainMenu.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                mainMenu.setDrawable(new Image(new Texture(Resource.MAIN_MENU_CLICKED.address())).getDrawable());
                game.changeScreen(new MainView(game));
            }

            @Override
            public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
                mainMenu.setDrawable(new Image(new Texture(Resource.MAIN_MENU_ON.address())).getDrawable());
            }

            @Override
            public void exit(InputEvent event, float x, float y, int pointer, Actor toActor) {
                mainMenu.setDrawable(new Image(new Texture(Resource.MAIN_MENU_OFF.address())).getDrawable());
            }
        });
        exitGame = new Image(new Texture(Resource.EXIT_GAME_OFF.address()));
        exitGame.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                exitGame.setDrawable(new Image(new Texture(Resource.EXIT_GAME_CLICKED.address())).getDrawable());
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
        save = new Image(new Texture(Resource.SAVE_OFF.address()));
        save.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                save.setDrawable(new Image(new Texture(Resource.SAVE_CLICKED.address())).getDrawable());
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
        leftTable.add(changeUsername);
        leftTable.row();
        leftTable.add(changePassword);
        rightTable.add(changeNickname);
        rightTable.row();
        rightTable.add(changeEmail);
        middleTable.add(history);
        middleTable.row();
        middleTable.add(mainMenu);
        stage.addActor(background);
        stage.addActor(leftTable);
        stage.addActor(middleTable);
        stage.addActor(rightTable);
        stage.addActor(usernameTable);
        stage.addActor(passwordTable);
        stage.addActor(emailTable);
        stage.addActor(nicknameTable);
        stage.addActor(saveTable);
        stage.addActor(historyTable);
    }

    @Override
    protected void backgroundLoader() {
        background = new Image(new Texture(Resource.PROFILE_BACKGROUND.address()));
    }
}
