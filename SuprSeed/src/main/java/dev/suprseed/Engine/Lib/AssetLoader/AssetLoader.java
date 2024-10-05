package dev.suprseed.Engine.Lib.AssetLoader;

import java.util.HashMap;
import java.util.Map;

import dev.suprseed.Engine.Core.ErrorLogger.CentralLogger;
import dev.suprseed.Engine.Core.ErrorLogger.ErrorType;
import dev.suprseed.Engine.Core.Scenes.SceneHeirarchy.BaseScene;
import dev.suprseed.Engine.Lib.Images.Animator;
import dev.suprseed.Engine.Lib.Images.SpriteImage;

abstract public class AssetLoader {

    // Dependencies
    protected Streamable assetStreamer;
    protected FolderParser folderParser;

    // Hold image data
    protected Map<String, SpriteImage> images = new HashMap<>();
    protected Map<String, Animator> animations = new HashMap<>();


    // Constructor
    public AssetLoader(BaseScene parentScene, Streamable assetStreamer, FolderParser folderParser){

        this.assetStreamer = assetStreamer;
        this.folderParser = folderParser;

        loadAssets(parentScene);
    }


    abstract public void loadAssets(BaseScene parentScene);


    public SpriteImage getImage(String imageId) {

        try {

            return images.get(imageId);

        } catch (Exception e) {

            e.printStackTrace();

            CentralLogger.getInstance().logMessage(ErrorType.FATAL_ERROR, "Could not find the image specified!");

            throw new NullPointerException();
        }
    }


    public Animator getAnimation(String imageId){

        try{

            return animations.get(imageId);

        }catch(Exception e){

            e.printStackTrace();

            CentralLogger.getInstance().logMessage(ErrorType.FATAL_ERROR, "Could not find the animation specified!");

            throw new NullPointerException();
        }

    }
}
