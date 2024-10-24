package dev.suprseed.Engine.Lib.AssetLoader;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import dev.suprseed.Engine.Core.ErrorLogger.CentralLogger;
import dev.suprseed.Engine.Core.ErrorLogger.ErrorType;
import dev.suprseed.Engine.Core.Scenes.SceneHeirarchy.BaseScene;
import dev.suprseed.Engine.Core.SpriteObjects.SpriteBase.AssetBundle;
import dev.suprseed.Engine.Lib.Images.SpriteImage;

abstract public class BasicAssetLoader implements AssetLoadable<Optional<AssetBundle>, Optional<SpriteImage>> {

    // Dependencies
    protected Streamable assetStreamer;
    protected FolderParser folderParser;

    // Hold image data
    protected List<SpriteImage> images = new ArrayList<>();


    // Constructor
    public BasicAssetLoader(BaseScene parentScene, Streamable assetStreamer, FolderParser folderParser) {

        this.assetStreamer = assetStreamer;
        this.folderParser = folderParser;

        loadAssets(parentScene);
    }


    @Override
    abstract public void loadAssets(BaseScene parentScene);

    @Override
    public Optional<AssetBundle> getAssetBundle(String tag) {

        return getImage(tag).map(AssetBundle::new);
    }

    @Override
    public Optional<AssetBundle> getAssetBundle(List<String> tags) {

        List<SpriteImage> assets = new ArrayList<>();

        for (String s : tags) {
            getImage(s).ifPresent(assets::add);
        }

        if (!assets.isEmpty()) {
            return Optional.of(new AssetBundle(assets));
        } else {
            CentralLogger.getInstance().logMessage(ErrorType.ERROR, "Could not find one or more of the images specified in the AssetLoader! tags: " + tags);
        }

        return Optional.empty();
    }

    @Override
    public Optional<SpriteImage> getImage(String tag) {

        Optional<SpriteImage> result = images.stream().filter(i -> i.getTag().equals(tag)).findFirst();

        if (!result.isPresent()) {
            CentralLogger.getInstance().logMessage(ErrorType.ERROR, "Could not find the image specified in the AssetLoader! imageId: " + tag);
        }

        return result;
    }

}
