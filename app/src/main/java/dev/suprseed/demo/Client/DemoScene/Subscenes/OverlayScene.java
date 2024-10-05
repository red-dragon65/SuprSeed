package dev.suprseed.demo.Client.DemoScene.Subscenes;

import dev.suprseed.demo.Client.DemoScene.SharedData.BounceData;
import dev.suprseed.demo.Client.DemoScene.SharedData.GameOverData;
import dev.suprseed.demo.Client.DemoScene.Sprites.HudSprite.OverlaySprites.GameOver;
import dev.suprseed.demo.Client.DemoScene.Sprites.HudSprite.OverlaySprites.PauseButton;
import dev.suprseed.demo.Client.DemoScene.Sprites.HudSprite.OverlaySprites.PauseText;
import dev.suprseed.demo.Client.DemoScene.Sprites.HudSprite.OverlaySprites.ScoreSprite;
import dev.suprseed.demo.Engine.Core.Scenes.SceneHeirarchy.BaseScene;
import dev.suprseed.demo.Engine.Core.Scenes.SceneHeirarchy.SceneManager;
import dev.suprseed.demo.Engine.Core.SpriteObjects.SpriteBase.ImageHandler;
import dev.suprseed.demo.Engine.Core.SpriteObjects.SpriteBase.Sprite;
import dev.suprseed.demo.Engine.Lib.AssetLoader.AssetLoader;

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
