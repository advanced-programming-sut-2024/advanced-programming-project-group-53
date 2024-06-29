package game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.utils.ScreenUtils;
import view.graphic.LoginView;
import view.graphic.RankingView;
import view.graphic.View;

public class GWENT extends Game {
    private View view;

    @Override
    public void create() {
        view = new RankingView(this, "a");
        setScreen(view);
    }

    @Override
    public void render() {
        ScreenUtils.clear(0, 0, 0, 0);
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
