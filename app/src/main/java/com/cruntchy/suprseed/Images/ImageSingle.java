package com.cruntchy.suprseed.Images;

import android.graphics.Bitmap;

import com.cruntchy.suprseed.AssetLoader.Streamable;

public class ImageSingle implements  ImageType {

    private Bitmap image;

    // Constructor
    public ImageSingle(String filePath, float imageScale, Streamable imageStreamer){

        this.image = imageStreamer.loadImage(filePath, imageScale);
    }

    public void setImage(Bitmap newImage){

        image = newImage;
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
}
