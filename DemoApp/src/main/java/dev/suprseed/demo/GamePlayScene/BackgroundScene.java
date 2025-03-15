package dev.suprseed.demo.GamePlayScene;

import dev.suprseed.Engine.Core.Scenes.SceneHeirarchy.BaseScene;
import dev.suprseed.Engine.Core.SpriteObjects.SpriteBase.AssetBundle;
import dev.suprseed.Engine.Core.SpriteObjects.SpriteBase.Sprite;
import dev.suprseed.Engine.Lib.AssetLoader.Bundler;
import dev.suprseed.demo.Shared.BackgroundSprite;

public class BackgroundScene extends BaseScene {

    public BackgroundScene(String sceneId, Bundler<AssetBundle> assetBundler) {
        super(sceneId);

        // Load background sprites here
        Sprite background = new BackgroundSprite(assetBundler.generateAssetBundle("background"));
        registerSprite(background);
    }
}
