package dev.suprseed.Engine.Lib.AssetLoader;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import dev.suprseed.Engine.Core.Scenes.SceneHeirarchy.BaseScene;
import dev.suprseed.Engine.Core.SpriteObjects.SpriteBase.AssetBundle;
import dev.suprseed.Engine.Lib.Images.PlaceHolder;
import dev.suprseed.Engine.Lib.Images.SpriteImage;

abstract public class SafeAssetLoader implements AssetLoadable<AssetBundle, SpriteImage> {

    // Dependencies
    protected Streamable assetStreamer;
    protected FolderParser folderParser;
    // Hold image data
    protected List<SpriteImage> images = new ArrayList<>();
    private AssetLoadable<Optional<AssetBundle>, Optional<SpriteImage>> assetLoader;
    private SpriteImage placeHolder;

    // Constructor
    public SafeAssetLoader(BaseScene parentScene, Streamable assetStreamer, FolderParser folderParser, PlaceHolder placeHolder) {

        this.assetStreamer = assetStreamer;
        this.folderParser = folderParser;
        this.placeHolder = placeHolder;

        assetLoader = new BasicAssetLoader(parentScene, assetStreamer, folderParser) {
            @Override
            public void loadAssets(BaseScene parentScene) {
                assetRegistry = SafeAssetLoader.this.images;
                // Do nothing here
            }
        };

        assetLoader.loadAssets(parentScene);
        loadAssets(parentScene);
    }


    @Override
    abstract public void loadAssets(BaseScene parentScene);

    @Override
    public AssetBundle getAssetBundle(String tag) {

        Optional<AssetBundle> result = assetLoader.getAssetBundle(tag);

        return result.orElseGet(() -> new AssetBundle(placeHolder));
    }

    @Override
    public AssetBundle getAssetBundle(List<String> tags) {

        List<SpriteImage> assets = new ArrayList<>();

        // There will always be the placeholder here as default
        assets.add(placeHolder);

        // Add image if any tag actually exist
        for (String s : tags) {
            assetLoader.getImage(s).ifPresent(assets::add);
        }

        return new AssetBundle(assets);
    }

    @Override
    public SpriteImage getImage(String imageId) {

        Optional<SpriteImage> result = assetLoader.getImage(imageId);

        return result.orElseGet(() -> placeHolder);
    }
}
