package view.single;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import controller.ProfileMenu;
import game.GWENT;
import view.graphic.Resource;

public class HistoryView extends View {
    private final Label history;
    private final TextField number;
    private final Image normal;
    private final Image specified;
    private final Image profile;
    private final Image exit;
    private boolean isOnNormal = false;
    private boolean isOnSpecified = false;

    public HistoryView(GWENT game, String currentUsername) {
        super(game);
        this.currentUsername = currentUsername;
        menu = ProfileMenu.getInstance();
        Table optionTable = new Table();
        optionTable.setBounds(62, 913, 900, 61);
        optionTable.align(Align.center);
        number = new TextField("", textField);
        number.setMessageText("number");
        history = new Label("", label);
        history.setPosition(512 - history.getWidth() / 2, 512 - history.getHeight() / 2);
        normal = new Image(new Texture(Resource.NORMAL_OFF.address()));
        normal.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                normal.setDrawable(new Image(new Texture(Resource.NORMAL_CLICKED.address())).getDrawable());
                if (isOnNormal) {
                    isOnNormal = false;
                } else {
                    specified.setDrawable(new Image(new Texture(Resource.SPECIFIED_OFF.address())).getDrawable());
                    isOnSpecified = false;
                    isOnNormal = true;
                    String response = ((ProfileMenu) menu).showGameHistory(
                            currentUsername,
                            "true",
                            number.getText());
                    history.setText(response);
                }
            }

            @Override
            public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
                if (!isOnNormal)
                    normal.setDrawable(new Image(new Texture(Resource.NORMAL_ON.address())).getDrawable());
            }

            @Override
            public void exit(InputEvent event, float x, float y, int pointer, Actor toActor) {
                if (!isOnNormal)
                    normal.setDrawable(new Image(new Texture(Resource.NORMAL_OFF.address())).getDrawable());
            }
        });
        specified = new Image(new Texture(Resource.SPECIFIED_OFF.address()));
        specified.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                specified.setDrawable(new Image(new Texture(Resource.SPECIFIED_CLICKED.address())).getDrawable());
                if (isOnSpecified) {
                    isOnSpecified = false;
                } else {
                    normal.setDrawable(new Image(new Texture(Resource.NORMAL_OFF.address())).getDrawable());
                    isOnNormal = false;
                    isOnSpecified = true;
                    String response = ((ProfileMenu) menu).showGameHistory(
                            currentUsername,
                            "false",
                            number.getText());
                    history.setText(response);
                }
            }

            @Override
            public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
                if (!isOnSpecified)
                    specified.setDrawable(new Image(new Texture(Resource.SPECIFIED_ON.address())).getDrawable());
            }

            @Override
            public void exit(InputEvent event, float x, float y, int pointer, Actor toActor) {
                if (!isOnSpecified)
                    specified.setDrawable(new Image(new Texture(Resource.SPECIFIED_OFF.address())).getDrawable());
            }
        });
        profile = new Image(new Texture(Resource.PROFILE_OFF.address()));
        profile.setPosition(574, 50);
        profile.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                profile.setDrawable(new Image(new Texture(Resource.PROFILE_CLICKED.address())).getDrawable());
                game.changeScreen(new ProfileView(game, currentUsername));
            }

            @Override
            public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
                profile.setDrawable(new Image(new Texture(Resource.PROFILE_ON.address())).getDrawable());
            }

            @Override
            public void exit(InputEvent event, float x, float y, int pointer, Actor toActor) {
                profile.setDrawable(new Image(new Texture(Resource.PROFILE_OFF.address())).getDrawable());
            }
        });
        exit = new Image(new Texture(Resource.EXIT_OFF.address()));
        exit.setPosition(50, 50);
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
        optionTable.add(normal);
        optionTable.add(number);
        optionTable.add(specified);
        stage.addActor(background);
        stage.addActor(history);
        stage.addActor(optionTable);
        stage.addActor(exit);
        stage.addActor(profile);
    }

    @Override
    protected void backgroundLoader() {
        background = new Image(new Texture(Resource.HISTORY_BACKGROUND.address()));
    }
}
