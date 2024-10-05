package dev.suprseed.demo.Sprites.Obstacles;

import dev.suprseed.Engine.Core.Scenes.SceneHeirarchy.BaseScene;
import dev.suprseed.Engine.Core.SpriteObjects.SpriteBase.ImageHandler;
import dev.suprseed.Engine.Core.SpriteObjects.SpriteBase.Sprite;

public class ObstacleSprite extends Sprite {

    public ObstacleSprite(BaseScene parentScene, ImageHandler imageHandler) {
        super(parentScene, imageHandler);
    }

    @Override
    public void runLogic() {

        // Disable this if it goes above the top of the screen
        if(getY() + imageHandler.getSelectedImageSet().getScaledHeight() < 0){

            setDrawable(false);
            setActive(false);
        }


        // Update to new location values
        setX(getX() + getxVel());
        setY(getY() + getyVel());
    }
}
