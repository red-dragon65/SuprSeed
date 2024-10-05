package dev.suprseed.demo.Client.DemoScene.Sprites.BackgroundSprite;

import dev.suprseed.Engine.Core.Scenes.SceneHeirarchy.BaseScene;
import dev.suprseed.Engine.Core.SpriteObjects.SpriteBase.ImageHandler;
import dev.suprseed.Engine.Core.SpriteObjects.SpriteBase.Sprite;

public class Background extends Sprite {

    public Background(BaseScene parentScene, ImageHandler imageHandler) {
        super(parentScene, imageHandler);

        // Disable camera
        disableCamera();
    }

    @Override
    public void runLogic() {

    }
}
