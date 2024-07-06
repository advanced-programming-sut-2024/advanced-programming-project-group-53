package view.graphic;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import game.GWENT;
import model.view.Resource;
import network.Instruction;

import java.util.ArrayList;
import java.util.Objects;

public class TournamentView extends View {
    private Image exit;
    private Image resign;

    public TournamentView(GWENT game, String currentUsername) {
        super(game);
        this.currentUsername = currentUsername;
        stage.addActor(background);
    }

    @Override
    protected void backgroundLoader() {
        background = new Image(new Texture(Resource.TOURNAMENT_BACKGROUND.address()));
    }

    @Override
    protected void perform(Instruction instruction) {

    }

    private void firstRound(String[][] arrangement) {
        for (int i = 0; i < arrangement.length; i++) {
            Label label1 = new Label(arrangement[i][0], skin);
            label1.setPosition(50 + (float) ((i / 2) * 924), 974 - (float) ((i % 2) * 462));
            Label label2 = new Label(arrangement[i][1], skin);
            label2.setPosition(50 + (float) ((i / 2) * 924), 974 - (float) ((i % 2) * 462) - 206);
            stage.addActor(label1);
            stage.addActor(label2);
        }
        for (int i = 0; i < 3; i++) {
            TextButton textButton = new TextButton("round1", skin);
            textButton.setPosition(100 + (float) ((i / 2) * 924), 974 - (float) ((i % 2) * 462) - 103);
            int finalI = i;
            textButton.addListener(new ClickListener() {
                @Override
                public void clicked(InputEvent event, float x, float y) {
                    if (Objects.equals(currentUsername, arrangement[finalI][0]) ||
                            Objects.equals(currentUsername, arrangement[finalI][1])) {
                        //TODO: start game!
                    }
                }
            });
            stage.addActor(textButton);
        }
    }

    private void secondRound(String[][] arrangement) {
        for (int i = 0; i < arrangement.length; i++) {
            Label label1 = new Label(arrangement[i][0], skin);
            label1.setPosition(200 + i * 774, 562);
            Label label2 = new Label(arrangement[i][1], skin);
            label2.setPosition(200 + i * 774, 462);
            stage.addActor(label1);
            stage.addActor(label2);
        }
        for (int i = 0; i < 2; i++) {
            TextButton textButton = new TextButton("round2", skin);
            textButton.setPosition(200 + i * 774, 512);
            int finalI = i;
            textButton.addListener(new ClickListener() {
                @Override
                public void clicked(InputEvent event, float x, float y) {
                    if (Objects.equals(currentUsername, arrangement[finalI][0]) ||
                            Objects.equals(currentUsername, arrangement[finalI][1])) {
                        //TODO: start game!
                    }
                }
            });
            stage.addActor(textButton);
        }
    }

    private void lastRound(String[][] arrangement) {
        Label label1 = new Label(arrangement[0][0], skin);
        label1.setPosition(350, 512);
        Label label2 = new Label(arrangement[0][1], skin);
        label2.setPosition(624, 512);
        stage.addActor(label1);
        stage.addActor(label2);
        TextButton textButton = new TextButton("round3", skin);
        textButton.setPosition(487, 512);
        int finalI = 0;
        textButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if (Objects.equals(currentUsername, arrangement[finalI][0]) ||
                        Objects.equals(currentUsername, arrangement[finalI][1])) {
                    //TODO: start game!
                }
            }
        });
        stage.addActor(textButton);
    }
}
