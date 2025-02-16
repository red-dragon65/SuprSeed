package dev.suprseed.demo.Subscenes;

import android.media.MediaPlayer;

import androidx.annotation.NonNull;
import androidx.lifecycle.DefaultLifecycleObserver;
import androidx.lifecycle.LifecycleOwner;

import java.util.HashMap;
import java.util.Map;

import dev.suprseed.Engine.Core.MainView.GameProcessor.Render.Graphics.RenderHandler;
import dev.suprseed.Engine.Core.Scenes.SceneHeirarchy.BaseScene;
import dev.suprseed.Engine.Core.Scenes.SceneHeirarchy.SceneManager;
import dev.suprseed.Engine.Core.SpriteObjects.SpriteBase.AssetBundle;
import dev.suprseed.Engine.Core.SpriteObjects.SpriteBase.SpriteImage;
import dev.suprseed.Engine.Lib.AssetLoader.AssetLoadable;
import dev.suprseed.Engine.Lib.AssetLoader.Bundler;
import dev.suprseed.Engine.Lib.AssetLoader.FolderParser;
import dev.suprseed.Engine.Lib.AssetLoader.LocalFolderParser;
import dev.suprseed.Engine.Lib.AssetLoader.LocalImageFileStreamer;
import dev.suprseed.Engine.Lib.AssetLoader.SafeAssetBundler;
import dev.suprseed.Engine.Lib.AssetLoader.Streamable;
import dev.suprseed.Engine.Lib.Images.PlaceHolder;
import dev.suprseed.Engine.Lib.SoundPlayer.BasicSoundEffects;
import dev.suprseed.Engine.Lib.SoundPlayer.SoundMixer;
import dev.suprseed.demo.Assets.GameDemoAssets;
import dev.suprseed.demo.MainActivity;
import dev.suprseed.demo.R;
import dev.suprseed.demo.SharedData.BounceData;
import dev.suprseed.demo.SharedData.GameOverData;

public class GameDemoMainScene extends SceneManager {

    private final MediaPlayer mediaPlayer;
    private final EntityScene entities;
    private final OverlayScene overlay;
    private final GameOverData gameOverData;
    private final SoundMixer<String> soundEffects;

    // Constructor
    public GameDemoMainScene(SceneManager parentScene, String sceneId) {
        super(parentScene, sceneId);

        //EngineTools.getCollisionDrawer().setEnabled(true);

        // Instantiate the assets for this scene
        FolderParser localFolderParser = new LocalFolderParser(context.getResources());
        Streamable localStreamer = new LocalImageFileStreamer(context.getResources());

        // Define a place holder asset
        PlaceHolder placeHolder;
        try {
            placeHolder = new PlaceHolder("Images/Placeholder.png", 1, localStreamer);
        } catch (Exception e) {
            throw new RuntimeException("The placeholder image could not load! Check the file path or image file!");
        }

        // Create the asset loader to and bundler
        AssetLoadable<SpriteImage> gamePlayAssets = new GameDemoAssets(this, localStreamer, localFolderParser);
        Bundler<AssetBundle> assetBundler = new SafeAssetBundler(gamePlayAssets, placeHolder);

        // Instantiate the sounds for this scene
        soundEffects = new BasicSoundEffects<>();
        Map<String, Integer> sounds = new HashMap<>();
        sounds.put("bounce", R.raw.bounce);
        sounds.put("hit", R.raw.hit);
        soundEffects.loadSounds(sounds, context);

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
        BaseScene background = new BackgroundScene(this, "background", assetBundler);
        entities = new EntityScene(this, "entities", assetBundler, soundEffects, bounceData, gameOverData);
        overlay = new OverlayScene(this, "overlay", assetBundler, bounceData, gameOverData);

        loadLifeCycleHandlers();
    }

    private void loadLifeCycleHandlers() {

        MainActivity.lifecycleRegistry.addObserver(new DefaultLifecycleObserver() {
            @Override
            public void onPause(@NonNull LifecycleOwner owner) {
                DefaultLifecycleObserver.super.onPause(owner);
                mediaPlayer.pause();
                soundEffects.pauseAll();
            }

            @Override
            public void onResume(@NonNull LifecycleOwner owner) {
                DefaultLifecycleObserver.super.onResume(owner);
                mediaPlayer.start();
                soundEffects.resumeAll();
            }
        });
    }

    @Override
    public void runLogic() {

        // TODO: Make 'Home' scene show after restarting scenes
        if (gameOverData.isRestart()) {

            overlay.resetState();
            entities.resetState();

            gameOverData.setRestart(false);
        }

        super.runLogic();
    }

    @Override
    public void draw(RenderHandler renderer) {
        super.draw(renderer);
    }
}
