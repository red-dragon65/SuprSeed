package dev.suprseed.demo.MenuScene;

import android.content.Context;

import dev.suprseed.Engine.Core.Scenes.SceneHeirarchy.SceneManager;
import dev.suprseed.Engine.Core.SpriteObjects.SpriteBase.AssetBundle;
import dev.suprseed.Engine.Core.SpriteObjects.SpriteBase.Sprite;
import dev.suprseed.Engine.Core.SpriteObjects.SpriteBase.SpriteImage;
import dev.suprseed.Engine.EngineTools;
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
import dev.suprseed.Engine.Lib.Input.InputListener;
import dev.suprseed.demo.ChangeSceneRequestDTO;
import dev.suprseed.demo.R;
import dev.suprseed.demo.Shared.BackgroundSprite;
import dev.suprseed.demo.Utils.GameFontPainter;

public class MenuScene extends SceneManager {

    private InputListener fullscreenInputListener;
    private ChangeSceneRequestDTO changeSceneRequestDTO;

    public MenuScene(String sceneId, ChangeSceneRequestDTO changeSceneRequestDTO, Context context) {
        super(sceneId);

        this.changeSceneRequestDTO = changeSceneRequestDTO;

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
        AssetLoadable<SpriteImage> menuAssets = new MenuAssets(this, localStreamer, localFolderParser);
        Bundler<AssetBundle> assetBundler = new SafeAssetBundler(menuAssets, placeHolder);


        Sprite background = new BackgroundSprite(assetBundler.generateAssetBundle("menu_background"));
        registerSprite(background);

        Sprite playButton = new Sprite(assetBundler.generateAssetBundle("play_button")) {
            @Override
            public void runLogic() {
                // Do nothing
            }
        };
        playButton.setX(50 - playButton.getWidth() / 2);
        playButton.setY(100);

        registerSprite(playButton);

        FontHolder fontHolder = new FontHolder(R.font.peaberry_base, 10, context, new GameFontPainter());
        registerSprite(new FontSprite("SuprSeed Demo", 10, 50, fontHolder));
        registerSprite(new FontSprite("Tap To Start", 15, 80, fontHolder));
    }

    @Override
    public void onPost() {

        // Load the input after the object is created to avoid it from being active while
        // the loading screen is still showing
        fullscreenInputListener = new FullScreenMenuTouchInput(changeSceneRequestDTO);
        EngineTools.getInputManager().getListenerRegistry().registerObject(fullscreenInputListener);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        // Remove the touch listener
        EngineTools.getInputManager().getListenerRegistry().removeObject(fullscreenInputListener);
    }
}