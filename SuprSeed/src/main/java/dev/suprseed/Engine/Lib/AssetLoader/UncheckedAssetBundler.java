package dev.suprseed.Engine.Lib.AssetLoader;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import dev.suprseed.Engine.Core.ErrorLogger.ErrorType;
import dev.suprseed.Engine.Core.SpriteObjects.SpriteBase.AssetBundle;
import dev.suprseed.Engine.Core.SpriteObjects.SpriteBase.PlayerCreator;
import dev.suprseed.Engine.Core.SpriteObjects.SpriteBase.SpriteImage;
import dev.suprseed.Engine.EngineContext;
import dev.suprseed.Engine.Lib.Images.SpriteAsset;
import dev.suprseed.Engine.Lib.Images.SpriteAssetBundle;

public class UncheckedAssetBundler implements Bundler<Optional<AssetBundle>> {

    private AssetLoadable<SpriteImage> assetLoader;
    private Map<String, PlayerCreator> sharedAssets;


    public UncheckedAssetBundler(AssetLoadable<SpriteImage> assetLoader) {
        this.assetLoader = assetLoader;
        this.sharedAssets = new HashMap<>();
    }

    @Override
    public Optional<AssetBundle> generateAssetBundle(String tag) {

        return generateAssetBundle(List.of(tag));
    }

    @Override
    public Optional<AssetBundle> generateAssetBundle(List<String> tags) {

        // TODO: Implement this warning log
        /*
        if(Engine.loop.hasStarted()){
            EngineContext.getLogger().logMessage(ErrorType.WARN, "Warning! Attempting to create an object within the game loop! Caller: createPlayer() for tag: " + tag);
        }
         */

        List<PlayerCreator> assets = assetLoader.getRegistry().stream() // get a stream of all sprite images
                .filter(image -> tags.contains(image.getTag())) // filter stream based on given tags
                .map(image -> new SpriteAsset(image)) // create sprite assets using filtered images
                .collect(Collectors.toList()); // collect all assets into a list

        // Try and create the asset bundle
        if (!assets.isEmpty()) {
            return Optional.of(new SpriteAssetBundle(assets));
        } else {
            EngineContext.getLogger().logMessage(ErrorType.ERROR, "Could not find one or more of the images specified in the AssetLoader! tags: " + tags);
        }

        return Optional.empty();
    }

    @Override
    public Optional<AssetBundle> generateSharedAssetBundle(String tag) {

        return generateSharedAssetBundle(List.of(tag));
    }

    /*
    Essentially returns assets that have the same shared player
    Note: the image will always be shared across all sprite assets
     */
    @Override
    public Optional<AssetBundle> generateSharedAssetBundle(List<String> tags) {

        // TODO: Implement this warning log
        /*
        if(Engine.loop.hasStarted()){
            EngineContext.getLogger().logMessage(ErrorType.WARN, "Warning! Attempting to create an object within the game loop! Caller: createPlayer() for tag: " + tag);
        }
         */

        List<PlayerCreator> assets = assetLoader.getRegistry().stream() // get a stream of all sprite images
                .filter(image -> tags.contains(image.getTag())) // filter stream based on given tags
                .map((image) -> { // create sprite assets using filtered images

                    // See if a shared sprite asset exists
                    for (Map.Entry<String, PlayerCreator> s : sharedAssets.entrySet()) {

                        // Return the shared sprite
                        if (tags.contains(s.getKey())) {

                            return s.getValue();
                        }
                    }

                    // Create a new shared sprite
                    PlayerCreator sharedSprite = new SpriteAsset(image);
                    sharedAssets.put(image.getTag(), sharedSprite);

                    // Return the new shared sprite
                    return sharedSprite;

                })
                .collect(Collectors.toList()); // collect all assets into a list

        // Try and create the asset bundle
        if (!assets.isEmpty()) {
            return Optional.of(new SpriteAssetBundle(assets));
        } else {
            EngineContext.getLogger().logMessage(ErrorType.ERROR, "Could not find one or more of the images specified in the AssetLoader! tags: " + tags);
        }

        return Optional.empty();
    }


}
