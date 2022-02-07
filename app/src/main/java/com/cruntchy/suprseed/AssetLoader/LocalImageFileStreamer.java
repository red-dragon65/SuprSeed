package com.cruntchy.suprseed.AssetLoader;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import java.io.IOException;
import java.io.InputStream;

public class LocalImageFileStreamer implements Streamable {

    private Resources res;
    private InputStream reader = null;

    private ImageTransformer imageProcessor;


    // Constructor
    public LocalImageFileStreamer(Resources res, ImageTransformer imageProcessor){

        this.res = res;

        this.imageProcessor = imageProcessor;
    }



    @Override
    public Bitmap loadImage(String fileName, float scale){

        if(scale == 0){
            return loadImage_Unscaled(fileName);
        }else if(scale == 1){
            return loadImage_WithDefaultScale(fileName);
        }else{
            return loadImage_WithAdditionalScaling(fileName, scale);
        }
    }

    @Override
    public Bitmap loadImage_WithDefaultScale(String fileName){
        return loadImage_WithAdditionalScaling(fileName, 1f);
    }


    // Loads an image from the assets folder
    @Override
    public Bitmap loadImage_WithAdditionalScaling(String fileName, float scaleFactor){

        try {
            reader = res.getAssets().open(fileName);

            Bitmap temp = BitmapFactory.decodeStream(reader);

            if(temp == null){

                Log.d("ImageLoader", "Image failed to load: " + fileName);
            }

            return Bitmap.createScaledBitmap(temp, ((int) ((float) temp.getWidth() * imageProcessor.getSpriteImageScaleRatio() * scaleFactor)), ((int) ((float) temp.getHeight() * imageProcessor.getSpriteImageScaleRatio() * scaleFactor)), false);

        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }


    // Loads an image from the assets folder
    @Override
    public Bitmap loadImage_Unscaled(String fileName){

        InputStream reader = null;

        try {
            reader = res.getAssets().open(fileName);

            Bitmap temp = BitmapFactory.decodeStream(reader);

            reader.close();

            if(temp == null){

                Log.d("ImageLoader", "Image failed to load: " + fileName);
            }

            return Bitmap.createScaledBitmap(temp, ((int) ((float) temp.getWidth())), ((int) ((float) temp.getHeight())), false);

        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }





}
