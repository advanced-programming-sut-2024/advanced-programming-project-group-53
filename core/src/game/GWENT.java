package game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.ScreenUtils;
import network.Client;
import view.graphic.View;
import view.graphic.RegisterView;

public class GWENT extends Game {
    private View menu;
    private double width;
    private double height;
    private Client client;

    public GWENT(Client client) {
        this.client = client;
    }

    @Override
    public void create() {
        menu = new RegisterView(this);
        setScreen(menu);
        width = Gdx.graphics.getWidth();
        height = Gdx.graphics.getHeight();
    }

    @Override
    public void render() {
        ScreenUtils.clear(0, 0, 0, 0);
        super.render();
    }

    @Override
    public void dispose() {
    }

    public double getWidth() {
        return width;
    }

    public double getHeight() {
        return height;
    }
    public void changeScreen(View view) {
        this.setScreen(view);
    }
}
