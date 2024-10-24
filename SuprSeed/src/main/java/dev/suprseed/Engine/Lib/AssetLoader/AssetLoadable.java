package dev.suprseed.Engine.Lib.AssetLoader;

import java.util.List;

import dev.suprseed.Engine.Core.Scenes.SceneHeirarchy.BaseScene;

public interface AssetLoadable<ASSET, SPRITE> {

    void loadAssets(BaseScene parentScene);

    ASSET getAssetBundle(String tag);

    ASSET getAssetBundle(List<String> tags);

    SPRITE getImage(String imageId);
}
