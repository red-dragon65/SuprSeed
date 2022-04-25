package com.cruntchy.suprseed.Engine.Lib.Images;

import android.graphics.Bitmap;

import com.cruntchy.suprseed.Engine.Core.MainView.GameProcessor.Render.CanvasData;
import com.cruntchy.suprseed.Engine.Lib.AssetLoader.Streamable;

public class ImageSingle implements SpriteImage {

    private final Bitmap image;

    // Constructor
    public ImageSingle(String filePath, float imageScale, Streamable imageStreamer) {

        this.image = imageStreamer.loadImage(filePath, imageScale);
    }

    @Override
    public Bitmap getImage() {
        return image;
    }

    @Override
    public Bitmap getIndexedImage(int index) {
        return image;
    }

    @Override
    public int getNumImages() {
        return 1;
    }


    @Override
    public float getScaledWidth() {

        return CanvasData.getInstance().formatCanvasToCoordinate(getImage().getWidth()) / scaler.getLocationScaleRatio();
    }

    @Override
    public float getScaledHeight() {

        return CanvasData.getInstance().formatCanvasToCoordinate(getImage().getHeight()) / scaler.getLocationScaleRatio();
    }

    @Override
    public float getScaledWidth(int index) {
        return getScaledWidth();
    }

    @Override
    public float getScaledHeight(int index) {
        return getScaledHeight();
    }
}
