package dev.suprseed.Engine.Lib.AssetLoader;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import dev.suprseed.Engine.Core.ErrorLogger.ErrorType;
import dev.suprseed.Engine.Core.Scenes.SceneHeirarchy.BaseScene;
import dev.suprseed.Engine.Core.SpriteObjects.SpriteBase.SpriteImage;
import dev.suprseed.Engine.EngineContext;

abstract public class AssetLoader implements AssetLoadable<SpriteImage> {

    // Dependencies
    protected Streamable assetStreamer;
    protected FolderParser folderParser;

    // Hold image data
    protected List<SpriteImage> imageRegistry = new ArrayList<>();


    // Constructor
    public AssetLoader(BaseScene parentScene, Streamable assetStreamer, FolderParser folderParser) {

        this.assetStreamer = assetStreamer;
        this.folderParser = folderParser;

        loadAssets(parentScene);
    }


    @Override
    abstract public void loadAssets(BaseScene parentScene);


    @Override
    public List<SpriteImage> getRegistry() {
        return imageRegistry;
    }

    @Override
    public Optional<SpriteImage> getImageSet(String tag) {

        Optional<SpriteImage> result = imageRegistry.stream().filter(i -> i.getTag().equals(tag)).findFirst();

        if (!result.isPresent()) {
            EngineContext.getLogger().logMessage(ErrorType.ERROR, "Could not find the image specified in the AssetLoader! imageId: " + tag);
        }

        return result;
    }

}
