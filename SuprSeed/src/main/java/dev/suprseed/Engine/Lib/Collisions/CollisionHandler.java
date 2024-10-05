package dev.suprseed.Engine.Lib.Collisions;

import dev.suprseed.Engine.Core.SpriteObjects.SpriteBase.Sprite;

public interface CollisionHandler {

    boolean checkCollision(Sprite one, Sprite two);
}
