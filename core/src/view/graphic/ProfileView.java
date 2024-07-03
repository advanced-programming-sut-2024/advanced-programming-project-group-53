package view.graphic;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import controller.ProfileMenu;
import game.GWENT;
import network.Command;
import network.Connector;
import network.Instruction;
import view.Resource;

import java.util.Objects;

public class ProfileView extends View {
    private final VerticalGroup usernameGroup;
    private final VerticalGroup passwordGroup;
    private final VerticalGroup nicknameGroup;
    private final VerticalGroup emailGroup;
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

    public ProfileView(GWENT game, String currentUsername) {
        super(game);
        this.currentUsername = currentUsername;
        menu = ProfileMenu.getInstance();
        Table rightTable = new Table();
        rightTable.setBounds(574, 50, 400, (float) (400 * 0.1458 * 2));
        rightTable.align(Align.center);
        Table middleTable = new Table();
        middleTable.setBounds(312, 810, 400, (float) (400 * 0.1458 * 3));
        middleTable.align(Align.center);
        Table leftTable = new Table();
        leftTable.setBounds(50, 50, 400, (float) (400 * 0.1458 * 2));
        leftTable.align(Align.center);
        VerticalGroup rightInformationGroup = new VerticalGroup();
        rightInformationGroup.setBounds(724, 338, 400, 400);
        rightInformationGroup.align(Align.center);
        VerticalGroup leftInformationGroup = new VerticalGroup();
        leftInformationGroup.setBounds(-100, 338, 400, 400);
        leftInformationGroup.align(Align.center);
        usernameGroup = new VerticalGroup();
        usernameGroup.setBounds(312, 400, 400, (float) (400 * 0.1458));
        usernameGroup.align(Align.center);
        usernameGroup.space(10);
        emailGroup = new VerticalGroup();
        emailGroup.setBounds(312, 400, 400, (float) (400 * 0.1458));
        emailGroup.align(Align.center);
        emailGroup.space(10);
        nicknameGroup = new VerticalGroup();
        nicknameGroup.setBounds(312, 400, 400, (float) (400 * 0.1458));
        nicknameGroup.align(Align.center);
        nicknameGroup.space(10);
        passwordGroup = new VerticalGroup();
        passwordGroup.setBounds(312, 430, 400, (float) (400 * 0.1458));
        passwordGroup.align(Align.center);
        passwordGroup.space(10);
        profileMessage = new Label("", skin);
        usernameMessage = new Label("", skin);
        nicknameMessage = new Label("", skin);
        maxScoreMessage = new Label("", skin);
        rankMessage = new Label("", skin);
        playCountMessage = new Label("", skin);
        drawCountMessage = new Label("", skin);
        winCountMessage = new Label("", skin);
        defeatCountMessage = new Label("", skin);
        perform(new Connector().perform(new Instruction(Command.PROFILE_INFORMATION, currentUsername)));
        username = new TextField("", skin);
        username.setMessageText("username");
        password = new TextField("", skin);
        password.setMessageText("password");
        password.setPasswordCharacter('*');
        password.setPasswordMode(true);
        newPassword = new TextField("", skin);
        newPassword.setMessageText("new password");
        newPassword.setPasswordMode(true);
        newPasswordConfirm = new TextField("", skin);
        newPasswordConfirm.setMessageText("confirm");
        newPasswordConfirm.setPasswordMode(true);
        email = new TextField("", skin);
        email.setMessageText("email");
        nickname = new TextField("", skin);
        nickname.setMessageText("nickname");
        changeUsername = new Image(new Texture(Resource.CHANGE_USERNAME_OFF.address()));
        changeUsername.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                changeUsername.setDrawable(new Image(new Texture(Resource.CHANGE_USERNAME_CLICKED.address())).getDrawable());
                if (isOnChangeUsername) {
                    isOnChangeUsername = false;
                    usernameGroup.clear();
                } else {
                    isOnChangeUsername = true;
                    isOnChangeNickname = false;
                    isOnChangeEmail = false;
                    isOnChangePassword = false;
                    changeNickname.setDrawable(new Image(new Texture(Resource.CHANGE_NICKNAME_OFF.address())).getDrawable());
                    changeEmail.setDrawable(new Image(new Texture(Resource.CHANGE_EMAIL_OFF.address())).getDrawable());
                    changePassword.setDrawable(new Image(new Texture(Resource.CHANGE_PASSWORD_OFF.address())).getDrawable());
                    usernameGroup.addActor(profileMessage);
                    usernameGroup.addActor(username);
                    usernameGroup.addActor(save);
                    nicknameGroup.clear();
                    emailGroup.clear();
                    passwordGroup.clear();
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
                    nicknameGroup.clear();
                } else {
                    isOnChangeUsername = false;
                    isOnChangeNickname = true;
                    isOnChangeEmail = false;
                    isOnChangePassword = false;
                    changeUsername.setDrawable(new Image(new Texture(Resource.CHANGE_USERNAME_OFF.address())).getDrawable());
                    changeEmail.setDrawable(new Image(new Texture(Resource.CHANGE_EMAIL_OFF.address())).getDrawable());
                    changePassword.setDrawable(new Image(new Texture(Resource.CHANGE_PASSWORD_OFF.address())).getDrawable());
                    nicknameGroup.addActor(profileMessage);
                    nicknameGroup.addActor(nickname);
                    nicknameGroup.addActor(save);
                    usernameGroup.clear();
                    emailGroup.clear();
                    passwordGroup.clear();
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
                    emailGroup.clear();
                } else {
                    isOnChangeUsername = false;
                    isOnChangeNickname = false;
                    isOnChangeEmail = true;
                    isOnChangePassword = false;
                    changeUsername.setDrawable(new Image(new Texture(Resource.CHANGE_USERNAME_OFF.address())).getDrawable());
                    changeNickname.setDrawable(new Image(new Texture(Resource.CHANGE_NICKNAME_OFF.address())).getDrawable());
                    changePassword.setDrawable(new Image(new Texture(Resource.CHANGE_PASSWORD_OFF.address())).getDrawable());
                    emailGroup.addActor(profileMessage);
                    emailGroup.addActor(email);
                    emailGroup.addActor(save);
                    usernameGroup.clear();
                    nicknameGroup.clear();
                    passwordGroup.clear();
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
                    passwordGroup.clear();
                } else {
                    isOnChangeUsername = false;
                    isOnChangeNickname = false;
                    isOnChangeEmail = false;
                    isOnChangePassword = true;
                    changeUsername.setDrawable(new Image(new Texture(Resource.CHANGE_USERNAME_OFF.address())).getDrawable());
                    changeNickname.setDrawable(new Image(new Texture(Resource.CHANGE_NICKNAME_OFF.address())).getDrawable());
                    changeEmail.setDrawable(new Image(new Texture(Resource.CHANGE_EMAIL_OFF.address())).getDrawable());
                    passwordGroup.addActor(profileMessage);
                    passwordGroup.addActor(password);
                    passwordGroup.addActor(newPassword);
                    passwordGroup.addActor(newPasswordConfirm);
                    passwordGroup.addActor(save);
                    usernameGroup.clear();
                    nicknameGroup.clear();
                    emailGroup.clear();
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
                //game.changeScreen(new HistoryView(game, currentUsername));
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
                game.changeScreen(new MainView(game, currentUsername));
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
                if (isOnChangeUsername)
                    perform(new Connector().perform(new Instruction(Command.CHANGE_USERNAME,
                            username.getText(),
                            currentUsername)));
                else if (isOnChangeNickname)
                    perform(new Connector().perform(new Instruction(Command.CHANGE_NICKNAME,
                            nickname.getText(),
                            currentUsername)));
                else if (isOnChangeEmail)
                    perform(new Connector().perform(new Instruction(Command.CHANGE_EMAIL,
                            email.getText(),
                            currentUsername)));
                else if (isOnChangePassword)
                    perform(new Connector().perform(new Instruction(Command.CHANGE_PASSWORD,
                            password.getText(),
                            newPassword.getText(),
                            newPasswordConfirm.getText(),
                            currentUsername)));
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
        leftInformationGroup.addActor(usernameMessage);
        leftInformationGroup.addActor(nicknameMessage);
        leftInformationGroup.addActor(maxScoreMessage);
        leftInformationGroup.addActor(rankMessage);
        rightInformationGroup.addActor(playCountMessage);
        rightInformationGroup.addActor(drawCountMessage);
        rightInformationGroup.addActor(winCountMessage);
        rightInformationGroup.addActor(defeatCountMessage);
        stage.addActor(background);
        stage.addActor(leftTable);
        stage.addActor(middleTable);
        stage.addActor(rightTable);
        stage.addActor(usernameGroup);
        stage.addActor(passwordGroup);
        stage.addActor(emailGroup);
        stage.addActor(nicknameGroup);
        stage.addActor(leftInformationGroup);
        stage.addActor(rightInformationGroup);
    }

    @Override
    protected void backgroundLoader() {
        background = new Image(new Texture(Resource.PROFILE_BACKGROUND.address()));
    }

    @Override
    protected void perform(Instruction instruction) {
        String[] arguments = instruction.arguments();
        switch (instruction.command()) {
            case PROFILE_MESSAGE:
                usernameMessage.setText(arguments[0]);
                nicknameMessage.setText(arguments[1]);
                maxScoreMessage.setText(arguments[2]);
                rankMessage.setText(arguments[3]);
                playCountMessage.setText(arguments[4]);
                drawCountMessage.setText(arguments[5]);
                winCountMessage.setText(arguments[6]);
                defeatCountMessage.setText(arguments[7]);
                break;
            case CHANGE_FIELD_MESSAGE:
                if (!Objects.equals(arguments[0], ""))
                    profileMessage.setText(arguments[0]);
                else {
                    if (isOnChangeUsername) {
                        isOnChangeUsername = false;
                        usernameGroup.clear();
                        changeUsername.setDrawable(new Image(new Texture(Resource.CHANGE_USERNAME_OFF.address())).getDrawable());
                    } else if (isOnChangeNickname) {
                        isOnChangeNickname = false;
                        nicknameGroup.clear();
                        changeNickname.setDrawable(new Image(new Texture(Resource.CHANGE_NICKNAME_OFF.address())).getDrawable());
                    } else if (isOnChangeEmail) {
                        isOnChangeEmail = false;
                        emailGroup.clear();
                        changeEmail.setDrawable(new Image(new Texture(Resource.CHANGE_EMAIL_OFF.address())).getDrawable());
                    } else if (isOnChangePassword) {
                        isOnChangePassword = false;
                        passwordGroup.clear();
                        changePassword.setDrawable(new Image(new Texture(Resource.CHANGE_PASSWORD_OFF.address())).getDrawable());
                    }
                }
                if (isOnChangeUsername)
                    username.setText("");
                else if (isOnChangeNickname)
                    nickname.setText("");
                else if (isOnChangeEmail)
                    email.setText("");
                else if (isOnChangePassword) {
                    password.setText("");
                    newPassword.setText("");
                    newPasswordConfirm.setText("");
                }
                break;
        }
    }
}
