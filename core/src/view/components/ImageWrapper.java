package view.components;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.Image;


public class ImageWrapper extends Image {
    private final Image image;
    private final float width;
    private final float height;

    public ImageWrapper(String address, float width, float height) {
        image = new Image(new Texture(address));
        this.width = width;
        this.height = height;
        image.setSize(width, height);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        image.draw(batch, parentAlpha);
    }

    @Override
    public float getPrefWidth() {
        return width;
    }

    @Override
    public float getPrefHeight() {
        return height;
    }

    @Override
    public void setPosition(float x, float y) {
        image.setPosition(x, y);
    }

    @Override
    public float getX() {
        return image.getX();
    }

    @Override
    public float getY() {
        return image.getY();
    }

    @Override
    public String toString() {
        return image.toString();
    }
}
