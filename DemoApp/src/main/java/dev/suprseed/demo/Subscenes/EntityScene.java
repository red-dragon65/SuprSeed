package dev.suprseed.demo.Subscenes;

import java.util.List;

import dev.suprseed.Engine.Core.Scenes.SceneHeirarchy.BaseScene;
import dev.suprseed.Engine.Core.Scenes.SceneHeirarchy.SceneManager;
import dev.suprseed.Engine.Core.SpriteObjects.SpriteBase.AssetBundle;
import dev.suprseed.Engine.Core.SpriteObjects.SpriteBase.Sprite;
import dev.suprseed.Engine.Lib.AssetLoader.AssetLoadable;
import dev.suprseed.Engine.Lib.Images.SpriteImage;
import dev.suprseed.Engine.Lib.SoundPlayer.SoundMixer;
import dev.suprseed.demo.SharedData.BounceData;
import dev.suprseed.demo.SharedData.GameOverData;
import dev.suprseed.demo.Sprites.HeroSprite.Hero;
import dev.suprseed.demo.Sprites.Obstacles.ObstacleCollection;

public class EntityScene extends BaseScene {

    private final Sprite hero;
    private final ObstacleCollection obstacleHandler;

    public EntityScene(SceneManager parentScene, String sceneId, AssetLoadable<AssetBundle, SpriteImage> gamePlayAssets, SoundMixer<String> gamePlaySounds, BounceData bounceData, GameOverData gameOverData) {
        super(parentScene, sceneId);

        // Create the character sprites here
        this.hero = new Hero(this, gamePlayAssets.getAssetBundle(List.of("heroLeft", "heroRight")), gamePlaySounds, bounceData, gameOverData);

        this.obstacleHandler = new ObstacleCollection(this, gamePlayAssets, bounceData, hero, gamePlaySounds, gameOverData);
    }

    @Override
    public void resetState() {

        hero.resetState();
        obstacleHandler.resetState();
    }
}
