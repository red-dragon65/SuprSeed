package com.cruntchy.suprseed.Client.Z_ClientTest_Active.GameCode.Scenes;

import com.cruntchy.suprseed.Client.Z_ClientTest_Active.GameCode.HeroSprite.Hero;
import com.cruntchy.suprseed.Engine.AssetLoader.AssetLoader;
import com.cruntchy.suprseed.Engine.MainView.GameProcessor.Loop.Scene;
import com.cruntchy.suprseed.Engine.MainView.GameProcessor.Loop.SceneController;
import com.cruntchy.suprseed.Engine.SpriteObjects.SpriteBase.ImageHandler;

public class TestScene extends Scene {


    public TestScene(SceneController sceneManager, String id, AssetLoader assets) {
        super(sceneManager, id);

        new Hero(new ImageHandler("hero", assets.getImage("hero")));
    }

    @Override
    public void runLogic() {

        // ignore this for now
    }
}
