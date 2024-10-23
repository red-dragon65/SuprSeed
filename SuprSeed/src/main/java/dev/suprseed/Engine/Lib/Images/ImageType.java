package dev.suprseed.Engine.Lib.Images;

import android.graphics.Bitmap;

import java.util.Optional;

public interface ImageType {

    // Get current image
    Bitmap getImage();

    // Get image at specified index
    Optional<Bitmap> getIndexedImage(int index);

    // Get number of images
    int getNumImages();
}
