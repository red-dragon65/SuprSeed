package com.cruntchy.suprseed.Client.Z_ClientTest_Active.GameCode.HeroSprite;

import com.cruntchy.suprseed.Engine.SpriteObjects.DefaultComponents.Movable;
import com.cruntchy.suprseed.Engine.SpriteObjects.SpriteBase.Sprite;

public class VelocityMovementComponent implements Movable {

    private final Sprite sprite;


    // Constructor
    public VelocityMovementComponent(Sprite sprite) {

        this.sprite = sprite;
    }


    @Override
    public void move() {

        // Update location based on velocity
        sprite.setX(sprite.getX() + sprite.getxVel());
        sprite.setY(sprite.getY() + sprite.getyVel());
    }
}
