package dev.suprseed.demo.Subscenes.GamePlay;

import dev.suprseed.Engine.Core.Scenes.SceneHeirarchy.BaseScene;
import dev.suprseed.Engine.Core.SpriteObjects.SpriteBase.AssetBundle;
import dev.suprseed.Engine.Core.SpriteObjects.SpriteBase.Sprite;
import dev.suprseed.Engine.Lib.AssetLoader.Bundler;
import dev.suprseed.demo.Sprites.BackgroundSprite.Background;

public class BackgroundScene extends BaseScene {

    public BackgroundScene(String sceneId, Bundler<AssetBundle> assetBundler) {
        super(sceneId);

        // Load background sprites here
        Sprite background = new Background(assetBundler.generateAssetBundle("background"));
        registerSprite(background);
        background.setAllowCollisionDiagnostic(false);
    }
}
