package view.graphic;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import game.GWENT;
import model.view.Resource;
import network.Instruction;

public class DeckExportView extends View {

    public DeckExportView(GWENT game, String currentUsername) {
        super(game);
    }

    @Override
    protected void backgroundLoader() {
        background = new Image(new Texture(Resource.START_BACKGROUND.address()));
    }

    @Override
    protected void perform(Instruction instruction) {

    }
}