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
        this.hero = new Hero(this, new ImageHandler("hero", (SpriteImage) gamePlayAssets.getAnimation("hero")), gamePlaySounds, bounceData);

        this.obstacleHandler = new ObstacleCollection(this, gamePlayAssets, bounceData, hero, gamePlaySounds, gameOverData);
    }





    @Override
    public void runLogic() {

        // TODO: Move this to 'TopScene'
        //  Make 'Home' scene run instead of restarting this scene
        if (gameOverData.isRestart()) {

            hero.resetState();
            obstacleHandler.resetState();

            gameOverData.setRestart(false);
        }

        // ignore this for now

        /*
        // See if hero has died
        if(!hero.isActive()){

            // Switch to a different scene

            SceneStrategy softChange = new SceneSoftChange();
            sceneManager.changeScene(softChange, this, "LandingScene");
        }
         */


        // See if hero has died
        if (!hero.isActive()) {

            // Start the game over overlay scene
            //Scene gameOverUI = new GameOverOverlayScene(sceneManager, "GameOver");

            //sceneManager.getRegister().registerObject(gameOverUI);
        }

        super.runLogic();
    }
}
