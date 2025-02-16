package dev.suprseed.Engine.Lib.AssetLoader;

import java.util.List;
import java.util.Optional;

import dev.suprseed.Engine.Core.Scenes.SceneHeirarchy.BaseScene;

public interface AssetLoadable<SPRITE> {

    void loadAssets(BaseScene parentScene);

    // Return the entire registry
    List<SPRITE> getRegistry();

    // Return an item from the registry based on the tag
    Optional<SPRITE> getImageSet(String tag);
}
