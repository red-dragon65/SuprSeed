package com.cruntchy.suprseed.Client.Z_ClientTest_Active.GameCode.Scenes.GameScene;

import androidx.core.graphics.drawable.IconCompat;

import com.cruntchy.suprseed.Client.Z_ClientTest_Active.GameCode.Sprites.HudSprite.ScoreSprite;
import com.cruntchy.suprseed.Engine.Scenes.SceneHeirarchy.BaseScene;
import com.cruntchy.suprseed.Engine.Scenes.SceneHeirarchy.SceneManager;
import com.cruntchy.suprseed.Engine.SpriteObjects.SpriteBase.Sprite;

public class OverlayScene extends BaseScene {

    public OverlayScene(SceneManager parentScene, String sceneId) {
        super(parentScene, sceneId);

        // Load HUD scenes here
        Sprite scoreSprite = new ScoreSprite(this, parentScene.getGameInfo().getContext());

    }
}
