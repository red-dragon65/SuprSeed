package dev.suprseed.demo.Sprites.BackgroundSprite;

import dev.suprseed.Engine.Core.SpriteObjects.SpriteBase.AssetBundle;
import dev.suprseed.Engine.Core.SpriteObjects.SpriteBase.Sprite;

public class Background extends Sprite {

    public Background(AssetBundle assetBundle) {
        super(assetBundle);

        // Disable collision debugging
        setAllowCollisionDiagnostic(false);

        // Disable camera
        disableCamera();
    }

    @Override
    public void runLogic() {

    }
}
