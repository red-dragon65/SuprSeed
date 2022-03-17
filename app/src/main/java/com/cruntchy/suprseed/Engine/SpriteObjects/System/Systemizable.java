package com.cruntchy.suprseed.Engine.SpriteObjects.System;

import com.cruntchy.suprseed.Engine.SpriteObjects.SpriteBase.Sprite;

public interface Systemizable {

    void registerLogicSprite(Logic sprite);

    void registerRenderSprite(Sprite sprite);
}
