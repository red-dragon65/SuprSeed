package dev.suprseed.demo.GamePlayScene.HudScene;

import android.content.Context;

import dev.suprseed.Engine.Core.Scenes.SceneHeirarchy.BaseScene;
import dev.suprseed.Engine.Core.SpriteObjects.SpriteBase.AssetBundle;
import dev.suprseed.Engine.Core.SpriteObjects.SpriteBase.Sprite;
import dev.suprseed.Engine.EngineTools;
import dev.suprseed.Engine.Lib.AssetLoader.Bundler;
import dev.suprseed.Engine.Lib.Input.InputListener;
import dev.suprseed.demo.GamePlayScene.HudScene.HudInput.FullScreenPauseInput;
import dev.suprseed.demo.GamePlayScene.HudScene.HudInput.FullScreenRestartInput;
import dev.suprseed.demo.GamePlayScene.HudScene.OverlaySprites.GameOver;
import dev.suprseed.demo.GamePlayScene.HudScene.OverlaySprites.PauseButton;
import dev.suprseed.demo.GamePlayScene.HudScene.OverlaySprites.PauseText;
import dev.suprseed.demo.GamePlayScene.HudScene.OverlaySprites.ScoreSprite;
import dev.suprseed.demo.GamePlayScene.SharedData.BounceData;
import dev.suprseed.demo.GamePlayScene.SharedData.GameOverData;

public class OverlayScene extends BaseScene {

    private final Sprite scoreSprite;
    private PauseButton pauseButton;
    private InputListener restartListener;
    private InputListener pauseListener;

    public OverlayScene(Context context, String sceneId, Bundler<AssetBundle> assetBundler, BounceData bounceData, GameOverData gameOverData) {
        super(sceneId);

        // Load HUD scenes here

        scoreSprite = new ScoreSprite(context, bounceData, gameOverData);
        registerSprite(scoreSprite);

        // This runs independent of the pause button
        registerSprite(new PauseText(assetBundler.generateAssetBundle("pause_text")));

        pauseButton = new PauseButton(assetBundler.generateAssetBundle("pause_button"));
        registerSprite(pauseButton);

        Sprite gameOver = new GameOver(context, gameOverData);
        registerSprite(gameOver);

        restartListener = new FullScreenRestartInput(gameOver, gameOverData);
        pauseListener = new FullScreenPauseInput();
    }

    @Override
    public void onPost() {

        EngineTools.getInputManager().getListenerRegistry().registerObject(restartListener);

        EngineTools.getInputManager().getListenerRegistry().registerObject(pauseListener);
        EngineTools.getInputManager().getListenerRegistry().registerObject(pauseButton);
    }

    @Override
    public void resetState() {

        scoreSprite.resetState();
    }

    @Override
    public void onDestroy() {

        EngineTools.getInputManager().getListenerRegistry().removeObject(restartListener);

        EngineTools.getInputManager().getListenerRegistry().removeObject(pauseListener);
        EngineTools.getInputManager().getListenerRegistry().removeObject(pauseButton);
    }
}
