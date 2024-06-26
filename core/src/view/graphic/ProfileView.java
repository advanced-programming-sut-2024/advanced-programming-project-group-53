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
import controller.ProfileMenu;
import game.GWENT;

public class ProfileView extends View {
    private final Table rightTable;
    private final Table middleTable;
    private final Table leftTable;
    private final Table rightInformationTable;
    private final Table leftInformationTable;
    private final Table usernameTable;
    private final Table passwordTable;
    private final Table nicknameTable;
    private final Table emailTable;
    private final Table saveTable;
    private final Label profileMessage;
    private final Label usernameMessage;
    private final Label nicknameMessage;
    private final Label maxScoreMessage;
    private final Label rankMessage;
    private final Label playCountMessage;
    private final Label drawCountMessage;
    private final Label winCountMessage;
    private final Label defeatCountMessage;
    private final TextField username;
    private final TextField password;
    private final TextField newPassword;
    private final TextField newPasswordConfirm;
    private final TextField email;
    private final TextField nickname;
    private final Image changeUsername;
    private final Image changeNickname;
    private final Image changeEmail;
    private final Image changePassword;
    private final Image history;
    private final Image exit;
    private final Image save;
    private final Image mainMenu;
    private boolean isOnChangeUsername = false;
    private boolean isOnChangeNickname = false;
    private boolean isOnChangeEmail = false;
    private boolean isOnChangePassword = false;

    public ProfileView(GWENT game) {
        super(game);
        menu = ProfileMenu.getInstance();
        rightTable = new Table();
        rightTable.setBounds(574, 50, 400, (float) (400 * 0.1458 * 2));
        rightTable.align(Align.center);
        middleTable = new Table();
        middleTable.setBounds(312, 810, 400, (float) (400 * 0.1458 * 3));
        middleTable.align(Align.center);
        leftTable = new Table();
        leftTable.setBounds(50, 50, 400, (float) (400 * 0.1458 * 2));
        leftTable.align(Align.center);
        rightInformationTable = new Table();
        rightInformationTable.setBounds(724, 338, 400, 400);
        rightInformationTable.align(Align.center);
        leftInformationTable = new Table();
        leftInformationTable.setBounds(-100, 338, 400, 400);
        leftInformationTable.align(Align.center);
        usernameTable = new Table();
        usernameTable.setBounds(312, 400, 400, (float) (400 * 0.1458));
        usernameTable.align(Align.center);
        emailTable = new Table();
        emailTable.setBounds(312, 400, 400, (float) (400 * 0.1458));
        emailTable.align(Align.center);
        nicknameTable = new Table();
        nicknameTable.setBounds(312, 400, 400, (float) (400 * 0.1458));
        nicknameTable.align(Align.center);
        passwordTable = new Table();
        passwordTable.setBounds(312, 430, 400, (float) (400 * 0.1458));
        passwordTable.align(Align.center);
        saveTable = new Table();
        saveTable.setBounds(312, 300, 400, (float) (400 * 0.1458));
        saveTable.align(Align.center);
        profileMessage = new Label("profile message", label);//TODO: handle message.
        usernameMessage = new Label("username", label);//TODO: handle message.
        nicknameMessage = new Label("nickname", label);//TODO: handle message.
        maxScoreMessage = new Label("max score", label);//TODO: handle message.
        rankMessage = new Label("rank", label);//TODO: handle message.
        playCountMessage = new Label("play count", label);//TODO: handle message.
        drawCountMessage = new Label("draw count", label);//TODO: handle message.
        winCountMessage = new Label("win", label);//TODO: handle message.
        defeatCountMessage = new Label("defeat", label);//TODO: handle message.
        username = new TextField("", textField);
        username.setMessageText("username");
        password = new TextField("", textField);
        password.setMessageText("password");
        password.setPasswordCharacter('*');
        password.setPasswordMode(true);
        newPassword = new TextField("", textField);
        newPassword.setMessageText("new password");
        newPassword.setPasswordMode(true);
        newPasswordConfirm = new TextField("", textField);
        newPasswordConfirm.setMessageText("confirm");
        newPasswordConfirm.setPasswordMode(true);
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
                    saveTable.clear();
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
                    saveTable.clear();
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
                    saveTable.clear();
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
                    passwordTable.row();
                    passwordTable.add(newPassword);
                    passwordTable.row();
                    passwordTable.add(newPasswordConfirm);
                    usernameTable.clear();
                    nicknameTable.clear();
                    emailTable.clear();
                    saveTable.clear();
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
                game.changeScreen(new HistoryView(game));
            }

            @Override
            public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
                history.setDrawable(new Image(new Texture(Resource.HISTORY_ON.address())).getDrawable());
            }

            @Override
            public void exit(InputEvent event, float x, float y, int pointer, Actor toActor) {
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
        middleTable.row();
        middleTable.add(exit);
        leftInformationTable.add(usernameMessage);
        leftInformationTable.row();
        leftInformationTable.add(nicknameMessage);
        leftInformationTable.row();
        leftInformationTable.add(maxScoreMessage);
        leftInformationTable.row();
        leftInformationTable.add(rankMessage);
        rightInformationTable.add(playCountMessage);
        rightInformationTable.row();
        rightInformationTable.add(drawCountMessage);
        rightInformationTable.row();
        rightInformationTable.add(winCountMessage);
        rightInformationTable.row();
        rightInformationTable.add(defeatCountMessage);
        stage.addActor(background);
        stage.addActor(leftTable);
        stage.addActor(middleTable);
        stage.addActor(rightTable);
        stage.addActor(usernameTable);
        stage.addActor(passwordTable);
        stage.addActor(emailTable);
        stage.addActor(nicknameTable);
        stage.addActor(saveTable);
        stage.addActor(leftInformationTable);
        stage.addActor(rightInformationTable);
    }

    @Override
    protected void backgroundLoader() {
        background = new Image(new Texture(Resource.PROFILE_BACKGROUND.address()));
    }
}
