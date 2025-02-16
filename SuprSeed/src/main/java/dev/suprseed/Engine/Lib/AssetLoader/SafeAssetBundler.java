package dev.suprseed.Engine.Lib.AssetLoader;

import java.util.List;
import java.util.Optional;

import dev.suprseed.Engine.Core.SpriteObjects.SpriteBase.AssetBundle;
import dev.suprseed.Engine.Core.SpriteObjects.SpriteBase.PlayerCreator;
import dev.suprseed.Engine.Core.SpriteObjects.SpriteBase.SpriteImage;
import dev.suprseed.Engine.Lib.Images.SpriteAsset;
import dev.suprseed.Engine.Lib.Images.SpriteAssetBundle;

public class SafeAssetBundler implements Bundler<AssetBundle> {

    private Bundler<Optional<AssetBundle>> uncheckedAssetBundler;
    private PlayerCreator placeHolder;

    public SafeAssetBundler(AssetLoadable<SpriteImage> assetLoader, SpriteImage placeholder) {
        this.placeHolder = new SpriteAsset(placeholder);
        uncheckedAssetBundler = new UncheckedAssetBundler(assetLoader);
    }

    @Override
    public AssetBundle generateAssetBundle(String tag) {

        Optional<AssetBundle> result = uncheckedAssetBundler.generateAssetBundle(tag);

        // Return an asset with a placeholder sprite if necessary
        return result.orElseGet(() -> new SpriteAssetBundle(placeHolder));
    }

    @Override
    public AssetBundle generateAssetBundle(List<String> tags) {

        Optional<AssetBundle> result = uncheckedAssetBundler.generateAssetBundle(tags);

        // Return an asset with a placeholder sprite if necessary
        return result.orElseGet(() -> new SpriteAssetBundle(placeHolder));
    }

    @Override
    public AssetBundle generateSharedAssetBundle(String tag) {

        Optional<AssetBundle> result = uncheckedAssetBundler.generateSharedAssetBundle(tag);

        // Return an asset with a placeholder sprite if necessary
        return result.orElseGet(() -> new SpriteAssetBundle(placeHolder));
    }

    @Override
    public AssetBundle generateSharedAssetBundle(List<String> tags) {

        Optional<AssetBundle> result = uncheckedAssetBundler.generateSharedAssetBundle(tags);

        // Return an asset with a placeholder sprite if necessary
        return result.orElseGet(() -> new SpriteAssetBundle(placeHolder));
    }
}
