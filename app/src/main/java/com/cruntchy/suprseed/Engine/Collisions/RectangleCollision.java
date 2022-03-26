package com.cruntchy.suprseed.Engine.Collisions;

import android.graphics.RectF;

import com.cruntchy.suprseed.Engine.SpriteObjects.SpriteBase.Sprite;

public class RectangleCollision implements CollisionHandler {

    // Hold the collision bounds
    private static final RectF first = new RectF();
    private static final RectF second = new RectF();



    // Constructor
    public RectangleCollision() {
    }


    @Override
    public boolean checkCollision(Sprite one, Sprite two) {

        one.getRectF(first);
        two.getRectF(second);

        return first.intersects(second.left, second.top, second.right, second.bottom);
    }
}
