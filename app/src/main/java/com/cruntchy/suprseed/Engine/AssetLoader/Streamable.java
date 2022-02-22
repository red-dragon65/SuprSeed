package com.cruntchy.suprseed.Engine.AssetLoader;

import android.graphics.Bitmap;

public interface Streamable {

    public Bitmap loadImage(String filePath, float scaleFactor);

    public Bitmap loadImage_WithDefaultScale(String filePath);

    public Bitmap loadImage_WithAdditionalScaling(String filePath, float scaleFactor);

    public Bitmap loadImage_Unscaled(String filePath);
}
