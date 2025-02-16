package dev.suprseed.demo.Subscenes;

import dev.suprseed.Engine.Core.Scenes.SceneHeirarchy.BaseScene;
import dev.suprseed.Engine.Core.Scenes.SceneHeirarchy.SceneManager;
import dev.suprseed.Engine.Core.SpriteObjects.SpriteBase.AssetBundle;
import dev.suprseed.Engine.Core.SpriteObjects.SpriteBase.Sprite;
import dev.suprseed.Engine.Lib.AssetLoader.Bundler;
import dev.suprseed.demo.SharedData.BounceData;
import dev.suprseed.demo.SharedData.GameOverData;
import dev.suprseed.demo.Sprites.HudSprite.OverlaySprites.GameOver;
import dev.suprseed.demo.Sprites.HudSprite.OverlaySprites.PauseButton;
import dev.suprseed.demo.Sprites.HudSprite.OverlaySprites.PauseText;
import dev.suprseed.demo.Sprites.HudSprite.OverlaySprites.ScoreSprite;

public class OverlayScene extends BaseScene {

    private final Sprite scoreSprite;

    public OverlayScene(SceneManager parentScene, String sceneId, Bundler<AssetBundle> assetBundler, BounceData bounceData, GameOverData gameOverData) {
        super(parentScene, sceneId);

        // Load HUD scenes here

        scoreSprite = new ScoreSprite(this, bounceData, gameOverData);

        // This runs independent of the pause button
        Sprite pauseText = new PauseText(this, assetBundler.generateAssetBundle("pause_text"));

        Sprite pauseButton = new PauseButton(this, assetBundler.generateAssetBundle("pause_button"));

        Sprite gameOverText = new GameOver(this, gameOverData);
    }

    @Override
    public void resetState() {

        scoreSprite.resetState();
    }
}
