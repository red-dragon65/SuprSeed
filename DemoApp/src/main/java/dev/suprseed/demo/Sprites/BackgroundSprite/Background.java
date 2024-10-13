package dev.suprseed.demo.Sprites.BackgroundSprite;

import dev.suprseed.Engine.Core.Scenes.SceneHeirarchy.BaseScene;
import dev.suprseed.Engine.Core.SpriteObjects.SpriteBase.AssetBundle;
import dev.suprseed.Engine.Core.SpriteObjects.SpriteBase.Sprite;

public class Background extends Sprite {

    public Background(BaseScene parentScene, AssetBundle assetBundle) {
        super(parentScene, assetBundle);

        // Disable camera
        disableCamera();
    }

    @Override
    public void runLogic() {

    }
}
