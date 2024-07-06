package view.graphic;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import game.GWENT;
import model.view.Resource;
import network.Instruction;

public class TournamentView extends View {
    public TournamentView(GWENT game) {
        super(game);
    }

    @Override
    protected void backgroundLoader() {
        background = new Image(new Texture(Resource.TOURNAMENT_BACKGROUND.address()));
    }

    @Override
    protected void perform(Instruction instruction) {

    }
}
