package com.cruntchy.suprseed.Client.Z_ClientTest_Active.GameCode.Sprites.HeroSprite;

import android.graphics.RectF;
import android.view.MotionEvent;

import com.cruntchy.suprseed.Engine.InputHandler.TouchInput.InputListener;
import com.cruntchy.suprseed.Engine.InputHandler.TouchInput.InputManager;
import com.cruntchy.suprseed.Engine.SpriteObjects.DefaultComponents.Movable;
import com.cruntchy.suprseed.Engine.SpriteObjects.SpriteBase.Sprite;

public class VelocityMovementComponent implements Movable, InputListener {

    private final Sprite sprite;
    private boolean move = true;

    // Constructor
    public VelocityMovementComponent(Sprite sprite) {

        this.sprite = sprite;
        InputManager.getInstance().listenerRegister.registerObject(this);
    }


    @Override
    public void move() {

        if (move) {
            // Update location based on velocity
            sprite.setX(sprite.getX() + sprite.getxVel());
            sprite.setY(sprite.getY() + sprite.getyVel());
        }
    }

    @Override
    public void getRectF(RectF result) {

        sprite.getRectF(result);
    }

    @Override
    public void processInput(String action, MotionEvent event) {

        if (action.equals("tap")) {
            move = !move;
        }
    }

    @Override
    public int getLayerDepth() {
        return sprite.getLayerDepth();
    }
}
