package dev.suprseed.demo.Subscenes.GamePlay;

import android.content.Context;

import dev.suprseed.Engine.Core.Scenes.SceneHeirarchy.BaseScene;
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

    public OverlayScene(Context context, String sceneId, Bundler<AssetBundle> assetBundler, BounceData bounceData, GameOverData gameOverData) {
        super(sceneId);

        // Load HUD scenes here

        scoreSprite = new ScoreSprite(context, bounceData, gameOverData);
        registerSprite(scoreSprite);

        // This runs independent of the pause button
        registerSprite(new PauseText(assetBundler.generateAssetBundle("pause_text")));

        registerSprite(new PauseButton(assetBundler.generateAssetBundle("pause_button")));

        registerSprite(new GameOver(context, gameOverData));
    }

    @Override
    public void resetState() {

        scoreSprite.resetState();
    }
}
