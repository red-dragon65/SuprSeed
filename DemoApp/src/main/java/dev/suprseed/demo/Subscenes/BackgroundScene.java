package dev.suprseed.demo.Subscenes;

import dev.suprseed.Engine.Core.Scenes.SceneHeirarchy.BaseScene;
import dev.suprseed.Engine.Core.Scenes.SceneHeirarchy.SceneManager;
import dev.suprseed.Engine.Core.SpriteObjects.SpriteBase.Sprite;
import dev.suprseed.Engine.Lib.AssetLoader.AssetLoader;
import dev.suprseed.demo.Sprites.BackgroundSprite.Background;

public class BackgroundScene extends BaseScene {

    public BackgroundScene(SceneManager parentScene, String sceneId, AssetLoader gamePlayAssets) {
        super(parentScene, sceneId);

        // Load background sprites here
        Sprite background = new Background(this, gamePlayAssets.getAssetBundle("background"));
        background.setAllowCollisionDiagnostic(false);
    }
}
