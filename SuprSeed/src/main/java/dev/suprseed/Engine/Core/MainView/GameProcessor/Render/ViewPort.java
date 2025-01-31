package dev.suprseed.Engine.Core.MainView.GameProcessor.Render;

public class ViewPort {

    private static final ViewPort INSTANCE = new ViewPort();
    private final float scaledWidth = 100;
    private float scaledHeight;

    public static ViewPort getInstance() {
        return INSTANCE;
    }

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
