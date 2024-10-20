package dev.suprseed.Engine.Lib.AssetLoader;

import java.util.ArrayList;
import java.util.List;

import dev.suprseed.Engine.Core.ErrorLogger.CentralLogger;
import dev.suprseed.Engine.Core.ErrorLogger.ErrorType;
import dev.suprseed.Engine.Core.Scenes.SceneHeirarchy.BaseScene;
import dev.suprseed.Engine.Core.SpriteObjects.SpriteBase.AssetBundle;
import dev.suprseed.Engine.Lib.Images.SpriteImage;

abstract public class AssetLoader {

    // Dependencies
    protected Streamable assetStreamer;
    protected FolderParser folderParser;

    // Hold image data
    protected List<SpriteImage> images = new ArrayList<>();


    // Constructor
    public AssetLoader(BaseScene parentScene, Streamable assetStreamer, FolderParser folderParser) {

        this.assetStreamer = assetStreamer;
        this.folderParser = folderParser;

        loadAssets(parentScene);
    }


    abstract public void loadAssets(BaseScene parentScene);

    public AssetBundle getAssetBundle(String tag) {

        return new AssetBundle(getImage(tag));
    }

    public AssetBundle getAssetBundle(List<String> tags) {

        List<SpriteImage> assets = new ArrayList<>();

        for (String s : tags) {
            assets.add(getImage(s));
        }

        return new AssetBundle(assets);
    }

    public SpriteImage getImage(String imageId) {

        try {

            return images.stream().filter(i -> i.getTag().equals(imageId)).findFirst().orElseThrow();

        } catch (Exception e) {

            e.printStackTrace();

            CentralLogger.getInstance().logMessage(ErrorType.FATAL_ERROR, "Could not find the image specified!");

            throw new NullPointerException();
        }
    }

}
