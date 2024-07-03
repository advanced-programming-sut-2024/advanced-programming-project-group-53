package game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.utils.ScreenUtils;
import view.single.GameView;
import view.single.LoginView;
import view.single.View;

public class GWENT extends Game {
    private View view;

    @Override
    public void create() {
        view = new GameView(this,"a","g");
        setScreen(view);
    }

    @Override
    public void render() {
        ScreenUtils.clear(0.184f, 0.09f, 0.035f, 1);
        super.render();
    }

    @Override
    public void dispose() {
    }

    public void changeScreen(View view) {
        this.view.dispose();
        this.view = view;
        this.setScreen(view);
    }
}
