package game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.utils.ScreenUtils;
import view.FirstView;

public class GWENT extends Game {

    @Override
    public void create() {
        setScreen(new FirstView(this));
    }

    @Override
    public void render() {
        ScreenUtils.clear(0.184f, 0.09f, 0.035f, 1);
        super.render();
    }

    @Override
    public void dispose() {
    }

    public void changeScreen(view.graphic.View view) {
        this.screen.dispose();
        this.setScreen(view);
    }

    public void changeScreen(view.single.View view) {
        this.screen.dispose();
        this.setScreen(view);
    }
}
