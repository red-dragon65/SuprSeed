package dev.suprseed.Engine.Lib.Collisions;

import android.graphics.RectF;

import dev.suprseed.Engine.Core.SpriteObjects.SpriteBase.Sprite;

public class RectangleCollision implements CollisionHandler {

    // Hold the collision bounds
    private static final RectF first = new RectF();
    private static final RectF second = new RectF();


    @Override
    public boolean checkCollision(Sprite one, Sprite two) {

        one.getRectF(first);
        two.getRectF(second);

        return first.intersects(second.left, second.top, second.right, second.bottom);
    }
}
