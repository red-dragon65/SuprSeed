package dev.suprseed.demo.LoadingScene;

import java.io.IOException;

import dev.suprseed.Engine.Core.Scenes.SceneHeirarchy.BaseScene;
import dev.suprseed.Engine.Core.SpriteObjects.SpriteBase.SpriteImage;
import dev.suprseed.Engine.Lib.AssetLoader.AssetLoader;
import dev.suprseed.Engine.Lib.AssetLoader.FolderParser;
import dev.suprseed.Engine.Lib.AssetLoader.Streamable;
import dev.suprseed.Engine.Lib.Images.BitmapCollection;
import dev.suprseed.Engine.Lib.Images.BitmapSingle;

public class LoadingAssets extends AssetLoader {

    public LoadingAssets(BaseScene parentScene, Streamable assetStreamer, FolderParser folderParser) {
        super(parentScene, assetStreamer, folderParser);
    }

    @Override
    public void loadAssets(BaseScene parentScene) {

        try {
            // Loading images

            SpriteImage background = new BitmapSingle("Images/LoadingScreen/SuprSeed_LoadingScreen.png", 1.25f, assetStreamer, "loadingBackground");
            imageRegistry.add(background);

            SpriteImage spinningIcon = new BitmapCollection("Images/LoadingScreen/strawberry", 10, assetStreamer, folderParser, "spinningIcon");
            imageRegistry.add(spinningIcon);

        } catch (IOException e) {
            throw new RuntimeException("One or more folders paths are incorrect!");
        }
    }
}
