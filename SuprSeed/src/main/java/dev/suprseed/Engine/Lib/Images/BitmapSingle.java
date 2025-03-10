package dev.suprseed.Engine.Lib.Images;

import android.graphics.Bitmap;

import java.io.IOException;
import java.util.Optional;

import dev.suprseed.Engine.Core.SpriteObjects.SpriteBase.SpriteImage;
import dev.suprseed.Engine.EngineContext;
import dev.suprseed.Engine.Lib.AssetLoader.Streamable;

public class BitmapSingle implements SpriteImage {

    private final Bitmap image;
    private String tag;

    // Constructor
    public BitmapSingle(String filePath, float imageScale, Streamable imageStreamer, String tag) throws IOException {

        this.image = imageStreamer.loadImage(filePath, imageScale);
        this.tag = tag;
    }

    @Override
    public String getTag() {
        return tag;
    }

    @Override
    public Bitmap getImage() {
        return image;
    }

    @Override
    public Optional<Bitmap> getIndexedImage(int index) {
        return Optional.ofNullable(image);
    }

    @Override
    public int getNumImages() {
        return 1;
    }


    @Override
    public float getScaledWidth() {

        return EngineContext.getScreen().convertCanvasToViewPort(getImage().getWidth());
    }

    @Override
    public float getScaledHeight() {

        return EngineContext.getScreen().convertCanvasToViewPort(getImage().getHeight());
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
