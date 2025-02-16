package dev.suprseed.Engine.Lib.AssetLoader;

import java.util.List;

public interface Bundler<ASSET> {

    ASSET generateAssetBundle(String tag);

    ASSET generateAssetBundle(List<String> tags);

    ASSET generateSharedAssetBundle(String tag);

    ASSET generateSharedAssetBundle(List<String> tags);
}
