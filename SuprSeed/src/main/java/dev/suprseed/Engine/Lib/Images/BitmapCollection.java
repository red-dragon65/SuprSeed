package dev.suprseed.Engine.Lib.Images;

import android.graphics.Bitmap;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import dev.suprseed.Engine.Core.ErrorLogger.ErrorType;
import dev.suprseed.Engine.Core.SpriteObjects.SpriteBase.SpriteImage;
import dev.suprseed.Engine.EngineContext;
import dev.suprseed.Engine.Lib.AssetLoader.FolderParser;
import dev.suprseed.Engine.Lib.AssetLoader.Streamable;


public class BitmapCollection implements SpriteImage {

    protected List<Bitmap> imageSet;
    private String tag;

    // Constructor for collection of images
    public BitmapCollection(String folderPath, float imageScale, Streamable imageStreamer, FolderParser folderParser, String tag) throws IOException {

        // Get each sub file from base path
        String[] subPaths = folderParser.getSubPaths(folderPath);

        if (subPaths.length == 0) {

            String message = "No images where found in the specified folder! Folder: " + folderPath;
            EngineContext.getLogger().logMessage(ErrorType.ERROR, message);
            throw new IOException(message);
        }

        // Initialize array list
        imageSet = new ArrayList<>(subPaths.length);

        // Read in each image
        for (String singleImagePath : subPaths) {

            imageSet.add(imageStreamer.loadImage(singleImagePath, imageScale));
        }

        this.tag = tag;
    }

    @Override
    public String getTag() {
        return tag;
    }

    @Override
    public Bitmap getImage() {

        return imageSet.get(0);
    }

    // Return the specified image
    @Override
    public Optional<Bitmap> getIndexedImage(int index) {

        // Prevent overflow
        if (index > imageSet.size()) {

            String tagInfo = "{tag == " + tag + "}";
            String message = "The index is out of bounds (upper) for ImageCollection tag " + tagInfo + "!";
            message += "\nIndex: " + index + " ImageSet.size(): " + imageSet.size();
            EngineContext.getLogger().logMessage(ErrorType.ERROR, message);

            return Optional.empty();
        } else if (index < 0) {

            String tagInfo = "{tag == " + tag + "}";
            String message = "The index is out of bounds (lower) for ImageCollection tag " + tagInfo + "!";
            message += "\nIndex: " + index + " Minimum allowed: " + "0";
            EngineContext.getLogger().logMessage(ErrorType.ERROR, message);

            return Optional.empty();
        } else if (imageSet.isEmpty()) {

            String tagInfo = "{tag == " + tag + "}";
            String message = "The ImageCollection for tag " + tagInfo + " has no images in it!";
            EngineContext.getLogger().logMessage(ErrorType.ERROR, message);

            return Optional.empty();
        }

        return Optional.ofNullable(imageSet.get(index));
    }

    @Override
    public int getNumImages() {
        return imageSet.size();
    }


    @Override
    public float getScaledWidth() {

        return EngineContext.getScreen().convertCanvasToViewPort(getImage().getWidth());
    }

    @Override
    public float getScaledHeight() {

        return EngineContext.getScreen().convertCanvasToViewPort(getImage().getHeight());
    }

    @Override
    public float getScaledWidth(int index) {

        if (getIndexedImage(index).isPresent()) {
            return EngineContext.getScreen().convertCanvasToViewPort(getIndexedImage(index).get().getWidth());
        }

        return 0;
    }

    @Override
    public float getScaledHeight(int index) {

        if (getIndexedImage(index).isPresent()) {
            return EngineContext.getScreen().convertCanvasToViewPort(getIndexedImage(index).get().getHeight());
        }

        return 0;
    }
}
