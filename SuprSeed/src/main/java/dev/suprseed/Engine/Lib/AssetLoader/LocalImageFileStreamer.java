package dev.suprseed.Engine.Lib.AssetLoader;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.IOException;
import java.io.InputStream;

import dev.suprseed.Engine.Core.ErrorLogger.CentralLogger;
import dev.suprseed.Engine.Core.ErrorLogger.ErrorType;
import dev.suprseed.Engine.Core.MainView.GameProcessor.Render.CanvasData;

public class LocalImageFileStreamer implements Streamable {

    private final Resources res;
    private InputStream reader = null;


    // Constructor
    public LocalImageFileStreamer(Resources res) {

        this.res = res;
    }


    @Override
    public Bitmap loadImage(String fileName, float scale) throws IOException {

        if (scale == 0) {
            return loadImage_Unscaled(fileName);
        } else if (scale == 1) {
            return loadImage_WithDefaultScale(fileName);
        } else {
            return loadImage_WithAdditionalScaling(fileName, scale);
        }
    }

    @Override
    public Bitmap loadImage_WithDefaultScale(String fileName) throws IOException {
        return loadImage_WithAdditionalScaling(fileName, 1f);
    }


    // Loads an image from the assets folder
    @Override
    public Bitmap loadImage_WithAdditionalScaling(String fileName, float scaleFactor) throws IOException {

        reader = res.getAssets().open(fileName);

        Bitmap temp = BitmapFactory.decodeStream(reader);

        reader.close();

        if (temp == null) {

            CentralLogger.getInstance().logMessage(ErrorType.ERROR, "Failed to load image file: " + fileName);
        }

        return Bitmap.createScaledBitmap(temp, ((int) ((float) temp.getWidth() * CanvasData.getInstance().getSpriteScaleRatio() * scaleFactor)), ((int) ((float) temp.getHeight() * CanvasData.getInstance().getSpriteScaleRatio() * scaleFactor)), false);
    }


    // Loads an image from the assets folder
    @Override
    public Bitmap loadImage_Unscaled(String fileName) throws IOException {

        reader = res.getAssets().open(fileName);

        Bitmap temp = BitmapFactory.decodeStream(reader);

        reader.close();

        if (temp == null) {

            CentralLogger.getInstance().logMessage(ErrorType.ERROR, "Failed to load image file: " + fileName);
        }

        return Bitmap.createScaledBitmap(temp, ((int) ((float) temp.getWidth())), ((int) ((float) temp.getHeight())), false);
    }

}
