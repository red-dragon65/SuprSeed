package dev.suprseed.demo.Subscenes.LoadingScreen;

import android.content.Context;

import dev.suprseed.Engine.Core.Scenes.SceneHeirarchy.BaseScene;
import dev.suprseed.Engine.Core.SpriteObjects.SpriteBase.AssetBundle;
import dev.suprseed.Engine.Core.SpriteObjects.SpriteBase.Sprite;
import dev.suprseed.Engine.Core.SpriteObjects.SpriteBase.SpriteImage;
import dev.suprseed.Engine.Lib.AssetLoader.AssetLoadable;
import dev.suprseed.Engine.Lib.AssetLoader.Bundler;
import dev.suprseed.Engine.Lib.AssetLoader.FolderParser;
import dev.suprseed.Engine.Lib.AssetLoader.LocalFolderParser;
import dev.suprseed.Engine.Lib.AssetLoader.LocalImageFileStreamer;
import dev.suprseed.Engine.Lib.AssetLoader.SafeAssetBundler;
import dev.suprseed.Engine.Lib.AssetLoader.Streamable;
import dev.suprseed.Engine.Lib.Images.PlaceHolder;
import dev.suprseed.demo.Assets.LoadingAssets;
import dev.suprseed.demo.Sprites.BackgroundSprite.Background;

public class GameLoadSpinner extends BaseScene {

    public GameLoadSpinner(String sceneId, Context context) {
        super(sceneId);

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

        // Create the asset loader and bundler
        AssetLoadable<SpriteImage> gamePlayAssets = new LoadingAssets(this, localStreamer, localFolderParser);
        Bundler<AssetBundle> assetBundler = new SafeAssetBundler(gamePlayAssets, placeHolder);


        // Load in sprites here
        Sprite background = new Background(assetBundler.generateAssetBundle("loadingBackground"));
        registerSprite(background);
        background.setAllowCollisionDiagnostic(false);

        registerSprite(new LoadingIcon(this, assetBundler.generateAssetBundle("spinningIcon")));

        registerSprite(new LoadingText(context));
    }
}
