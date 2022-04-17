package com.cruntchy.suprseed.Client.Scene1.Subscenes;

import com.cruntchy.suprseed.Client.Scene1.Sprites.BackgroundSprite.Background;
import com.cruntchy.suprseed.Engine.AssetLoader.AssetLoader;
import com.cruntchy.suprseed.Engine.Scenes.SceneHeirarchy.BaseScene;
import com.cruntchy.suprseed.Engine.Scenes.SceneHeirarchy.SceneManager;
import com.cruntchy.suprseed.Engine.SpriteObjects.SpriteBase.ImageHandler;
import com.cruntchy.suprseed.Engine.SpriteObjects.SpriteBase.Sprite;

public class BackgroundScene extends BaseScene {

    public BackgroundScene(SceneManager parentScene, String sceneId, AssetLoader gamePlayAssets) {
        super(parentScene, sceneId);

        // Load background sprites here
        Sprite background = new Background(this, new ImageHandler("background", gamePlayAssets.getImage("background")));

    }
}
