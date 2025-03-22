package dev.suprseed.demo.SplashScene;

import android.content.Context;

import dev.suprseed.Engine.Core.MainView.GameProcessor.Render.Graphics.RenderHandler;
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
import dev.suprseed.demo.ChangeSceneRequestDTO;
import dev.suprseed.demo.Shared.BackgroundSprite;
import dev.suprseed.demo.SplashScene.Sprites.FadeoutSprite;
import dev.suprseed.demo.SplashScene.Sprites.SplashLogoSprite;
import dev.suprseed.demo.SplashScene.Sprites.SplashTextSprite;
import dev.suprseed.demo.Utils.AlphaController;

public class SplashScene extends BaseScene {

    private int startingDelayMs = 1000;
    private int animationTimeMs = 500;
    private int postAnimationDelayMs = 2000;
    private long startTime = 0;
    private long currentTime = 0;

    private AlphaController textAlpha = new AlphaController(70);
    private AlphaController fadeOutAlpha = new AlphaController(100);

    private ChangeSceneRequestDTO changeSceneRequestDTO;

    public SplashScene(String sceneId, Context context, ChangeSceneRequestDTO changeSceneRequestDTO) {
        super(sceneId);

        this.changeSceneRequestDTO = changeSceneRequestDTO;

        Bundler<AssetBundle> assetBundler = buildBundler(context);

        Sprite background = new BackgroundSprite(assetBundler.generateAssetBundle("splashScreen"));
        registerSprite(background);

        // TODO: this should ideally be a lottie animation instead of a keyframe animation
        Sprite logo = new SplashLogoSprite(this, assetBundler.generateAssetBundle("splashLogo"));
        registerSprite(logo);

        Sprite text = new SplashTextSprite(assetBundler.generateAssetBundle("splashText"), textAlpha);
        registerSprite(text);

        FadeoutSprite fadeoutSprite = new FadeoutSprite(null, fadeOutAlpha);
        registerSprite(fadeoutSprite);
    }

    @Override
    public void generateNextFrame() {

        // Delay the logo animation
        if (currentTime > startTime + startingDelayMs) {
            super.generateNextFrame();
        }
    }

    @Override
    public void draw(RenderHandler renderer) {
        super.draw(renderer);

        // Initialize the time counter
        if (startTime == 0) {
            startTime = currentTime = System.currentTimeMillis();
        }

        // Update the time counter
        currentTime = System.currentTimeMillis();


        // wait x amount, then start animation
        if (currentTime > startTime + startingDelayMs) {

            if (!textAlpha.isStarted()) {
                textAlpha.toggleAnimation(true);
            }
        }

        // wait x amount for animation to complete
        if (currentTime > startTime + startingDelayMs + animationTimeMs) {

        }

        // wait x amount after animation before starting fadeout
        if (currentTime > startTime + startingDelayMs + animationTimeMs + postAnimationDelayMs) {

            if (!fadeOutAlpha.isStarted()) {
                fadeOutAlpha.toggleAnimation(true);
            }
        }

        if (fadeOutAlpha.isDone()) {
            changeSceneRequestDTO.notifyRequestChange();
        }
    }

    private Bundler<AssetBundle> buildBundler(Context context) {

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
        AssetLoadable<SpriteImage> splashAssets = new SplashAssets(this, localStreamer, localFolderParser);
        return new SafeAssetBundler(splashAssets, placeHolder);
    }
}
