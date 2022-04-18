package com.cruntchy.suprseed.Client.Scene1.Sprites.Obstacles;

import com.cruntchy.suprseed.Engine.Scenes.SceneHeirarchy.BaseScene;
import com.cruntchy.suprseed.Engine.SpriteObjects.SpriteBase.ImageHandler;
import com.cruntchy.suprseed.Engine.SpriteObjects.SpriteBase.Sprite;

public class ObstacleSprite extends Sprite {

    public ObstacleSprite(BaseScene parentScene, ImageHandler imageHandler) {
        super(parentScene, imageHandler);
    }

    @Override
    public void runLogic() {

        // Update to new location values
        setX(getX() + getxVel());
        setY(getY() + getyVel());
    }
}
