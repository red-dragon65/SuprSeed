package dev.suprseed.demo.GamePlayScene;

import android.content.Context;
import android.media.MediaPlayer;

import androidx.annotation.NonNull;
import androidx.lifecycle.DefaultLifecycleObserver;
import androidx.lifecycle.LifecycleOwner;

import java.util.HashMap;
import java.util.Map;

import dev.suprseed.Engine.Core.MainView.GameProcessor.Loop.WindowEventListener;
import dev.suprseed.Engine.Core.MainView.GameProcessor.Render.Graphics.RenderHandler;
import dev.suprseed.Engine.Core.Scenes.SceneHeirarchy.SceneManager;
import dev.suprseed.Engine.Core.SpriteObjects.SpriteBase.AssetBundle;
import dev.suprseed.Engine.Core.SpriteObjects.SpriteBase.SpriteImage;
import dev.suprseed.Engine.EngineTools;
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
import dev.suprseed.demo.GamePlayScene.EntityScene.EntityScene;
import dev.suprseed.demo.GamePlayScene.HudScene.OverlayScene;
import dev.suprseed.demo.GamePlayScene.SharedData.BounceData;
import dev.suprseed.demo.GamePlayScene.SharedData.GameOverData;
import dev.suprseed.demo.MainActivity;
import dev.suprseed.demo.R;

public class GamePlayScene extends SceneManager {

    private final MediaPlayer mediaPlayer;
    private final EntityScene entities;
    private final OverlayScene overlay;
    private final GameOverData gameOverData;
    private final SoundMixer<String> soundEffects;
    private final WindowEventListener pauseEventListener;

    // Constructor
    public GamePlayScene(Context context, String sceneId) {
        super(sceneId);

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

        pauseEventListener = hasFocus -> EngineTools.getLoopController().setSoftPause(hasFocus);


        // Shared data
        BounceData bounceData = new BounceData();
        gameOverData = new GameOverData();

        // Create leaf scenes here
        // AND/OR create the sprites for this scene
        // NOTE: ORDER MATTERS! OR you can set the scenes layer value!
        registerScene(new BackgroundScene("background", assetBundler));

        entities = new EntityScene(context, "entities", assetBundler, soundEffects, bounceData, gameOverData);
        registerScene(entities);

        overlay = new OverlayScene(context, "overlay", assetBundler, bounceData, gameOverData);
        registerScene(overlay);
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
    public void onPost() {

        // Pause the game when the window gets focused again
        // By default, the engine will pause/resume when the window loses/has focus
        EngineTools.getWindowEventRegistry().registerObject(pauseEventListener);
        loadLifeCycleHandlers();
    }

    @Override
    public void runLogic() {

        if (gameOverData.isRestart()) {

            overlay.resetState();
            entities.resetState();

            gameOverData.setRestart(false);
        }

        super.runLogic();
    }

    @Override
    public void onDestroy() {

        EngineTools.getWindowEventRegistry().removeObject(pauseEventListener);
    }

    @Override
    public void draw(RenderHandler renderer) {
        super.draw(renderer);
    }
}
