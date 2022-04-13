package com.cruntchy.suprseed.Client.Z_ClientTest_Active.GameCode.Scenes.GameScene;

import android.content.Context;

import com.cruntchy.suprseed.Client.Z_ClientTest_Active.GameCode.Assets.AssetScriptTest;
import com.cruntchy.suprseed.Client.Z_ClientTest_Active.GameCode.Scenes.UIOverlayScene.GameOverOverlayScene;
import com.cruntchy.suprseed.Client.Z_ClientTest_Active.GameCode.Sprites.BackgroundSprite.Background;
import com.cruntchy.suprseed.Client.Z_ClientTest_Active.GameCode.Sprites.HeroSprite.Hero;
import com.cruntchy.suprseed.Engine.AssetLoader.AssetLoader;
import com.cruntchy.suprseed.Engine.AssetLoader.FolderParser;
import com.cruntchy.suprseed.Engine.AssetLoader.LocalFolderParser;
import com.cruntchy.suprseed.Engine.AssetLoader.LocalImageFileStreamer;
import com.cruntchy.suprseed.Engine.AssetLoader.Streamable;
import com.cruntchy.suprseed.Engine.Images.SpriteImage;
import com.cruntchy.suprseed.Engine.MainView.Scenes.Scene;
import com.cruntchy.suprseed.Engine.MainView.Scenes.SceneController;
import com.cruntchy.suprseed.Engine.SoundPlayer.BasicSoundEffects;
import com.cruntchy.suprseed.Engine.SoundPlayer.SoundMixer;
import com.cruntchy.suprseed.Engine.SpriteObjects.SpriteBase.ImageHandler;
import com.cruntchy.suprseed.R;

import java.util.HashMap;
import java.util.Map;

public class MainScene extends Scene {


    private Hero hero;


    public MainScene(SceneController sceneManager, String id, Context context) {
        super(sceneManager, id);

        FolderParser localFolderParser = new LocalFolderParser(sceneManager.getResources());
        Streamable localStreamer = new LocalImageFileStreamer(sceneManager.getResources());
        AssetLoader myAssets = new AssetScriptTest(localStreamer, localFolderParser);

        SoundMixer<String> soundEngine = new BasicSoundEffects<>();
        Map<String, Integer> sounds = new HashMap<>();
        sounds.put("bounce", R.raw.bounce);
        soundEngine.loadSounds(sounds, context);


        new Background(new ImageHandler("background", myAssets.getImage("background")));

        //new Hero(new ImageHandler("hero", assets.getImage("hero")));
        hero = new Hero(new ImageHandler("hero", (SpriteImage) myAssets.getAnimation("hero")), soundEngine);
    }

    @Override
    public void runLogic() {

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
            Scene gameOverUI = new GameOverOverlayScene(sceneManager, "GameOver");
            gameOverUI.setActive(true);
            gameOverUI.setHidden(false);

            sceneManager.registerObject(gameOverUI);
        }
    }
}
