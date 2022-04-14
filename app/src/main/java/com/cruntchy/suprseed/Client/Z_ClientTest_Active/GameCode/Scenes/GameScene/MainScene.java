package com.cruntchy.suprseed.Client.Z_ClientTest_Active.GameCode.Scenes.GameScene;

import com.cruntchy.suprseed.Client.Z_ClientTest_Active.GameCode.Assets.GamePlayAssets;
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

public class MainScene extends SceneManager {

    // Constructor
    public MainScene(SceneManager parentScene, String sceneId){
        super(parentScene, sceneId);

        // Instantiate the assets for this scene
        FolderParser localFolderParser = new LocalFolderParser(parentScene.getGameInfo().getResources());
        Streamable localStreamer = new LocalImageFileStreamer(parentScene.getGameInfo().getResources());
        AssetLoader gamePlayAssets = new GamePlayAssets(this, localStreamer, localFolderParser);

        // Instantiate the sounds for this scene
        SoundMixer<String> soundEngine = new BasicSoundEffects<>();
        Map<String, Integer> sounds = new HashMap<>();
        sounds.put("bounce", R.raw.bounce);
        soundEngine.loadSounds(sounds, parentScene.getGameInfo().getContext());


        // Create leaf scenes here
        // AND/OR create the sprites for this scene
        // NOTE: ORDER MATTERS! OR you can set the scenes priority value!
        BaseScene background = new BackgroundScene(this, "background", gamePlayAssets);
        BaseScene entities = new EntityScene(this, "entities", gamePlayAssets, soundEngine);
        BaseScene overlay = new OverlayScene(this, "overlay");

    }
}
