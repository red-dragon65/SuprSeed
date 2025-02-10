package dev.suprseed.Engine.Core.MainView.GameProcessor.Render;

public class ViewPort {

    private final float scaledWidth = 100;
    private float scaledHeight;

    public void initScaledHeight(float scaledHeight) {
        this.scaledHeight = scaledHeight;
    }

    public float getWidth() {
        return scaledWidth;
    }

    public float getHeight() {
        return scaledHeight;
    }
}
