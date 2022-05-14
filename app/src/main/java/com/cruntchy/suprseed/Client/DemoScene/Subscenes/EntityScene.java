package com.cruntchy.suprseed.Client.DemoScene.Subscenes;

import com.cruntchy.suprseed.Client.DemoScene.SharedData.BounceData;
import com.cruntchy.suprseed.Client.DemoScene.SharedData.GameOverData;
import com.cruntchy.suprseed.Client.DemoScene.Sprites.HeroSprite.Hero;
import com.cruntchy.suprseed.Client.DemoScene.Sprites.Obstacles.ObstacleCollection;
import com.cruntchy.suprseed.Engine.Core.Scenes.SceneHeirarchy.BaseScene;
import com.cruntchy.suprseed.Engine.Core.Scenes.SceneHeirarchy.SceneManager;
import com.cruntchy.suprseed.Engine.Core.SpriteObjects.SpriteBase.ImageHandler;
import com.cruntchy.suprseed.Engine.Core.SpriteObjects.SpriteBase.Sprite;
import com.cruntchy.suprseed.Engine.Lib.AssetLoader.AssetLoader;
import com.cruntchy.suprseed.Engine.Lib.Images.SpriteImage;
import com.cruntchy.suprseed.Engine.Lib.SoundPlayer.SoundMixer;

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
