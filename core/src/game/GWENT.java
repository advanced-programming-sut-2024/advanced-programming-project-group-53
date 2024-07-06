package game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.utils.ScreenUtils;
import view.FirstView;
import model.view.Resource;

public class GWENT extends Game {
    private Music music;

    @Override
    public void create() {
        setScreen(new FirstView(this));
        music = Gdx.audio.newMusic(Gdx.files.internal(Resource.FIRST_MUSIC.address()));
        music.setLooping(true);
        music.play();
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
        music.stop();
        if (view instanceof view.graphic.LoginView)
            music = Gdx.audio.newMusic(Gdx.files.internal(Resource.LOGIN_MUSIC.address()));
        else if (view instanceof view.graphic.RegisterView)
            music = Gdx.audio.newMusic(Gdx.files.internal(Resource.REGISTER_MUSIC.address()));
        else if (view instanceof view.graphic.MainView)
            music = Gdx.audio.newMusic(Gdx.files.internal(Resource.MAIN_MUSIC.address()));
        else if (view instanceof view.graphic.ChatView)
            music = Gdx.audio.newMusic(Gdx.files.internal(Resource.CHAT_MUSIC.address()));
        else if (view instanceof view.graphic.ProfileView)
            music = Gdx.audio.newMusic(Gdx.files.internal(Resource.PROFILE_MUSIC.address()));
        else if (view instanceof view.graphic.FriendView)
            music = Gdx.audio.newMusic(Gdx.files.internal(Resource.FRIEND_MUSIC.address()));
        else if (view instanceof view.graphic.HistoryView)
            music = Gdx.audio.newMusic(Gdx.files.internal(Resource.HISTORY_MUSIC.address()));
        else if (view instanceof view.graphic.RankingView)
            music = Gdx.audio.newMusic(Gdx.files.internal(Resource.RANKING_MUSIC.address()));
        else if (view instanceof view.graphic.FactionView)
            music = Gdx.audio.newMusic(Gdx.files.internal(Resource.START_MUSIC.address()));
        else if (view instanceof view.graphic.PregameView)
            music = Gdx.audio.newMusic(Gdx.files.internal(Resource.PREGAME_MUSIC.address()));
        else if (view instanceof view.graphic.GameView)
            music = Gdx.audio.newMusic(Gdx.files.internal(Resource.GAME_MUSIC.address()));
        else if (view instanceof view.graphic.SelectView)
            music = Gdx.audio.newMusic(Gdx.files.internal(Resource.SELECT_MUSIC.address()));
        else if (view instanceof view.graphic.TournamentView)
            music = Gdx.audio.newMusic(Gdx.files.internal(Resource.TOURNAMENT_MUSIC.address()));
        music.setLooping(true);
        music.play();
    }

    public void changeScreen(view.single.View view) {
        this.screen.dispose();
        this.setScreen(view);
        music.pause();
        if (view instanceof view.single.LoginView)
            music = Gdx.audio.newMusic(Gdx.files.internal(Resource.LOGIN_MUSIC.address()));
        else if (view instanceof view.single.RegisterView)
            music = Gdx.audio.newMusic(Gdx.files.internal(Resource.REGISTER_MUSIC.address()));
        else if (view instanceof view.single.MainView)
            music = Gdx.audio.newMusic(Gdx.files.internal(Resource.MAIN_MUSIC.address()));
        else if (view instanceof view.single.ProfileView)
            music = Gdx.audio.newMusic(Gdx.files.internal(Resource.PROFILE_MUSIC.address()));
        else if (view instanceof view.single.HistoryView)
            music = Gdx.audio.newMusic(Gdx.files.internal(Resource.HISTORY_MUSIC.address()));
        else if (view instanceof view.single.RankingView)
            music = Gdx.audio.newMusic(Gdx.files.internal(Resource.RANKING_MUSIC.address()));
        else if (view instanceof view.single.FactionView)
            music = Gdx.audio.newMusic(Gdx.files.internal(Resource.START_MUSIC.address()));
        else if (view instanceof view.single.GameView)
            music = Gdx.audio.newMusic(Gdx.files.internal(Resource.GAME_MUSIC.address()));
        else if (view instanceof view.single.SelectView)
            music = Gdx.audio.newMusic(Gdx.files.internal(Resource.SELECT_MUSIC.address()));
        else if (view instanceof view.single.EndView)
            music = Gdx.audio.newMusic(Gdx.files.internal(Resource.END_MUSIC.address()));
        music.setLooping(true);
        music.play();
    }
}
