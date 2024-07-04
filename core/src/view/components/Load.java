package view.components;

import com.badlogic.gdx.scenes.scene2d.ui.Image;

import java.util.ArrayList;

public class Load extends Image {
    private final ArrayList<Image> frames;
    private final double interval;
    private int currentFrame;
    private double time;
    private final Image currentImage;

    public Load(ArrayList<Image> frames, double interval) {
        this.frames = frames;
        this.interval = interval;
        this.time = 0;
        this.currentFrame = 1;
        this.currentImage = frames.get(0);
    }

    public void passTime(double delta) {
        time += delta;
        if (Math.abs(time % interval) <= 0.1f) {
            change();
            time = 0;
        }
    }

    private void change() {
        if (currentFrame == frames.size())
            currentFrame = 1;
        else
            currentFrame++;
        currentImage.setDrawable(frames.get(currentFrame - 1).getDrawable());
    }
}
