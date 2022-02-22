package com.cruntchy.suprseed.Engine.AssetLoader;

import com.cruntchy.suprseed.Engine.ErrorLogger.CentralLogger;
import com.cruntchy.suprseed.Engine.ErrorLogger.ErrorType;
import com.cruntchy.suprseed.Engine.Images.Animator;
import com.cruntchy.suprseed.Engine.Images.ImageType;

import java.util.Map;

abstract public class AssetLoader {

    // Dependencies
    protected Streamable assetStreamer;
    protected FolderParser folderParser;

    // Hold image data
    protected Map<String, ImageType> images;
    protected Map<String, Animator> animations;


    // Constructor
    public AssetLoader(Streamable assetStreamer, FolderParser folderParser){

        this.assetStreamer = assetStreamer;
        this.folderParser = folderParser;
    }


    abstract public void loadAssets();


    public ImageType getImage(String imageId){

        try{

            return images.get(imageId);

        }catch(Exception e){

            e.printStackTrace();

            CentralLogger.logMessage(ErrorType.FATAL_ERROR, "Could not find the image specified!");

            throw new NullPointerException();
        }
    }


    public Animator getAnimation(String imageId){

        try{

            return animations.get(imageId);

        }catch(Exception e){

            e.printStackTrace();

            CentralLogger.logMessage(ErrorType.FATAL_ERROR, "Could not find the animation specified!");

            throw new NullPointerException();
        }

    }
}
