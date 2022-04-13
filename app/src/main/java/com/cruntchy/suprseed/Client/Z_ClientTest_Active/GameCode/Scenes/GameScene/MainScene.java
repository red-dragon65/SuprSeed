package com.cruntchy.suprseed.Client.Z_ClientTest_Active.GameCode.Scenes.GameScene;

import com.cruntchy.suprseed.Client.Z_ClientTest_Active.GameCode.Assets.AssetScriptTest;
import com.cruntchy.suprseed.Client.Z_ClientTest_Active.GameCode.Sprites.BackgroundSprite.Background;
import com.cruntchy.suprseed.Client.Z_ClientTest_Active.GameCode.Sprites.HeroSprite.Hero;
import com.cruntchy.suprseed.Engine.AssetLoader.AssetLoader;
import com.cruntchy.suprseed.Engine.AssetLoader.FolderParser;
import com.cruntchy.suprseed.Engine.AssetLoader.LocalFolderParser;
import com.cruntchy.suprseed.Engine.AssetLoader.LocalImageFileStreamer;
import com.cruntchy.suprseed.Engine.AssetLoader.Streamable;
import com.cruntchy.suprseed.Engine.Images.SpriteImage;
import com.cruntchy.suprseed.Engine.Scenes.SceneHeirarchy.BaseScene;
import com.cruntchy.suprseed.Engine.Scenes.SceneHeirarchy.SceneManager;
import com.cruntchy.suprseed.Engine.SoundPlayer.BasicSoundEffects;
import com.cruntchy.suprseed.Engine.SoundPlayer.SoundMixer;
import com.cruntchy.suprseed.Engine.SpriteObjects.SpriteBase.ImageHandler;
import com.cruntchy.suprseed.Engine.SpriteObjects.SpriteBase.Sprite;
import com.cruntchy.suprseed.R;

import java.util.HashMap;
import java.util.Map;

public class MainScene extends BaseScene {

    private Hero hero;


    // Constructor
    public MainScene(SceneManager parentScene, String sceneId){
        super(parentScene, sceneId);

        // Instantiate the assets for this scene
        FolderParser localFolderParser = new LocalFolderParser(parentScene.getGameInfo().getResources());
        Streamable localStreamer = new LocalImageFileStreamer(parentScene.getGameInfo().getResources());
        AssetLoader myAssets = new AssetScriptTest(this, localStreamer, localFolderParser);

        // Instantiate the sounds for this scene
        SoundMixer<String> soundEngine = new BasicSoundEffects<>();
        Map<String, Integer> sounds = new HashMap<>();
        sounds.put("bounce", R.raw.bounce);
        soundEngine.loadSounds(sounds, parentScene.getGameInfo().getContext());


        // Create the sprites for this scene
        Sprite background = new Background(this, new ImageHandler("background", myAssets.getImage("background")));
        hero = new Hero(this, new ImageHandler("hero", (SpriteImage) myAssets.getAnimation("hero")), soundEngine);
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
