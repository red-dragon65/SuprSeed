package dev.suprseed.demo.Client.DemoScene.Subscenes;

import dev.suprseed.demo.Client.DemoScene.Sprites.BackgroundSprite.Background;
import dev.suprseed.demo.Engine.Core.Scenes.SceneHeirarchy.BaseScene;
import dev.suprseed.demo.Engine.Core.Scenes.SceneHeirarchy.SceneManager;
import dev.suprseed.demo.Engine.Core.SpriteObjects.SpriteBase.ImageHandler;
import dev.suprseed.demo.Engine.Core.SpriteObjects.SpriteBase.Sprite;
import dev.suprseed.demo.Engine.Lib.AssetLoader.AssetLoader;

public class BackgroundScene extends BaseScene {

    public BackgroundScene(SceneManager parentScene, String sceneId, AssetLoader gamePlayAssets) {
        super(parentScene, sceneId);

        // Load background sprites here
        Sprite background = new Background(this, new ImageHandler("background", gamePlayAssets.getImage("background")));
    }
}
