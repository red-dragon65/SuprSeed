package dev.suprseed.demo.GamePlayScene.EntityScene;

import android.content.Context;

import java.util.List;

import dev.suprseed.Engine.Core.Scenes.SceneHeirarchy.BaseScene;
import dev.suprseed.Engine.Core.SpriteObjects.SpriteBase.AssetBundle;
import dev.suprseed.Engine.Core.SpriteObjects.SpriteBase.Sprite;
import dev.suprseed.Engine.EngineTools;
import dev.suprseed.Engine.Lib.AssetLoader.Bundler;
import dev.suprseed.Engine.Lib.SoundPlayer.SoundMixer;
import dev.suprseed.demo.GamePlayScene.SharedData.BounceData;
import dev.suprseed.demo.GamePlayScene.SharedData.GameOverData;
import dev.suprseed.demo.GamePlayScene.EntityScene.Hero.FullScreenHeroTouchInput;
import dev.suprseed.demo.GamePlayScene.EntityScene.Hero.Hero;
import dev.suprseed.demo.GamePlayScene.EntityScene.Obstacles.ObstacleCollection;

public class EntityScene extends BaseScene {

    private final Sprite hero;
    private final ObstacleCollection obstacleHandler;
    private FullScreenHeroTouchInput fullScreenHeroTouchInput;

    public EntityScene(Context context, String sceneId, Bundler<AssetBundle> assetBundler, SoundMixer<String> gamePlaySounds, BounceData bounceData, GameOverData gameOverData) {
        super(sceneId);

        fullScreenHeroTouchInput = new FullScreenHeroTouchInput(false, gameOverData);

        // Create the character sprites here
        this.hero = new Hero(this, context, assetBundler.generateAssetBundle(List.of("heroLeft", "heroRight")), gamePlaySounds, bounceData, fullScreenHeroTouchInput);
        registerSprite(hero);

        this.obstacleHandler = new ObstacleCollection(this, assetBundler, bounceData, hero, gamePlaySounds, gameOverData);
    }

    @Override
    public void onPost() {

        EngineTools.getInputManager().getListenerRegistry().registerObject(fullScreenHeroTouchInput);
    }

    @Override
    public void resetState() {

        hero.resetState();
        obstacleHandler.resetState();
    }

    @Override
    public void onDestroy() {

        EngineTools.getInputManager().getListenerRegistry().removeObject(fullScreenHeroTouchInput);
    }
}
