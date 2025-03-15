package dev.suprseed.demo.Shared;

import dev.suprseed.Engine.Core.SpriteObjects.SpriteBase.AssetBundle;
import dev.suprseed.Engine.Core.SpriteObjects.SpriteBase.Sprite;

public class BackgroundSprite extends Sprite {

    public BackgroundSprite(AssetBundle assetBundle) {
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
