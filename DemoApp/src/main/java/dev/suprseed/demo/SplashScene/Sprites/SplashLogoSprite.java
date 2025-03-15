package dev.suprseed.demo.SplashScene.Sprites;

import dev.suprseed.Engine.Core.Scenes.SceneHeirarchy.BaseScene;
import dev.suprseed.Engine.Core.SpriteObjects.SpriteBase.AssetBundle;
import dev.suprseed.Engine.Core.SpriteObjects.SpriteBase.PlayBackOptions;
import dev.suprseed.Engine.Core.SpriteObjects.SpriteBase.Sprite;

public class SplashLogoSprite extends Sprite {

    public SplashLogoSprite(BaseScene parent, AssetBundle assetBundle) {
        super(assetBundle);

        setX(50 - (getWidth() / 2));
        setY(40);

        // Play the splash screen animation
        assetBundle.getSelectedAsset()
                .createPlayer(parent, 30)
                .setPlayOptions(PlayBackOptions.PLAY_FORWARD)
                .play();
    }

    @Override
    public void runLogic() {

    }
}
