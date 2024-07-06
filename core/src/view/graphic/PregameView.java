package view.graphic;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import game.GWENT;
import model.view.Resource;
import network.Instruction;

public class PregameView extends View {

    public PregameView(GWENT game) {
        super(game);
    }

    @Override
    protected void backgroundLoader() {
        background = new Image(new Texture(Resource.PREGAME_BACKGROUND.address()));
    }

    @Override
    protected void perform(Instruction instruction) {

    }
}
