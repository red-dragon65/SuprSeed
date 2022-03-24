package com.cruntchy.suprseed.Client.Z_ClientTest_Active.GameCode.Scenes.GameScene;

import com.cruntchy.suprseed.Client.Z_ClientTest_Active.GameCode.Sprites.BackgroundSprite.Background;
import com.cruntchy.suprseed.Client.Z_ClientTest_Active.GameCode.Sprites.HeroSprite.Hero;
import com.cruntchy.suprseed.Engine.AssetLoader.AssetLoader;
import com.cruntchy.suprseed.Engine.Images.SpriteImage;
import com.cruntchy.suprseed.Engine.MainView.GameProcessor.Loop.Scene;
import com.cruntchy.suprseed.Engine.MainView.GameProcessor.Loop.SceneController;
import com.cruntchy.suprseed.Engine.SpriteObjects.SpriteBase.ImageHandler;

public class MainScene extends Scene {


    public MainScene(SceneController sceneManager, String id, AssetLoader assets) {
        super(sceneManager, id);

        new Background(new ImageHandler("background", assets.getImage("background")));

        //new Hero(new ImageHandler("hero", assets.getImage("hero")));
        new Hero(new ImageHandler("hero", (SpriteImage) assets.getAnimation("hero")));
    }

    @Override
    public void runLogic() {

        // ignore this for now
    }
}
