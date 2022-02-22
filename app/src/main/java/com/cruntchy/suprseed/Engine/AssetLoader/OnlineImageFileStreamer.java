package com.cruntchy.suprseed.Engine.AssetLoader;

import android.graphics.Bitmap;


/**
 * Note: This is just a place holder example for online asset streaming
 */
public class OnlineImageFileStreamer implements Streamable {



    // Constructor
    public OnlineImageFileStreamer(){

    }




    @Override
    public Bitmap loadImage(String filePath, float scaleFactor) {
        return null;
    }

    @Override
    public Bitmap loadImage_WithDefaultScale(String filePath) {

        /*
        Open connection

        Socket connection = Socket.open(filePath);

        BitStream data = connection.read();

        Bitmap image = BitmapFactory.decodeStream(data);

        Bitmap.createScaledBitmap(......);

        return image;

         */

        return null;
    }

    @Override
    public Bitmap loadImage_WithAdditionalScaling(String filePath, float scaleFactor) {
        return null;
    }

    @Override
    public Bitmap loadImage_Unscaled(String filePath) {
        return null;
    }
}
