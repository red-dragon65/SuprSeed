package com.cruntchy.suprseed.Engine.AssetLoader;

public interface ImageTransformer {

    void rotateSprite(float degrees);

    void flipSprite(boolean vertical);

    void invertColors();
}
