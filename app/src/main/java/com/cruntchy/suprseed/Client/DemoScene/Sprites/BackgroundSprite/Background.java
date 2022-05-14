package com.cruntchy.suprseed.Client.DemoScene.Sprites.BackgroundSprite;

import com.cruntchy.suprseed.Engine.Core.Scenes.SceneHeirarchy.BaseScene;
import com.cruntchy.suprseed.Engine.Core.SpriteObjects.SpriteBase.ImageHandler;
import com.cruntchy.suprseed.Engine.Core.SpriteObjects.SpriteBase.Sprite;

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
