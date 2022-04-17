package com.cruntchy.suprseed.Client.Scene1.Subscenes;

import com.cruntchy.suprseed.Client.Scene1.Sprites.HudSprite.ScoreSprite;
import com.cruntchy.suprseed.Engine.Scenes.SceneHeirarchy.BaseScene;
import com.cruntchy.suprseed.Engine.Scenes.SceneHeirarchy.SceneManager;
import com.cruntchy.suprseed.Engine.SpriteObjects.SpriteBase.Sprite;

public class OverlayScene extends BaseScene {

    public OverlayScene(SceneManager parentScene, String sceneId) {
        super(parentScene, sceneId);

        // Load HUD scenes here
        Sprite scoreSprite = new ScoreSprite(this, context);

    }
}
