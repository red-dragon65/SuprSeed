package dev.suprseed.demo.Assets;

import java.io.IOException;

import dev.suprseed.Engine.Core.Scenes.SceneHeirarchy.BaseScene;
import dev.suprseed.Engine.Core.SpriteObjects.SpriteBase.SpriteImage;
import dev.suprseed.Engine.Lib.AssetLoader.AssetLoader;
import dev.suprseed.Engine.Lib.AssetLoader.FolderParser;
import dev.suprseed.Engine.Lib.AssetLoader.Streamable;
import dev.suprseed.Engine.Lib.Images.BitmapCollection;
import dev.suprseed.Engine.Lib.Images.BitmapSingle;

public class GameDemoAssets extends AssetLoader {


    public GameDemoAssets(BaseScene parentScene, Streamable assetStreamer, FolderParser folderParser) {
        super(parentScene, assetStreamer, folderParser);
    }


    @Override
    public void loadAssets(BaseScene parentScene) {

        // Client can load sprites images here

        try {
            // Hero
            SpriteImage heroLeft = new BitmapCollection("Images/Hero/Left", 10, assetStreamer, folderParser, "heroLeft");
            imageRegistry.add(heroLeft);

            SpriteImage heroRight = new BitmapCollection("Images/Hero/Right", 10, assetStreamer, folderParser, "heroRight");
            imageRegistry.add(heroRight);

            // Enemies
            SpriteImage bat = new BitmapCollection("Images/Enemies/bat", 7, assetStreamer, folderParser, "bat");
            imageRegistry.add(bat);

            SpriteImage bee = new BitmapCollection("Images/Enemies/bee", 7, assetStreamer, folderParser, "bee");
            imageRegistry.add(bee);

            SpriteImage bird = new BitmapCollection("Images/Enemies/bird", 7, assetStreamer, folderParser, "bird");
            imageRegistry.add(bird);

            SpriteImage duck = new BitmapCollection("Images/Enemies/duck", 7, assetStreamer, folderParser, "duck");
            imageRegistry.add(duck);

            SpriteImage ghost = new BitmapCollection("Images/Enemies/ghost", 7, assetStreamer, folderParser, "ghost");
            imageRegistry.add(ghost);

            // Background
            SpriteImage background = new BitmapSingle("Images/Background/Grassy_Mountains_preview_fullcolor.png", 11f, assetStreamer, "background");
            imageRegistry.add(background);

            // User interface
            SpriteImage pause = new BitmapSingle("Images/UI/pause_button.png", 1f, assetStreamer, "pause_button");
            imageRegistry.add(pause);

            SpriteImage pauseText = new BitmapSingle("Images/UI/pause_text.png", 1f, assetStreamer, "pause_text");
            imageRegistry.add(pauseText);

        } catch (IOException e) {

            throw new RuntimeException("One or more folder paths are incorrect!", e);
        }
    }

}
