package com.cruntchy.suprseed.Engine.Collisions;

import com.cruntchy.suprseed.Engine.SpriteObjects.SpriteBase.Sprite;

public interface CollisionHandler {

    boolean checkCollision(Sprite one, Sprite two);
}
