package com.cruntchy.suprseed.Client.Scene1.Sprites.BackgroundSprite;

import com.cruntchy.suprseed.Engine.Scenes.SceneHeirarchy.BaseScene;
import com.cruntchy.suprseed.Engine.SpriteObjects.SpriteBase.ImageHandler;
import com.cruntchy.suprseed.Engine.SpriteObjects.SpriteBase.Sprite;

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
