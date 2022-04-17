package com.cruntchy.suprseed.Client.Scene1.Subscenes;

import com.cruntchy.suprseed.Client.Scene1.Sprites.HeroSprite.Hero;
import com.cruntchy.suprseed.Engine.AssetLoader.AssetLoader;
import com.cruntchy.suprseed.Engine.Images.SpriteImage;
import com.cruntchy.suprseed.Engine.Scenes.SceneHeirarchy.BaseScene;
import com.cruntchy.suprseed.Engine.Scenes.SceneHeirarchy.SceneManager;
import com.cruntchy.suprseed.Engine.SoundPlayer.SoundMixer;
import com.cruntchy.suprseed.Engine.SpriteObjects.SpriteBase.ImageHandler;

public class EntityScene extends BaseScene {

    private Hero hero;

    public EntityScene(SceneManager parentScene, String sceneId, AssetLoader gamePlayAssets, SoundMixer<String> gamePlaySounds) {
        super(parentScene, sceneId);

        // Create the character sprites here
        hero = new Hero(this, new ImageHandler("hero", (SpriteImage) gamePlayAssets.getAnimation("hero")), gamePlaySounds);

    }





    @Override
    public void runLogic() {
        super.runLogic();

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
        if(!hero.isActive()){

            // Start the game over overlay scene
            //Scene gameOverUI = new GameOverOverlayScene(sceneManager, "GameOver");

            //sceneManager.getRegister().registerObject(gameOverUI);
        }
    }
}
