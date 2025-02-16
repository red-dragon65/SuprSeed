package dev.suprseed.Engine.Core.SpriteObjects.SpriteBase;

import androidx.annotation.NonNull;

import java.util.List;

public interface AssetBundle {

    int getNumAssets();

    List<String> getAllTags();

    PlayerCreator getSpriteAssetByTag(String tag);

    PlayerCreator getSelectedAsset();

    boolean trySelectingAsset(String tag);

    void addAsset(@NonNull PlayerCreator spriteAsset);

}
