package view.graphic;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import controller.ChatMenu;
import game.GWENT;
import network.Command;
import network.Connector;
import network.Instruction;
import view.ChatContainer;
import view.Resource;

import java.util.ArrayList;

public class ChatView extends View {
    private ArrayList<HorizontalGroup> horizontalGroups;
    private final VerticalGroup group;
    private final ScrollPane chat;
    private final Label error;
    private final Label who;
    private final TextField username;
    private final TextField input;
    private final Image save;
    private final Image send;
    private final Image mainMenu;
    private final Image exit;

    public ChatView(GWENT game, String currentUsername) {
        super(game);
        this.currentUsername = currentUsername;
        menu = ChatMenu.getInstance();
        group = new VerticalGroup();
        group.setFillParent(true);
        group.space(20);
        chat = new ScrollPane(group, skin);
        chat.setFlickScroll(true);
        chat.setFadeScrollBars(false);
        username = new TextField("", skin);
        username.setMessageText("Receiver");
        username.setPosition(400, 950);
        input = new TextField("", skin);
        error = new Label("", skin);
        error.setPosition(50, 100);
        who = new Label("", skin);
        who.setPosition(150, 950);
        Table table = new Table();
        table.setBounds(574, 50, 400, (float) (400 * 0.1458 * 3));
        input.setPosition(287 - input.getWidth() / 2, 50 + table.getHeight() / 2 - input.getHeight() / 2);
        chat.setPosition(50, 50 + table.getHeight());
        chat.setBounds(50, 50 + table.getHeight(), 475, 675);
        exit = new Image(new Texture(Resource.EXIT_OFF.address()));
        exit.setPosition(900, 0);
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
        mainMenu = new Image(new Texture(Resource.MAIN_MENU_OFF.address()));
        mainMenu.setPosition(500, 0);
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
        send = new Image(new Texture(Resource.SEND_OFF.address()));
        send.setPosition(100, 0);
        send.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                send.setDrawable(new Image(new Texture(Resource.SEND_CLICKED.address())).getDrawable());
                perform(new Connector().perform(new Instruction(Command.SEND, input.getText(), currentUsername, who.getText().toString())));
                input.setText("");
                perform(new Connector().perform(new Instruction(Command.SHOW_CHAT, who.getText().toString(), currentUsername)));
            }

            @Override
            public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
                send.setDrawable(new Image(new Texture(Resource.SEND_ON.address())).getDrawable());
            }

            @Override
            public void exit(InputEvent event, float x, float y, int pointer, Actor toActor) {
                send.setDrawable(new Image(new Texture(Resource.SEND_OFF.address())).getDrawable());
            }
        });
        save = new Image(new Texture(Resource.SAVE_OFF.address()));
        save.setPosition(574, 950);
        save.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                save.setDrawable(new Image(new Texture(Resource.SAVE_CLICKED.address())).getDrawable());
                perform(new Connector().perform(new Instruction(Command.USERNAME_VALIDATION, username.getText())));
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
        table.add(mainMenu);
        table.row();
        table.add(exit);
        table.row();
        table.add(send);
        stage.addActor(background);
        stage.addActor(chat);
        stage.addActor(table);
        stage.addActor(username);
        stage.addActor(input);
        stage.addActor(save);
        stage.addActor(who);
        stage.addActor(error);
    }

    @Override
    protected void backgroundLoader() {
        background = new Image(new Texture(Resource.CHAT_BACKGROUND.address()));
    }

    @Override
    protected void perform(Instruction instruction) {
        StringBuilder builder = new StringBuilder();
        for (String string : instruction.arguments())
            builder.append(string).append(" ");
        String arguments = builder.toString().trim();
        switch (instruction.command()) {
            case USERNAME_VALIDATION_MESSAGE:
                if (!arguments.equals("empty")) {
                    username.setText("");
                    error.setText(arguments);
                } else {
                    who.setText(username.getText());
                    username.setText("");
                    perform(new Connector().perform(new Instruction(Command.SHOW_CHAT, who.getText().toString(), currentUsername)));
                }
                break;
            case SHOW_CHAT_MESSAGE:
                if (!arguments.equals("empty"))
                    updateContent(arguments);
                break;
            case SEND_MESSAGE:
                if (!arguments.equals("empty"))
                    error.setText(arguments);
        }
    }

    private void updateContent(String content) {
        group.clear();
        horizontalGroups = new ArrayList<>();
        String[] messages = content.split("\\\\");
        for (String message : messages) {
            HorizontalGroup group = new HorizontalGroup();
            String[] contents = message.split("/");
            group.addActor(new Label("message:\n" +
                    contents[0] +
                    "\nfrom: " +
                    contents[1] +
                    "\nto: " +
                    contents[2] +
                    "\ndate: " +
                    contents[3],
                    skin));
            horizontalGroups.add(group);
        }
        for (HorizontalGroup horizontalGroup : horizontalGroups)
            group.addActor(horizontalGroup);
    }
}
