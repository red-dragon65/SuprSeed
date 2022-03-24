package com.cruntchy.suprseed.Engine.Collisions;

import android.graphics.RectF;

import com.cruntchy.suprseed.Engine.SpriteObjects.SpriteBase.Sprite;

public interface RectangleCreator {

    void getRectF(RectF result, Sprite obj);
}
