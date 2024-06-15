package view.graphic;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import controller.LoginMenu;
import game.GWENT;

public class ForgetPasswordView extends MenuView {
    private final Table textTable;
    private final Table forgetPasswordTable;
    private final Label question;
    private final TextField answer;
    private final TextField newPassword;
    private final TextField newPasswordConfirm;
    private final Image changePassword;
    private final Image loginMenu;
    private final Image exitMenu;
    private final Image exitGame;

    public ForgetPasswordView(GWENT game) {
        super(game);
        menu = LoginMenu.getInstance();
        forgetPasswordTable = new Table();
        forgetPasswordTable.setBounds(50, 50, 400, 400 / 1.48f);
        textTable = new Table();
        textTable.setBounds(635, 170, 400, 400);
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
        changePassword = new Image(new Texture("button/changePasswordOn.png"));
        changePassword.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                System.exit(0);
            }
        });
        loginMenu = new Image(new Texture("button/loginMenuOn.png"));
        loginMenu.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                System.exit(0);
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
        textTable.add(question);
        textTable.row();
        textTable.add(answer);
        textTable.row();
        textTable.add(newPassword);
        textTable.row();
        textTable.add(newPasswordConfirm);
        textTable.row();
        forgetPasswordTable.add(changePassword);
        forgetPasswordTable.row();
        forgetPasswordTable.add(loginMenu);
        forgetPasswordTable.row();
        forgetPasswordTable.add(exitMenu);
        forgetPasswordTable.row();
        forgetPasswordTable.add(exitGame);
        forgetPasswordTable.row();
        stage.addActor(background);
        stage.addActor(forgetPasswordTable);
        stage.addActor(textTable);
    }

    @Override
    protected void backgroundLoader() {
        background = new Image(new Texture("background/loginBackground.5.jpeg"));
    }
}
