package dev.suprseed.demo.LoadingScene;

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
import dev.suprseed.Engine.Lib.Fonts.FontHolder;
import dev.suprseed.Engine.Lib.Fonts.FontSprite;
import dev.suprseed.Engine.Lib.Images.PlaceHolder;
import dev.suprseed.demo.R;
import dev.suprseed.demo.Shared.BackgroundSprite;
import dev.suprseed.demo.Utils.GameFontPainter;

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
        AssetLoadable<SpriteImage> loadingAssets = new LoadingAssets(this, localStreamer, localFolderParser);
        Bundler<AssetBundle> assetBundler = new SafeAssetBundler(loadingAssets, placeHolder);


        // Load in sprites here
        Sprite background = new BackgroundSprite(assetBundler.generateAssetBundle("loadingBackground"));
        registerSprite(background);

        registerSprite(new LoadingIcon(this, assetBundler.generateAssetBundle("spinningIcon")));

        FontHolder fontHolder = new FontHolder(R.font.peaberry_base, 12, context, new GameFontPainter());
        registerSprite(new FontSprite("Loading...", 20, 80, fontHolder));
    }
}
