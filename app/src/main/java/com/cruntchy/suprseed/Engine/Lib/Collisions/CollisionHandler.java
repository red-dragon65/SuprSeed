package com.cruntchy.suprseed.Engine.Lib.Collisions;

import com.cruntchy.suprseed.Engine.Core.SpriteObjects.SpriteBase.Sprite;

public interface CollisionHandler {

    boolean checkCollision(Sprite one, Sprite two);
}
