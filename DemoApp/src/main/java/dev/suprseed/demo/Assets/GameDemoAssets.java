package dev.suprseed.demo.Assets;

import android.util.Log;

import java.io.IOException;

import dev.suprseed.Engine.Core.Scenes.SceneHeirarchy.BaseScene;
import dev.suprseed.Engine.Lib.AssetLoader.FolderParser;
import dev.suprseed.Engine.Lib.AssetLoader.SafeAssetLoader;
import dev.suprseed.Engine.Lib.AssetLoader.Streamable;
import dev.suprseed.Engine.Lib.Images.BitmapAnimation;
import dev.suprseed.Engine.Lib.Images.BitmapSingle;
import dev.suprseed.Engine.Lib.Images.DefaultFPS;
import dev.suprseed.Engine.Lib.Images.PlaceHolder;

public class GameDemoAssets extends SafeAssetLoader {


    public GameDemoAssets(BaseScene parentScene, Streamable assetStreamer, FolderParser folderParser, PlaceHolder placeHolder) {
        super(parentScene, assetStreamer, folderParser, placeHolder);
    }


    @Override
    public void loadAssets(BaseScene parentScene) {

        // Client can load sprites images here

        try {
            // Hero
            BitmapAnimation heroLeft = new BitmapAnimation(parentScene, "Images/Hero/Left", 10, assetStreamer, folderParser, DefaultFPS._2.toInt(), true, "heroLeft");
            images.add(heroLeft);

            BitmapAnimation heroRight = new BitmapAnimation(parentScene, "Images/Hero/Right", 10, assetStreamer, folderParser, DefaultFPS._2.toInt(), true, "heroRight");
            images.add(heroRight);

            // Enemies
            int enemyAnimationSpeed = DefaultFPS._15.toInt();

            Log.e("", "fps: " + enemyAnimationSpeed);

            BitmapAnimation bat = new BitmapAnimation(parentScene, "Images/Enemies/bat", 7, assetStreamer, folderParser, enemyAnimationSpeed, true, "bat");
            images.add(bat);

            BitmapAnimation bee = new BitmapAnimation(parentScene, "Images/Enemies/bee", 7, assetStreamer, folderParser, enemyAnimationSpeed, true, "bee");
            images.add(bee);

            BitmapAnimation bird = new BitmapAnimation(parentScene, "Images/Enemies/bird", 7, assetStreamer, folderParser, enemyAnimationSpeed, true, "bird");
            images.add(bird);

            BitmapAnimation duck = new BitmapAnimation(parentScene, "Images/Enemies/duck", 7, assetStreamer, folderParser, enemyAnimationSpeed, true, "duck");
            images.add(duck);

            BitmapAnimation ghost = new BitmapAnimation(parentScene, "Images/Enemies/ghost", 7, assetStreamer, folderParser, enemyAnimationSpeed, true, "ghost");
            images.add(ghost);

            // Background
            BitmapSingle background = new BitmapSingle("Images/Background/Grassy_Mountains_preview_fullcolor.png", 11f, assetStreamer, "background");
            images.add(background);

            // User interface
            BitmapSingle pause = new BitmapSingle("Images/UI/pause_button.png", 1f, assetStreamer, "pause_button");
            images.add(pause);

            BitmapSingle pauseText = new BitmapSingle("Images/UI/pause_text.png", 1f, assetStreamer, "pause_text");
            images.add(pauseText);

        } catch (IOException e) {

            throw new RuntimeException("One or more folder paths are incorrect!", e);
        }
    }

}
