package dev.suprseed.demo.Subscenes.LoadingScreen;

import dev.suprseed.Engine.Core.SpriteObjects.SpriteBase.AssetBundle;
import dev.suprseed.Engine.Core.SpriteObjects.SpriteBase.Sprite;

public class LoadingIcon extends Sprite {

    public LoadingIcon(AssetBundle assetBundle) {
        super(assetBundle);

        // Set initial location
        setX(50 - (getWidth() / 2));
        setY(100);
    }

    @Override
    public void runLogic() {
        // Nothing to calculate here...
    }
}
