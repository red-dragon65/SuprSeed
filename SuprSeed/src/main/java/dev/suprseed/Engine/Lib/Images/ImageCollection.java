package dev.suprseed.Engine.Lib.Images;

import android.graphics.Bitmap;

import java.util.ArrayList;

import dev.suprseed.Engine.Core.ErrorLogger.CentralLogger;
import dev.suprseed.Engine.Core.ErrorLogger.ErrorType;
import dev.suprseed.Engine.Core.MainView.GameProcessor.Render.CanvasData;
import dev.suprseed.Engine.Lib.AssetLoader.FolderParser;
import dev.suprseed.Engine.Lib.AssetLoader.Streamable;


public class ImageCollection implements SpriteImage {

    protected ArrayList<Bitmap> imageSet;

    // Constructor for collection of images
    public ImageCollection(String folderPath, float imageScale, Streamable imageStreamer, FolderParser folderParser) {

        // Initialize array list
        imageSet = new ArrayList<>();

        // Get each sub file from base path
        String[] subPaths = folderParser.getSubPaths(folderPath);

        // Read in each image
        for (String singleImagePath : subPaths) {

            imageSet.add(imageStreamer.loadImage(singleImagePath, imageScale));
        }

        if (imageSet.size() == 0) {
            CentralLogger.getInstance().logMessage(ErrorType.FATAL_ERROR, "No images found in folder! Folder: " + folderPath);
        }
    }

    @Override
    public Bitmap getImage() {

        if (imageSet != null) {
            return imageSet.get(0);
        }

        throw new NullPointerException("This 'ImageCollection' has no images in it!");
    }

    // Return the specified image
    @Override
    public Bitmap getIndexedImage(int index) {

        // Prevent overflow
        if (index > imageSet.size() || index < 0 || imageSet.size() == 0) {

            return null;
        }

        return imageSet.get(index);
    }

    @Override
    public int getNumImages() {
        return imageSet.size();
    }


    @Override
    public float getScaledWidth() {

        return CanvasData.getInstance().formatCanvasToCoordinate(getImage().getWidth()) / scaler.getLocationScaleRatio();
    }

    @Override
    public float getScaledHeight() {

        return CanvasData.getInstance().formatCanvasToCoordinate(getImage().getHeight()) / scaler.getLocationScaleRatio();
    }

    @Override
    public float getScaledWidth(int index) {
        return CanvasData.getInstance().formatCanvasToCoordinate(getIndexedImage(index).getWidth()) / scaler.getLocationScaleRatio();
    }

    @Override
    public float getScaledHeight(int index) {

        return CanvasData.getInstance().formatCanvasToCoordinate(getIndexedImage(index).getHeight()) / scaler.getLocationScaleRatio();
    }
}
