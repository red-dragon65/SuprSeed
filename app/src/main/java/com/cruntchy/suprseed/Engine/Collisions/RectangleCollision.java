package com.cruntchy.suprseed.Engine.Collisions;

import android.graphics.RectF;

import com.cruntchy.suprseed.Engine.SpriteObjects.SpriteBase.Sprite;

public class RectangleCollision implements CollisionHandler {

    // Hold the collision bounds
    private static final RectF first = new RectF();
    private static final RectF second = new RectF();

    // Dependency injection
    private final RectangleCreator rector;


    // Constructor
    public RectangleCollision(RectangleCreator rector) {

        this.rector = rector;
    }


    @Override
    public boolean checkCollision(Sprite one, Sprite two) {

        rector.getRectF(first, one);
        rector.getRectF(second, two);

        return first.intersects(second.left, second.top, second.right, second.bottom);
    }
}
