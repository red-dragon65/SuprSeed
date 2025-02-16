package dev.suprseed.Engine.Lib.Images;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import dev.suprseed.Engine.Core.ErrorLogger.ErrorType;
import dev.suprseed.Engine.Core.SpriteObjects.SpriteBase.AssetBundle;
import dev.suprseed.Engine.Core.SpriteObjects.SpriteBase.PlayerCreator;
import dev.suprseed.Engine.EngineContext;

public class SpriteAssetBundle implements AssetBundle {

    private List<PlayerCreator> assetSet;
    private String selectedImage;

    // Constructor that takes one sprite image set
    public SpriteAssetBundle(@NonNull PlayerCreator spriteAsset) {

        assetSet = new ArrayList<>(1);
        assetSet.add(spriteAsset);

        selectedImage = spriteAsset.getImageSet().getTag();
    }

    // Constructor that takes multiple sprite image sets
    public SpriteAssetBundle(@NonNull List<PlayerCreator> spriteAssets) {

        assetSet = spriteAssets;

        selectedImage = spriteAssets.get(0).getImageSet().getTag();
    }


    @Override
    public int getNumAssets() {
        return assetSet.size();
    }

    @Override
    public List<String> getAllTags() {

        return assetSet.stream().map(s -> s.getImageSet().getTag()).collect(Collectors.toList());
    }

    @Override
    public PlayerCreator getSpriteAssetByTag(String tag) {

        Optional<PlayerCreator> result = assetSet.stream().filter(s -> s.getImageSet().getTag().equals(tag)).findFirst();

        if (result.isPresent()) {
            return result.get();
        }

        EngineContext.getLogger().logMessage(ErrorType.ERROR, "Image with key: '" + tag + "' does not exist in the AssetBundle!");

        /*
        There is no reason for this to ever throw for the end user
        This can only throw if the PlaceHolder has been messed with in other parts of the code base
         */
        return assetSet.stream().filter(s -> s.getImageSet().getTag().equals(PlaceHolder.TAG.PLACEHOLDER.toString())).findFirst().orElseThrow();
    }

    @Override
    public PlayerCreator getSelectedAsset() {

        /*
        This should always work for the end user
        The constructors will throw an exception if the spriteImage tag can not be set
        The setSelectedImageSet() method will be thrown if the provided string is invalid
         */
        return assetSet.stream().filter(s -> s.getImageSet().getTag().equals(selectedImage)).findFirst().orElseThrow();
    }

    @Override
    public boolean trySelectingAsset(String tag) {

        // Verify id exists
        if (assetSet.stream().anyMatch(s -> s.getImageSet().getTag().equals(tag))) {
            selectedImage = tag;
            return true;
        }

        selectedImage = PlaceHolder.TAG.PLACEHOLDER.toString();

        String message = "Image with key: '" + tag + "' does not exist in the AssetBundle!";
        EngineContext.getLogger().logMessage(ErrorType.ERROR, message);

        return false;
    }

    @Override
    public void addAsset(@NonNull PlayerCreator spriteAsset) {

        assetSet.add(spriteAsset);
    }
}
