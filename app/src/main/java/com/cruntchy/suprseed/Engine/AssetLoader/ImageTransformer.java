package com.cruntchy.suprseed.Engine.AssetLoader;

public interface ImageTransformer {


    public void init(int width, int height, float targetResolutionWidth);

    public void rotateSprite(float degrees);

    public void flipSprite(boolean vertical);

    public void invertColors();

    public float getSpriteImageScaleRatio();
}
