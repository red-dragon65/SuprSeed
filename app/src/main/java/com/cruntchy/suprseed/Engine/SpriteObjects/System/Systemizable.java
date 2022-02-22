package com.cruntchy.suprseed.Engine.SpriteObjects.System;

import com.cruntchy.suprseed.Engine.SpriteObjects.SpriteBase.Sprite;
import com.cruntchy.suprseed.Engine.SpriteObjects.SpriteExtensions.Collidable;
import com.cruntchy.suprseed.Engine.SpriteObjects.SpriteExtensions.Logic;
import com.cruntchy.suprseed.Engine.SpriteObjects.SpriteExtensions.Movable;

public interface Systemizable {

    public void registerCollisionSprite(Collidable sprite);
    public void registerLogicSprite(Logic sprite);
    public void registerMovingSprite(Movable sprite);
    public void registerRenderSprite(Sprite sprite);
}
