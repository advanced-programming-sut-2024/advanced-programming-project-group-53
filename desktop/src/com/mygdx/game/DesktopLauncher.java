package com.mygdx.game;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import game.GWENT;

public class DesktopLauncher {
    public static void main(String[] arg) {
        Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
        config.setForegroundFPS(60);
        config.useVsync(true);
        config.setTitle("GWENT");
        config.setWindowedMode(1024, 1024);
        config.setResizable(false);
        config.setWindowIcon("icon/gameIcon.png");
        new Lwjgl3Application(new GWENT(), config);
    }
}
