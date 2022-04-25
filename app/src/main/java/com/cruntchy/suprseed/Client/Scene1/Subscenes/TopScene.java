package com.cruntchy.suprseed.Client.Scene1.Subscenes;

import android.media.MediaPlayer;

import com.cruntchy.suprseed.Client.Scene1.Assets.GamePlayAssets;
import com.cruntchy.suprseed.Client.Scene1.Data.BounceData;
import com.cruntchy.suprseed.Client.Scene1.Data.GameOverData;
import com.cruntchy.suprseed.Engine.Core.MainView.GameProcessor.Loop.LoopManager;
import com.cruntchy.suprseed.Engine.Core.MainView.GameProcessor.Render.Graphics.RenderHandler;
import com.cruntchy.suprseed.Engine.Core.Scenes.SceneHeirarchy.BaseScene;
import com.cruntchy.suprseed.Engine.Core.Scenes.SceneHeirarchy.SceneManager;
import com.cruntchy.suprseed.Engine.Lib.AssetLoader.AssetLoader;
import com.cruntchy.suprseed.Engine.Lib.AssetLoader.FolderParser;
import com.cruntchy.suprseed.Engine.Lib.AssetLoader.LocalFolderParser;
import com.cruntchy.suprseed.Engine.Lib.AssetLoader.LocalImageFileStreamer;
import com.cruntchy.suprseed.Engine.Lib.AssetLoader.Streamable;
import com.cruntchy.suprseed.Engine.Lib.SoundPlayer.BasicSoundEffects;
import com.cruntchy.suprseed.Engine.Lib.SoundPlayer.SoundMixer;
import com.cruntchy.suprseed.R;

import java.util.HashMap;
import java.util.Map;

public class TopScene extends SceneManager {

    private boolean musicHasStarted = false;
    private final MediaPlayer mediaPlayer;

    private final EntityScene entities;
    private final OverlayScene overlay;

    private final GameOverData gameOverData;

    // Constructor
    public TopScene(SceneManager parentScene, String sceneId) {
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
        soundEngine.loadSounds(sounds, context);

        // Set the background music
        mediaPlayer = MediaPlayer.create(context, R.raw.background_music);
        mediaPlayer.setLooping(true);
        mediaPlayer.setVolume(0.60f, 0.60f);


        // Shared data
        BounceData bounceData = new BounceData();
        gameOverData = new GameOverData();

        // Create leaf scenes here
        // AND/OR create the sprites for this scene
        // NOTE: ORDER MATTERS! OR you can set the scenes priority value!
        BaseScene background = new BackgroundScene(this, "background", gamePlayAssets);
        entities = new EntityScene(this, "entities", gamePlayAssets, soundEngine, bounceData, gameOverData);
        overlay = new OverlayScene(this, "overlay", gamePlayAssets, bounceData, gameOverData);

    }

    @Override
    public void runLogic() {

        // TODO: Make 'Home' scene show after restarting scenes
        if (gameOverData.isRestart()) {

            overlay.resetState();
            entities.resetState();

            gameOverData.setRestart(false);
        }


        // Start/resume background music
        if (!musicHasStarted) {

            mediaPlayer.start();
            musicHasStarted = true;
        }


        super.runLogic();
    }

    @Override
    public void draw(RenderHandler renderer) {
        super.draw(renderer);

        // Pause sound if necessary
        if (LoopManager.loopy.isSoftPause()) {

            mediaPlayer.pause();
            musicHasStarted = false;

        }
    }
}
