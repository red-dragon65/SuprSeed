package dev.suprseed.demo.Subscenes;

import android.media.MediaPlayer;

import java.util.HashMap;
import java.util.Map;

import dev.suprseed.Engine.Core.MainView.GameProcessor.Loop.LoopManager;
import dev.suprseed.Engine.Core.MainView.GameProcessor.Render.Graphics.RenderHandler;
import dev.suprseed.Engine.Core.Scenes.SceneHeirarchy.BaseScene;
import dev.suprseed.Engine.Core.Scenes.SceneHeirarchy.SceneManager;
import dev.suprseed.Engine.Lib.AssetLoader.AssetLoader;
import dev.suprseed.Engine.Lib.AssetLoader.FolderParser;
import dev.suprseed.Engine.Lib.AssetLoader.LocalFolderParser;
import dev.suprseed.Engine.Lib.AssetLoader.LocalImageFileStreamer;
import dev.suprseed.Engine.Lib.AssetLoader.Streamable;
import dev.suprseed.Engine.Lib.SoundPlayer.BasicSoundEffects;
import dev.suprseed.Engine.Lib.SoundPlayer.SoundMixer;
import dev.suprseed.demo.Assets.GameDemoAssets;
import dev.suprseed.demo.R;
import dev.suprseed.demo.SharedData.BounceData;
import dev.suprseed.demo.SharedData.GameOverData;

public class GameDemoMainScene extends SceneManager {

    private final MediaPlayer mediaPlayer;
    private final EntityScene entities;
    private final OverlayScene overlay;
    private final GameOverData gameOverData;
    private boolean musicHasStarted = false;

    // Constructor
    public GameDemoMainScene(SceneManager parentScene, String sceneId) {
        super(parentScene, sceneId);

        //CollisionDiagnosticsOverlay.getInstance().setEnabled(true);

        // Instantiate the assets for this scene
        FolderParser localFolderParser = new LocalFolderParser(context.getResources());
        Streamable localStreamer = new LocalImageFileStreamer(context.getResources());
        AssetLoader gamePlayAssets = new GameDemoAssets(this, localStreamer, localFolderParser);

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
