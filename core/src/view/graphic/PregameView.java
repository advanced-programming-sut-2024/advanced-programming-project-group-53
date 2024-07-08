package view.graphic;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import controller.StartMenu;
import game.GWENT;
import model.view.Resource;
import network.Command;
import network.Connector;
import network.Instruction;

import java.util.Objects;

public class PregameView extends View {
    private final Image exit;
    private final Image random;
    private final ScrollPane friends;
    private final VerticalGroup group;
    private final Table table;

    public PregameView(GWENT game, String currentUsername) {
        super(game);
        this.menu = StartMenu.getInstance();
        this.currentUsername = currentUsername;
        group = new VerticalGroup();
        group.space(10);
        friends = new ScrollPane(group, skin);
        perform(new Connector().perform(new Instruction(Command.FRIEND, currentUsername)));
        table = new Table();
        table.setBounds(312, 100, 400, (float) (400 * 0.1458 * 2));
        Label wait = new Label("", skin);
        random = new Image(new Texture(Resource.RANDOM_OFF.address()));
        random.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                random.setDrawable(new Image(new Texture(Resource.RANDOM_CLICKED.address())).getDrawable());
                wait.setText("Please wait we are looking for an opponent");
                wait.setPosition(300, 924);
            }

            @Override
            public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
                random.setDrawable(new Image(new Texture(Resource.RANDOM_ON.address())).getDrawable());
            }

            @Override
            public void exit(InputEvent event, float x, float y, int pointer, Actor toActor) {
                random.setDrawable(new Image(new Texture(Resource.RANDOM_OFF.address())).getDrawable());
            }
        });
        exit = new Image(new Texture(Resource.EXIT_OFF.address()));
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
        table.add(random);
        table.row();
        table.add(exit);
        stage.addActor(background);
        stage.addActor(table);
        stage.addActor(wait);
        stage.addActor(friends);
    }

    @Override
    protected void backgroundLoader() {
        background = new Image(new Texture(Resource.PREGAME_BACKGROUND.address()));
    }

    @Override
    protected void perform(Instruction instruction) {
        String[] response = instruction.arguments();
        String empty = response[0];
        StringBuilder builder = new StringBuilder();
        for (String string : instruction.arguments())
            builder.append(string).append(" ");
        String arguments = builder.toString().trim();
        switch (instruction.command()) {
            case FRIEND_MESSAGE:
                if (!Objects.equals(empty, "empty")) {
                    group.clear();
                    for (String name : response)
                        group.addActor(new Label(name, skin));
                }
                break;
        }
    }
}
