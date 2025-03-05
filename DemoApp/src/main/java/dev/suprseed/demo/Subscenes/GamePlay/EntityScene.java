package dev.suprseed.demo.Subscenes.GamePlay;

import android.content.Context;

import java.util.List;

import dev.suprseed.Engine.Core.Scenes.SceneHeirarchy.BaseScene;
import dev.suprseed.Engine.Core.SpriteObjects.SpriteBase.AssetBundle;
import dev.suprseed.Engine.Core.SpriteObjects.SpriteBase.Sprite;
import dev.suprseed.Engine.Lib.AssetLoader.Bundler;
import dev.suprseed.Engine.Lib.SoundPlayer.SoundMixer;
import dev.suprseed.demo.SharedData.BounceData;
import dev.suprseed.demo.SharedData.GameOverData;
import dev.suprseed.demo.Sprites.HeroSprite.Hero;
import dev.suprseed.demo.Sprites.Obstacles.ObstacleCollection;

public class EntityScene extends BaseScene {

    private final Sprite hero;
    private final ObstacleCollection obstacleHandler;

    public EntityScene(Context context, String sceneId, Bundler<AssetBundle> assetBundler, SoundMixer<String> gamePlaySounds, BounceData bounceData, GameOverData gameOverData) {
        super(sceneId);

        // Create the character sprites here
        this.hero = new Hero(this, context, assetBundler.generateAssetBundle(List.of("heroLeft", "heroRight")), gamePlaySounds, bounceData, gameOverData);
        registerSprite(hero);

        this.obstacleHandler = new ObstacleCollection(this, assetBundler, bounceData, hero, gamePlaySounds, gameOverData);
    }

    @Override
    public void resetState() {

        hero.resetState();
        obstacleHandler.resetState();
    }
}
