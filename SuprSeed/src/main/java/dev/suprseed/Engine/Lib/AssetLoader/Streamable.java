package dev.suprseed.Engine.Lib.AssetLoader;

import android.graphics.Bitmap;

import java.io.IOException;

public interface Streamable {

    Bitmap loadImage(String filePath, float scaleFactor) throws IOException;

    Bitmap loadImage_WithDefaultScale(String filePath) throws IOException;

    Bitmap loadImage_WithAdditionalScaling(String filePath, float scaleFactor) throws IOException;

    Bitmap loadImage_Unscaled(String filePath) throws IOException;
}
