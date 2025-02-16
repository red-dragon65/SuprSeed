package dev.suprseed.demo.Sprites.Obstacles;

import dev.suprseed.Engine.Core.Scenes.SceneHeirarchy.BaseScene;
import dev.suprseed.Engine.Core.SpriteObjects.SpriteBase.AssetBundle;
import dev.suprseed.Engine.Core.SpriteObjects.SpriteBase.Sprite;

public class ObstacleSprite extends Sprite {

    public ObstacleSprite(BaseScene parentScene, AssetBundle assetBundle) {
        super(parentScene, assetBundle);
    }

    @Override
    public void runLogic() {

        // Disable this if it goes above the top of the screen
        if (getY() + getHeight() < 0) {

            setDrawable(false);
            setActive(false);
        }


        // Update to new location values
        applyXVel();
        applyYVel();
    }
}
