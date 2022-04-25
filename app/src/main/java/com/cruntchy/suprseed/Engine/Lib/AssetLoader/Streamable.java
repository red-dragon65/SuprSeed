package com.cruntchy.suprseed.Engine.Lib.AssetLoader;

import android.graphics.Bitmap;

public interface Streamable {

    Bitmap loadImage(String filePath, float scaleFactor);

    Bitmap loadImage_WithDefaultScale(String filePath);

    Bitmap loadImage_WithAdditionalScaling(String filePath, float scaleFactor);

    Bitmap loadImage_Unscaled(String filePath);
}
