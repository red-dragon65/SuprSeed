package com.cruntchy.suprseed.Client.Scene1.Subscenes;

import com.cruntchy.suprseed.Client.Scene1.Assets.GamePlayAssets;
import com.cruntchy.suprseed.Engine.AssetLoader.AssetLoader;
import com.cruntchy.suprseed.Engine.AssetLoader.FolderParser;
import com.cruntchy.suprseed.Engine.AssetLoader.LocalFolderParser;
import com.cruntchy.suprseed.Engine.AssetLoader.LocalImageFileStreamer;
import com.cruntchy.suprseed.Engine.AssetLoader.Streamable;
import com.cruntchy.suprseed.Engine.Scenes.SceneHeirarchy.BaseScene;
import com.cruntchy.suprseed.Engine.Scenes.SceneHeirarchy.SceneManager;
import com.cruntchy.suprseed.Engine.SoundPlayer.BasicSoundEffects;
import com.cruntchy.suprseed.Engine.SoundPlayer.SoundMixer;
import com.cruntchy.suprseed.Engine.SpriteObjects.System.Logic;
import com.cruntchy.suprseed.R;

import java.util.HashMap;
import java.util.Map;

public class TopScene extends SceneManager {

    private boolean musicHasStarted = false;
    private SoundMixer<String> soundEngine;

    // Constructor
    public TopScene(SceneManager parentScene, String sceneId){
        super(parentScene, sceneId);

        // Instantiate the assets for this scene
        FolderParser localFolderParser = new LocalFolderParser(context.getResources());
        Streamable localStreamer = new LocalImageFileStreamer(context.getResources());
        AssetLoader gamePlayAssets = new GamePlayAssets(this, localStreamer, localFolderParser);

        // Instantiate the sounds for this scene
        SoundMixer<String> soundEngine = new BasicSoundEffects<>();
        Map<String, Integer> sounds = new HashMap<>();
        sounds.put("bounce", R.raw.bounce);
        sounds.put("hit", R.raw.hit);
        sounds.put("background_music", R.raw.background_music);
        soundEngine.loadSounds(sounds, context);
        this.soundEngine = soundEngine;


        // Create leaf scenes here
        // AND/OR create the sprites for this scene
        // NOTE: ORDER MATTERS! OR you can set the scenes priority value!
        BaseScene background = new BackgroundScene(this, "background", gamePlayAssets);
        BaseScene entities = new EntityScene(this, "entities", gamePlayAssets, soundEngine);
        BaseScene overlay = new OverlayScene(this, "overlay", gamePlayAssets);

    }

    @Override
    public void runLogic(){
        super.runLogic();

        // TODO: Add code to sound effects to verify sound has loaded before playing
        if(!musicHasStarted){

            // Start background music
            soundEngine.playSound("background_music", true);
            musicHasStarted = true;
        }
    }
}
