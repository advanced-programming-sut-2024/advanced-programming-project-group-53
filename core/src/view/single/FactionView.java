package view.single;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import controller.StartMenu;
import game.GWENT;
import model.card.Faction;
import view.Resource;

import java.util.HashMap;

public class FactionView extends View {
    private final HashMap<Image, Faction> factionImages;
    private Faction currentFaction = Faction.NorthernRealms;
    private Image faction;
    private Image lastFaction;
    private final Image save;

    public FactionView(GWENT game, String currentUsername) {
        super(game);
        this.currentUsername = currentUsername;
        menu = StartMenu.getInstance();
        factionImages = new HashMap<>();
        for (Faction faction : Faction.values()) {
            if (faction != Faction.Neutral) {
                Image image = new Image(new Texture(faction.address()));
                image.setWidth(150);
                image.setHeight(225);
                factionImages.put(image, faction);
            }
        }
        for (Image image : factionImages.keySet()) {
            if (factionImages.get(image) == Faction.NorthernRealms)
                faction = image;
            image.addListener(new ClickListener() {
                @Override
                public void clicked(InputEvent event, float x, float y) {
                    if (faction != image) {
                        lastFaction = faction;
                        faction = image;
                        float xOld = faction.getX();
                        float yOld = faction.getY();
                        float xOriginOld = faction.getOriginX();
                        float yOriginOld = faction.getOriginY();
                        float rotation = faction.getRotation();
                        faction.setPosition(lastFaction.getX(), lastFaction.getY());
                        faction.setOrigin(lastFaction.getOriginX(), lastFaction.getOriginY());
                        faction.setRotation(lastFaction.getRotation());
                        lastFaction.setPosition(xOld, yOld);
                        lastFaction.setOrigin(xOriginOld, yOriginOld);
                        lastFaction.setRotation(rotation);
                        currentFaction = factionImages.get(image);
                    }
                }

                @Override
                public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
                    image.setScale(1.01f);
                }

                @Override
                public void exit(InputEvent event, float x, float y, int pointer, Actor toActor) {
                    image.setScale(1);
                }
            });
            image.setOrigin(image.getWidth() / 2, image.getHeight() / 2);
            switch (factionImages.get(image)) {
                case Monsters:
                    image.setPosition(150, 254);
                    image.setRotation(45);
                    break;
                case NilfgaardianEmpire:
                    image.setPosition(150, 570);
                    image.setRotation(-45);
                    break;
                case NorthernRealms:
                    image.setPosition(437, 412);
                    break;
                case Scoiatael:
                    image.setPosition(724, 254);
                    image.setRotation(-45);
                    break;
                case Skellige:
                    image.setPosition(724, 570);
                    image.setRotation(45);
            }
        }
        Table table = new Table();
        table.setBounds(312, 50, 400, (float) (400 * 0.1458 * 3));
        table.align(Align.center);
        save = new Image(new Texture(Resource.SAVE_OFF.address()));
        save.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                save.setDrawable(new Image(new Texture(Resource.SAVE_CLICKED.address())).getDrawable());
                game.changeScreen(new CommanderView(game, currentFaction, currentUsername));
            }

            @Override
            public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
                save.setDrawable(new Image(new Texture(Resource.SAVE_ON.address())).getDrawable());
            }

            @Override
            public void exit(InputEvent event, float x, float y, int pointer, Actor toActor) {
                save.setDrawable(new Image(new Texture(Resource.SAVE_OFF.address())).getDrawable());
            }
        });
        Image mainMenu = new Image(new Texture(Resource.MAIN_MENU_OFF.address()));
        mainMenu.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                mainMenu.setDrawable(new Image(new Texture(Resource.MAIN_MENU_CLICKED.address())).getDrawable());
                game.changeScreen(new MainView(game, currentUsername));
            }

            @Override
            public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
                mainMenu.setDrawable(new Image(new Texture(Resource.MAIN_MENU_ON.address())).getDrawable());
            }

            @Override
            public void exit(InputEvent event, float x, float y, int pointer, Actor toActor) {
                mainMenu.setDrawable(new Image(new Texture(Resource.MAIN_MENU_OFF.address())).getDrawable());
            }
        });
        Image exit = new Image(new Texture(Resource.EXIT_OFF.address()));
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
        table.add(save);
        table.row();
        table.add(mainMenu);
        table.row();
        table.add(exit);
        stage.addActor(background);
        for (Image image : factionImages.keySet())
            stage.addActor(image);
        stage.addActor(table);
    }

    public FactionView(GWENT game, String currentUsername, String username1) {
        super(game);
        this.username1 = username1;
        this.currentUsername = currentUsername;
        menu = StartMenu.getInstance();
        factionImages = new HashMap<>();
        for (Faction faction : Faction.values()) {
            if (faction != Faction.Neutral) {
                Image image = new Image(new Texture(faction.address()));
                image.setWidth(150);
                image.setHeight(225);
                factionImages.put(image, faction);
            }
        }
        for (Image image : factionImages.keySet()) {
            if (factionImages.get(image) == Faction.NorthernRealms)
                faction = image;
            image.addListener(new ClickListener() {
                @Override
                public void clicked(InputEvent event, float x, float y) {
                    if (faction != image) {
                        lastFaction = faction;
                        faction = image;
                        float xOld = faction.getX();
                        float yOld = faction.getY();
                        float xOriginOld = faction.getOriginX();
                        float yOriginOld = faction.getOriginY();
                        float rotation = faction.getRotation();
                        faction.setPosition(lastFaction.getX(), lastFaction.getY());
                        faction.setOrigin(lastFaction.getOriginX(), lastFaction.getOriginY());
                        faction.setRotation(lastFaction.getRotation());
                        lastFaction.setPosition(xOld, yOld);
                        lastFaction.setOrigin(xOriginOld, yOriginOld);
                        lastFaction.setRotation(rotation);
                        currentFaction = factionImages.get(image);
                    }
                }

                @Override
                public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
                    image.setScale(1.01f);
                }

                @Override
                public void exit(InputEvent event, float x, float y, int pointer, Actor toActor) {
                    image.setScale(1);
                }
            });
            image.setOrigin(image.getWidth() / 2, image.getHeight() / 2);
            switch (factionImages.get(image)) {
                case Monsters:
                    image.setPosition(150, 254);
                    image.setRotation(45);
                    break;
                case NilfgaardianEmpire:
                    image.setPosition(150, 570);
                    image.setRotation(-45);
                    break;
                case NorthernRealms:
                    image.setPosition(437, 412);
                    break;
                case Scoiatael:
                    image.setPosition(724, 254);
                    image.setRotation(-45);
                    break;
                case Skellige:
                    image.setPosition(724, 570);
                    image.setRotation(45);
            }
        }
        save = new Image(new Texture(Resource.SAVE_OFF.address()));
        save.setPosition(312, 50);
        save.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                save.setDrawable(new Image(new Texture(Resource.SAVE_CLICKED.address())).getDrawable());
                game.changeScreen(new CommanderView(game, currentFaction, currentUsername, username1));
            }

            @Override
            public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
                save.setDrawable(new Image(new Texture(Resource.SAVE_ON.address())).getDrawable());
            }

            @Override
            public void exit(InputEvent event, float x, float y, int pointer, Actor toActor) {
                save.setDrawable(new Image(new Texture(Resource.SAVE_OFF.address())).getDrawable());
            }
        });
        stage.addActor(background);
        for (Image image : factionImages.keySet())
            stage.addActor(image);
        stage.addActor(save);
    }

    @Override
    protected void backgroundLoader() {
        background = new Image(new Texture(Resource.START_BACKGROUND.address()));
    }
}
