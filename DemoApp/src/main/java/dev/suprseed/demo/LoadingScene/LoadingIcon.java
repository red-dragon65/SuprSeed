package dev.suprseed.demo.LoadingScene;

import dev.suprseed.Engine.Core.Scenes.SceneHeirarchy.BaseScene;
import dev.suprseed.Engine.Core.SpriteObjects.SpriteBase.AssetBundle;
import dev.suprseed.Engine.Core.SpriteObjects.SpriteBase.PlayBackOptions;
import dev.suprseed.Engine.Core.SpriteObjects.SpriteBase.Sprite;
import dev.suprseed.Engine.Lib.Images.DefaultFPS;

public class LoadingIcon extends Sprite {

    public LoadingIcon(BaseScene parent, AssetBundle assetBundle) {
        super(assetBundle);

        // Set initial location
        setX(50 - (getWidth() / 2));
        setY(100);

        // Create and register an animation player
        assetBundle.getSelectedAsset()
                .createPlayer(parent, DefaultFPS._30.toInt())
                .setPlayOptions(PlayBackOptions.LOOP_FORWARD)
                .play();
    }

    @Override
    public void runLogic() {
        // Nothing to calculate here...
    }
}
