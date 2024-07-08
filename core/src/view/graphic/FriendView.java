package view.graphic;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import controller.FriendMenu;
import game.GWENT;
import model.view.Resource;
import network.Command;
import network.Connector;
import network.Instruction;

import java.util.Objects;


public class FriendView extends View {
    private final TextField add;
    private final Image addSave;
    private final Label error;
    private final VerticalGroup groupRequestIn;
    private final VerticalGroup groupRequestOut;
    private final VerticalGroup groupFriend;
    private final Image profile;
    private final Image exit;

    public FriendView(GWENT game, String currentUsername) {
        super(game);
        this.currentUsername = currentUsername;
        menu = FriendMenu.getInstance();
        add = new TextField("", skin);
        add.setMessageText("new friend");
        error = new Label("", skin);
        error.setPosition(50, 924);
        add.setPosition(100 + error.getWidth(), 924);
        groupRequestOut = new VerticalGroup();
        groupRequestOut.space(10);
        groupRequestIn = new VerticalGroup();
        groupRequestIn.space(10);
        groupFriend = new VerticalGroup();
        groupFriend.space(10);
        perform(new Connector().perform(new Instruction(Command.REQUEST_IN, currentUsername)));
        perform(new Connector().perform(new Instruction(Command.REQUEST_OUT, currentUsername)));
        perform(new Connector().perform(new Instruction(Command.FRIEND, currentUsername)));
        ScrollPane requestsOut = new ScrollPane(groupRequestOut, skin);
        requestsOut.setBounds(537, 150, 437, 325);
        requestsOut.setFlickScroll(true);
        requestsOut.setFadeScrollBars(false);
        ScrollPane requestsIn = new ScrollPane(groupRequestIn, skin);
        requestsIn.setBounds(537, 499, 437, 325);
        requestsIn.setFlickScroll(true);
        requestsIn.setFadeScrollBars(false);
        ScrollPane friends = new ScrollPane(groupFriend, skin);
        friends.setBounds(50, 150, 437, 674);
        friends.setFlickScroll(true);
        friends.setFadeScrollBars(false);
        profile = new Image(new Texture(Resource.PROFILE_OFF.address()));
        profile.setPosition(50, 50);
        profile.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                profile.setDrawable(new Image(new Texture(Resource.PROFILE_CLICKED.address())).getDrawable());
                game.changeScreen(new ProfileView(game, currentUsername));
            }

            @Override
            public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
                profile.setDrawable(new Image(new Texture(Resource.PROFILE_ON.address())).getDrawable());
            }

            @Override
            public void exit(InputEvent event, float x, float y, int pointer, Actor toActor) {
                profile.setDrawable(new Image(new Texture(Resource.PROFILE_OFF.address())).getDrawable());
            }
        });
        exit = new Image(new Texture(Resource.EXIT_OFF.address()));
        exit.setPosition(574, 50);
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
        addSave = new Image(new Texture(Resource.SAVE_OFF.address()));
        addSave.setPosition(574, 924);
        addSave.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                addSave.setDrawable(new Image(new Texture(Resource.SAVE_CLICKED.address())).getDrawable());
                perform(new Connector().perform(new Instruction(Command.SEND_REQUEST, currentUsername, add.getText())));
            }

            @Override
            public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
                addSave.setDrawable(new Image(new Texture(Resource.SAVE_ON.address())).getDrawable());
            }

            @Override
            public void exit(InputEvent event, float x, float y, int pointer, Actor toActor) {
                addSave.setDrawable(new Image(new Texture(Resource.SAVE_OFF.address())).getDrawable());
            }
        });
        stage.addActor(background);
        stage.addActor(add);
        stage.addActor(addSave);
        stage.addActor(profile);
        stage.addActor(exit);
        stage.addActor(error);
        stage.addActor(friends);
        stage.addActor(requestsIn);
        stage.addActor(requestsOut);
    }

    @Override
    protected void backgroundLoader() {
        background = new Image(new Texture(Resource.FRIEND_BACKGROUND.address()));
    }

    @Override
    protected void perform(Instruction instruction) {
        token(instruction);
        String[] response = instruction.arguments();
        String empty = response[0];
        StringBuilder builder = new StringBuilder();
        for (String string : instruction.arguments())
            builder.append(string).append(" ");
        String arguments = builder.toString().trim();
        switch (instruction.command()) {
            case REQUEST_IN_MESSAGE:
                if (!Objects.equals(empty, "empty")) {
                    groupRequestIn.clear();
                    for (String name : response) {
                        HorizontalGroup group = new HorizontalGroup();
                        Image accept = new Image(new Texture(Resource.ACCEPT_OFF.address()));
                        accept.addListener(new ClickListener() {
                            @Override
                            public void clicked(InputEvent event, float x, float y) {
                                accept.setDrawable(new Image(new Texture(Resource.ACCEPT_CLICKED.address())).getDrawable());
                                perform(new Connector().perform(new Instruction(Command.FRIEND_REQUEST, name, currentUsername, "true")));
                            }

                            @Override
                            public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
                                accept.setDrawable(new Image(new Texture(Resource.ACCEPT_ON.address())).getDrawable());
                            }

                            @Override
                            public void exit(InputEvent event, float x, float y, int pointer, Actor toActor) {
                                accept.setDrawable(new Image(new Texture(Resource.ACCEPT_OFF.address())).getDrawable());
                            }
                        });
                        Image reject = new Image(new Texture(Resource.REJECT_OFF.address()));
                        reject.addListener(new ClickListener() {
                            @Override
                            public void clicked(InputEvent event, float x, float y) {
                                reject.setDrawable(new Image(new Texture(Resource.REJECT_CLICKED.address())).getDrawable());
                                perform(new Connector().perform(new Instruction(Command.FRIEND_REQUEST, name, currentUsername, "false")));
                            }

                            @Override
                            public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
                                reject.setDrawable(new Image(new Texture(Resource.REJECT_ON.address())).getDrawable());
                            }

                            @Override
                            public void exit(InputEvent event, float x, float y, int pointer, Actor toActor) {
                                reject.setDrawable(new Image(new Texture(Resource.REJECT_OFF.address())).getDrawable());
                            }
                        });
                        group.addActor(new Label(name, skin));
                        group.addActor(accept);
                        group.addActor(reject);
                        groupRequestIn.addActor(group);
                    }
                }
                break;
            case REQUEST_OUT_MESSAGE:
                if (!Objects.equals(empty, "empty")) {
                    groupRequestOut.clear();
                    for (String name : response)
                        groupRequestOut.addActor(new Label(name, skin));
                }
                break;
            case FRIEND_MESSAGE:
                if (empty != "empty") {
                    groupFriend.clear();
                    for (String name : response)
                        groupFriend.addActor(new Label(name, skin));
                }
                break;
            case SEND_REQUEST_MESSAGE:
                if (!Objects.equals(empty, "empty")) {
                    error.setText(arguments);
                    add.setText("");
                }
                break;
            case FRIEND_REQUEST_MESSAGE:
                perform(new Connector().perform(new Instruction(Command.REQUEST_IN, currentUsername)));
                break;
        }
    }
}
