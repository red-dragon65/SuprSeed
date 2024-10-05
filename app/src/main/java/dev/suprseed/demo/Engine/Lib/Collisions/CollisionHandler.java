package dev.suprseed.demo.Engine.Lib.Collisions;

import dev.suprseed.demo.Engine.Core.SpriteObjects.SpriteBase.Sprite;

public interface CollisionHandler {

    boolean checkCollision(Sprite one, Sprite two);
}
