package dev.suprseed.demo.SplashScene;

import java.io.IOException;

import dev.suprseed.Engine.Core.Scenes.SceneHeirarchy.BaseScene;
import dev.suprseed.Engine.Core.SpriteObjects.SpriteBase.SpriteImage;
import dev.suprseed.Engine.Lib.AssetLoader.AssetLoader;
import dev.suprseed.Engine.Lib.AssetLoader.FolderParser;
import dev.suprseed.Engine.Lib.AssetLoader.Streamable;
import dev.suprseed.Engine.Lib.Images.BitmapCollection;
import dev.suprseed.Engine.Lib.Images.BitmapSingle;

public class SplashAssets extends AssetLoader {

    public SplashAssets(BaseScene parentScene, Streamable assetStreamer, FolderParser folderParser) {
        super(parentScene, assetStreamer, folderParser);
    }

    @Override
    public void loadAssets(BaseScene parentScene) {

        try {
            // Splash images

            SpriteImage background = new BitmapSingle("Images/SplashScreen/SuprSeed_SplashScreen.png", 1.25f, assetStreamer, "splashScreen");
            imageRegistry.add(background);

            SpriteImage spinningLogo = new BitmapCollection("Images/SplashScreen/logo-animation", 2f, assetStreamer, folderParser, "splashLogo");
            imageRegistry.add(spinningLogo);

            SpriteImage text = new BitmapSingle("Images/SplashScreen/splash-text.png", 1f, assetStreamer, "splashText");
            imageRegistry.add(text);

        } catch (IOException e) {
            throw new RuntimeException("One or more folders paths are incorrect!");
        }
    }
}
