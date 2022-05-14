package com.cruntchy.suprseed.Client.DemoScene.Subscenes;

import com.cruntchy.suprseed.Client.DemoScene.SharedData.BounceData;
import com.cruntchy.suprseed.Client.DemoScene.SharedData.GameOverData;
import com.cruntchy.suprseed.Client.DemoScene.Sprites.HudSprite.OverlaySprites.GameOver;
import com.cruntchy.suprseed.Client.DemoScene.Sprites.HudSprite.OverlaySprites.PauseButton;
import com.cruntchy.suprseed.Client.DemoScene.Sprites.HudSprite.OverlaySprites.PauseText;
import com.cruntchy.suprseed.Client.DemoScene.Sprites.HudSprite.OverlaySprites.ScoreSprite;
import com.cruntchy.suprseed.Engine.Core.Scenes.SceneHeirarchy.BaseScene;
import com.cruntchy.suprseed.Engine.Core.Scenes.SceneHeirarchy.SceneManager;
import com.cruntchy.suprseed.Engine.Core.SpriteObjects.SpriteBase.ImageHandler;
import com.cruntchy.suprseed.Engine.Core.SpriteObjects.SpriteBase.Sprite;
import com.cruntchy.suprseed.Engine.Lib.AssetLoader.AssetLoader;

public class OverlayScene extends BaseScene {

    private final Sprite scoreSprite;

    public OverlayScene(SceneManager parentScene, String sceneId, AssetLoader gamePlayAssets, BounceData bounceData, GameOverData gameOverData) {
        super(parentScene, sceneId);

        // Load HUD scenes here

        scoreSprite = new ScoreSprite(this, bounceData, gameOverData);

        // This runs independent of the pause button
        Sprite pauseText = new PauseText(this, new ImageHandler("pause_text", gamePlayAssets.getImage("pause_text")));

        Sprite pauseButton = new PauseButton(this, new ImageHandler("pause_button", gamePlayAssets.getImage("pause_button")));

        Sprite gameOverText = new GameOver(this, gameOverData);
    }

    @Override
    public void resetState() {

        scoreSprite.resetState();
    }
}
