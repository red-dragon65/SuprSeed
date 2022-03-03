package com.cruntchy.suprseed.Client.Z_ClientTest_Active.GameCode.HeroSprite;

import com.cruntchy.suprseed.Engine.SpriteObjects.SpriteBase.ImageHandler;
import com.cruntchy.suprseed.Engine.SpriteObjects.SpriteBase.Sprite;
import com.cruntchy.suprseed.Engine.SpriteObjects.SpriteExtensions.Logic;
import com.cruntchy.suprseed.Engine.SpriteObjects.SpriteExtensions.Movable;

public class Hero extends Sprite implements Logic, Movable {



    public Hero(ImageHandler imageHandler) {
        super(imageHandler);

        // Register this to the system
        spriteSystem.registerLogicSprite(this);
        spriteSystem.registerMovingSprite(this);

        // Initialize starting state
        this.setX(0); // This should be half the width of the screen
        this.setY(0); // This should be half the width of the screen from the top

        this.setxVel(0.4f); // This should move from the left side of the screen to the right in about 4 seconds
        this.setyVel(0.8f);
    }

    @Override
    public void runLogic() {

        float width = getImageHandler().getSelectedImageSet().getScaledWidth();
        float height = getImageHandler().getSelectedImageSet().getScaledHeight();

        if (this.getX() > getCanvasScaledWidth() - width) { // This should see if the sprite moves off the right side of the screen

            setxVel(-getxVel());

        } else if (getX() < 0) {

            setxVel(-getxVel());
        }


        if (this.getY() > getCanvasScaledHeight() - height) {

            setyVel(-getyVel());

        } else if (getY() < 0) {

            setyVel(-getyVel());
        }


    }

    @Override
    public void move() {

        // Update location based on velocity
        setX(getX() + getxVel());
        setY(getY() + getyVel());
    }
}
