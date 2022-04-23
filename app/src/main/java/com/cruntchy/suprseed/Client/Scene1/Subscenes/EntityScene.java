package com.cruntchy.suprseed.Client.Scene1.Subscenes;

import com.cruntchy.suprseed.Client.Scene1.Data.BounceData;
import com.cruntchy.suprseed.Client.Scene1.Data.GameOverData;
import com.cruntchy.suprseed.Client.Scene1.Sprites.HeroSprite.Hero;
import com.cruntchy.suprseed.Client.Scene1.Sprites.Obstacles.ObstacleCollection;
import com.cruntchy.suprseed.Engine.AssetLoader.AssetLoader;
import com.cruntchy.suprseed.Engine.Images.SpriteImage;
import com.cruntchy.suprseed.Engine.Scenes.SceneHeirarchy.BaseScene;
import com.cruntchy.suprseed.Engine.Scenes.SceneHeirarchy.SceneManager;
import com.cruntchy.suprseed.Engine.SoundPlayer.SoundMixer;
import com.cruntchy.suprseed.Engine.SpriteObjects.SpriteBase.ImageHandler;

public class EntityScene extends BaseScene {

    private final Hero hero;
    private final ObstacleCollection obstacleHandler;
    private final GameOverData gameOverData;

    public EntityScene(SceneManager parentScene, String sceneId, AssetLoader gamePlayAssets, SoundMixer<String> gamePlaySounds, BounceData bounceData, GameOverData gameOverData) {
        super(parentScene, sceneId);

        this.gameOverData = gameOverData;

        // Create the character sprites here
        this.hero = new Hero(this, new ImageHandler("hero", (SpriteImage) gamePlayAssets.getAnimation("hero")), gamePlaySounds, bounceData, gameOverData);

        this.obstacleHandler = new ObstacleCollection(this, gamePlayAssets, bounceData, hero, gamePlaySounds, gameOverData);
    }


    @Override
    public void runLogic() {
        super.runLogic();
    }

    @Override
    public void resetState() {

        hero.resetState();
        obstacleHandler.resetState();
    }
}
