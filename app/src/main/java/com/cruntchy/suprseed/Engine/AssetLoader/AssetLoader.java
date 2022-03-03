package com.cruntchy.suprseed.Engine.AssetLoader;

import com.cruntchy.suprseed.Engine.ErrorLogger.CentralLogger;
import com.cruntchy.suprseed.Engine.ErrorLogger.ErrorType;
import com.cruntchy.suprseed.Engine.Images.Animator;
import com.cruntchy.suprseed.Engine.Images.SpriteImage;

import java.util.HashMap;
import java.util.Map;

abstract public class AssetLoader {

    // Dependencies
    protected Streamable assetStreamer;
    protected FolderParser folderParser;

    // Hold image data
    protected Map<String, SpriteImage> images = new HashMap<>();
    protected Map<String, Animator> animations = new HashMap<>();


    // Constructor
    public AssetLoader(Streamable assetStreamer, FolderParser folderParser){

        this.assetStreamer = assetStreamer;
        this.folderParser = folderParser;

        loadAssets();
    }


    abstract public void loadAssets();


    public SpriteImage getImage(String imageId) {

        try {

            return images.get(imageId);

        } catch (Exception e) {

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
