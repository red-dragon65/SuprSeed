package com.cruntchy.suprseed.Engine.Collisions;

import android.graphics.RectF;

import com.cruntchy.suprseed.Engine.SpriteObjects.SpriteBase.Sprite;

public class RectangleOffsetCollision implements CollisionHandler {

    // Hold the collision bounds
    private static final RectF first = new RectF();
    private static final RectF second = new RectF();

    private final int xOffset;
    private final int yOffset;


    // Constructor
    public RectangleOffsetCollision(int xOffset, int yOffset) {

        this.xOffset = xOffset;
        this.yOffset = yOffset;
    }


    @Override
    public boolean checkCollision(Sprite one, Sprite two) {

        one.getRectF(first);
        two.getRectF(second);

        first.offset(xOffset, yOffset);
        second.offset(xOffset, yOffset);

        return first.intersects(second.left, second.top, second.right, second.bottom);
    }
}
