package dev.suprseed.demo.Assets;

import dev.suprseed.Engine.Core.Scenes.SceneHeirarchy.BaseScene;
import dev.suprseed.Engine.Lib.AssetLoader.AssetLoader;
import dev.suprseed.Engine.Lib.AssetLoader.FolderParser;
import dev.suprseed.Engine.Lib.AssetLoader.Streamable;
import dev.suprseed.Engine.Lib.Images.BitmapAnimation;
import dev.suprseed.Engine.Lib.Images.BitmapSingle;

public class GameDemoAssets extends AssetLoader {


    public GameDemoAssets(BaseScene parentScene, Streamable assetStreamer, FolderParser folderParser) {
        super(parentScene, assetStreamer, folderParser);
    }


    @Override
    public void loadAssets(BaseScene parentScene) {

        // Client can load sprites images here


        // Hero
        BitmapAnimation hero = new BitmapAnimation(parentScene, "Images/Hero", 10, assetStreamer, folderParser, 2, true, "hero");
        images.add(hero);


        // Background
        BitmapSingle background = new BitmapSingle("Images/Background/Grassy_Mountains_preview_fullcolor.png", 11f, assetStreamer, "background");
        images.add(background);


        // Enemies
        int enemyAnimationSpeed = 15;

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


        // User interface
        BitmapSingle pause = new BitmapSingle("Images/UI/pause_button.png", 1f, assetStreamer, "pause_button");
        images.add(pause);

        BitmapSingle pauseText = new BitmapSingle("Images/UI/pause_text.png", 1f, assetStreamer, "pause_text");
        images.add(pauseText);
    }

}
