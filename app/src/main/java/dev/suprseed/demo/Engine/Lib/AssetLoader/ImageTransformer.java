package dev.suprseed.demo.Engine.Lib.AssetLoader;

public interface ImageTransformer {

    void rotateSprite(float degrees);

    void flipSprite(boolean vertical);

    void invertColors();
}
