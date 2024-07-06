package view.graphic;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import controller.GameMenu;
import game.GWENT;
import network.Instruction;
import model.view.Resource;
import view.single.MainView;

import java.util.ArrayList;
import java.util.Random;

public class EndView extends View {
    private final static ArrayList<String> quotes = new ArrayList<>();

    static {
        quotes.add("We must believe that we are gifted for something, and that this thing,\nat whatever cost, must be attained.\n- Marie Curie");
        quotes.add("The older I get, the greater power I seem to have to help the world;\nI am like a snowball - the further I am rolled the more I gain.\n- Susan B. Anthony");
        quotes.add("Knowledge is love and light and vision.\n- Helen Keller");
        quotes.add("Wisdom is knowing what to do next. Skill is knowing how to do it.\nVirtue is doing it.\n- Thomas Jefferson");
        quotes.add("When one door of happiness closes, another one opens;\nbut we look so long at the closed door that we do not see\nthe one which has opened for us.\n- Helen Keller");
        quotes.add("Poor eyes limit your sight; poor vision limits your deeds.\n- Franklin Field");
        quotes.add("I do not pray for success. I ask for faithfulness.\n- Mother Teresa");
        quotes.add("I used to ask God to help me. Then I asked if I might help him.\n- Hudson Taylor");
        quotes.add("The wise person doesn't give the right answers,\nbut poses the right questions.\n- Claude Levi-Strauss");
        quotes.add("What happens is not as important as how you react to what happens.\n- Ellen Glasgow");
        quotes.add("The journey of a thousand miles begins with one step.\n- Lao Tzu");
        quotes.add("The only true wisdom is in knowing you know nothing.\n- Socrates");
        quotes.add("Just as treasures are uncovered from the earth,\nso virtue appears from good deeds,\nand wisdom appears from a pure and peaceful mind.\nTo walk safely through the maze of human life,\none needs the light of wisdom and the guidance of virtue.\n- Buddha");
        quotes.add("True wisdom comes to each of us when we realize how little we understand about life,\nourselves, and the world around us.\n- Socrates");
        quotes.add("Tell me and I'll forget; show me and I may remember;\ninvolve me and I'll understand.\n- Chinese Proverb");
        quotes.add("Just remember the world is not a playground but a schoolroom.\nLife is not a holiday but an education.\nOne eternal lesson for us all: to teach us how better we should love.\n- Barbra Jordan");
        quotes.add("Prayer is the raising of one's mind and heart to God or the requesting of good things from God.\n- St. John Damascene, Theologian (675-746 AD)");
        quotes.add("Prayer is not an old woman's idle amusement. Properly understood and applied,\nit is the most potent instrument of action.\n- Mahatma Gandhi");
        quotes.add("Perhaps instead of bombarding God with requests for what is not, we might try, instead,\nasking God to open our eyes to see what is.\n- Margaret Silf");
        quotes.add("Listen to the wind, it talks. Listen to the silence, it speaks.\nListen to your heart, it knows.\n– Ojibwe Prayer");
        quotes.add("Grace is not part of consciousness;\nit is the amount of light in our souls, not knowledge nor reason.\n- Pope Francis");
        quotes.add("There are two ways of spreading light:\nto be the candle or the mirror that reflects it.\n- Edith Wharton");
    }

    private final Image mainMenu;
    private final Image exit;

    public EndView(GWENT game, String winner, String loser, String currentUser) {
        super(game);
        menu = GameMenu.getInstance();
        this.currentUsername = currentUser;
        Label winner1 = new Label("WINNER:\n" + winner, skin);
        winner1.setColor(Color.GREEN);
        winner1.setPosition(512 - winner1.getWidth() / 2, 800);
        winner1.setAlignment(Align.center);
        Label loser1 = new Label("LOSER:\n" + loser, skin);
        loser1.setColor(Color.RED);
        loser1.setPosition(512 - loser1.getWidth() / 2, 700);
        loser1.setAlignment(Align.center);
        Random random = new Random();
        int index = random.nextInt(quotes.size());
        Label quote = new Label(quotes.get(index), skin);
        quote.setAlignment(Align.center);
        quote.setPosition(512 - quote.getWidth() / 2, 450 - quote.getHeight());
        mainMenu = new Image(new Texture(Resource.MAIN_MENU_OFF.address()));
        mainMenu.setPosition(100, 500);
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
        exit = new Image(new Texture(Resource.EXIT_OFF.address()));
        exit.setPosition(524, 500);
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
        stage.addActor(background);
        stage.addActor(quote);
        stage.addActor(mainMenu);
        stage.addActor(exit);
        stage.addActor(winner1);
        stage.addActor(loser1);
    }

    @Override
    protected void backgroundLoader() {
        background = new Image(new Texture(Resource.END_BACKGROUND.address()));
    }

    @Override
    protected void perform(Instruction instruction) {

    }
}