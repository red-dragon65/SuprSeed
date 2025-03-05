package dev.suprseed.demo.Assets;

import java.io.IOException;

import dev.suprseed.Engine.Core.Scenes.SceneHeirarchy.BaseScene;
import dev.suprseed.Engine.Core.SpriteObjects.SpriteBase.SpriteImage;
import dev.suprseed.Engine.Lib.AssetLoader.AssetLoader;
import dev.suprseed.Engine.Lib.AssetLoader.FolderParser;
import dev.suprseed.Engine.Lib.AssetLoader.Streamable;
import dev.suprseed.Engine.Lib.Images.BitmapSingle;

public class MenuAssets extends AssetLoader {

    public MenuAssets(BaseScene parentScene, Streamable assetStreamer, FolderParser folderParser) {
        super(parentScene, assetStreamer, folderParser);
    }

    @Override
    public void loadAssets(BaseScene parentScene) {

        try {
            // Loading images

            SpriteImage background = new BitmapSingle("Images/Menu/menu_background.png", 2.82f, assetStreamer, "menu_background");
            imageRegistry.add(background);

            SpriteImage playButton = new BitmapSingle("Images/Menu/menu_play.png", 11f, assetStreamer, "play_button");
            imageRegistry.add(playButton);

        } catch (IOException e) {
            throw new RuntimeException("One or more folders paths are incorrect!");
        }
    }
}
