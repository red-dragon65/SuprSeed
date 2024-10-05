package dev.suprseed.demo.Client.DemoScene.Subscenes;

import dev.suprseed.demo.Client.DemoScene.SharedData.BounceData;
import dev.suprseed.demo.Client.DemoScene.SharedData.GameOverData;
import dev.suprseed.demo.Client.DemoScene.Sprites.HeroSprite.Hero;
import dev.suprseed.demo.Client.DemoScene.Sprites.Obstacles.ObstacleCollection;
import dev.suprseed.Engine.Core.Scenes.SceneHeirarchy.BaseScene;
import dev.suprseed.Engine.Core.Scenes.SceneHeirarchy.SceneManager;
import dev.suprseed.Engine.Core.SpriteObjects.SpriteBase.ImageHandler;
import dev.suprseed.Engine.Core.SpriteObjects.SpriteBase.Sprite;
import dev.suprseed.Engine.Lib.AssetLoader.AssetLoader;
import dev.suprseed.Engine.Lib.Images.SpriteImage;
import dev.suprseed.Engine.Lib.SoundPlayer.SoundMixer;

public class EntityScene extends BaseScene {

    private final Sprite hero;
    private final ObstacleCollection obstacleHandler;

    public EntityScene(SceneManager parentScene, String sceneId, AssetLoader gamePlayAssets, SoundMixer<String> gamePlaySounds, BounceData bounceData, GameOverData gameOverData) {
        super(parentScene, sceneId);

        // Create the character sprites here
        this.hero = new Hero(this, new ImageHandler("hero", (SpriteImage) gamePlayAssets.getAnimation("hero")), gamePlaySounds, bounceData, gameOverData);

        this.obstacleHandler = new ObstacleCollection(this, gamePlayAssets, bounceData, hero, gamePlaySounds, gameOverData);
    }

    @Override
    public void resetState() {

        hero.resetState();
        obstacleHandler.resetState();
    }
}
