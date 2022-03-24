package com.cruntchy.suprseed.Engine.Collisions;

import android.graphics.RectF;

import com.cruntchy.suprseed.Engine.SpriteObjects.SpriteBase.Sprite;

public class SpriteRectangle implements RectangleCreator {

    @Override
    public void getRectF(RectF result, Sprite obj) {


        // TODO: VERIFY THAT THIS IS WORKING AS EXPECTED!

        result.left = obj.getX();
        result.right = result.left + obj.getImageHandler().getSelectedImageSet().getScaledWidth();

        result.top = obj.getY();
        result.bottom = result.top + obj.getImageHandler().getSelectedImageSet().getScaledHeight();
    }
}
