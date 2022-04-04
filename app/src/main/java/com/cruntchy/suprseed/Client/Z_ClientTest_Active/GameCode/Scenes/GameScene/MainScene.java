package com.cruntchy.suprseed.Client.Z_ClientTest_Active.GameCode.Scenes.GameScene;

import android.content.Context;

import com.cruntchy.suprseed.Client.Z_ClientTest_Active.GameCode.Sprites.BackgroundSprite.Background;
import com.cruntchy.suprseed.Client.Z_ClientTest_Active.GameCode.Sprites.HeroSprite.Hero;
import com.cruntchy.suprseed.Engine.AssetLoader.AssetLoader;
import com.cruntchy.suprseed.Engine.Images.SpriteImage;
import com.cruntchy.suprseed.Engine.MainView.GameProcessor.Loop.Scene;
import com.cruntchy.suprseed.Engine.MainView.GameProcessor.Loop.SceneController;
import com.cruntchy.suprseed.Engine.SoundPlayer.BasicSoundEffects;
import com.cruntchy.suprseed.Engine.SoundPlayer.SoundMixer;
import com.cruntchy.suprseed.Engine.SpriteObjects.SpriteBase.ImageHandler;
import com.cruntchy.suprseed.R;

import java.util.HashMap;
import java.util.Map;

public class MainScene extends Scene {


    public MainScene(SceneController sceneManager, String id, AssetLoader assets, Context context) {
        super(sceneManager, id);

        SoundMixer<String> soundEngine = new BasicSoundEffects<>();
        Map<String, Integer> sounds = new HashMap<>();
        sounds.put("bounce", R.raw.bounce);
        soundEngine.loadSounds(sounds, context);


        new Background(new ImageHandler("background", assets.getImage("background")));

        //new Hero(new ImageHandler("hero", assets.getImage("hero")));
        new Hero(new ImageHandler("hero", (SpriteImage) assets.getAnimation("hero")), soundEngine);
    }

    @Override
    public void runLogic() {

        // ignore this for now
    }
}
