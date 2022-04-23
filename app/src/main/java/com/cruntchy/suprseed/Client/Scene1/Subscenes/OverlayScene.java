package com.cruntchy.suprseed.Client.Scene1.Subscenes;

import com.cruntchy.suprseed.Client.Scene1.Data.BounceData;
import com.cruntchy.suprseed.Client.Scene1.Data.GameOverData;
import com.cruntchy.suprseed.Client.Scene1.Sprites.HudSprite.GameOver;
import com.cruntchy.suprseed.Client.Scene1.Sprites.HudSprite.PauseButton;
import com.cruntchy.suprseed.Client.Scene1.Sprites.HudSprite.PauseText;
import com.cruntchy.suprseed.Client.Scene1.Sprites.HudSprite.ScoreSprite;
import com.cruntchy.suprseed.Engine.AssetLoader.AssetLoader;
import com.cruntchy.suprseed.Engine.Scenes.SceneHeirarchy.BaseScene;
import com.cruntchy.suprseed.Engine.Scenes.SceneHeirarchy.SceneManager;
import com.cruntchy.suprseed.Engine.SpriteObjects.SpriteBase.ImageHandler;
import com.cruntchy.suprseed.Engine.SpriteObjects.SpriteBase.Sprite;

public class OverlayScene extends BaseScene {

    public OverlayScene(SceneManager parentScene, String sceneId, AssetLoader gamePlayAssets, BounceData bounceData, GameOverData gameOverData) {
        super(parentScene, sceneId);

        // Load HUD scenes here

        Sprite scoreSprite = new ScoreSprite(this, bounceData);

        // This runs independent of the pause button
        Sprite pauseText = new PauseText(this, new ImageHandler("pause_text", gamePlayAssets.getImage("pause_text")));

        Sprite pauseButton = new PauseButton(this, new ImageHandler("pause_button", gamePlayAssets.getImage("pause_button")));

        Sprite gameOverText = new GameOver(this, gameOverData);
    }
}
