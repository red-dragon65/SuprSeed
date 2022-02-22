package com.cruntchy.suprseed.Engine.Images;

import android.graphics.Bitmap;

public interface ImageType {

    // Get current image
    Bitmap getImage();

    // Get image at specified index
    Bitmap getIndexedImage(int index);

    // Get number of images
    int getNumImages();
}
