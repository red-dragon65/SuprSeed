package com.cruntchy.suprseed.Client.DemoScene.Sprites.HudSprite.HudFontPaintStrategies;

import android.graphics.Color;
import android.graphics.Paint;

import com.cruntchy.suprseed.Engine.Lib.Fonts.FontPaintRoller;

public class GameOverPaintStrategy extends FontPaintRoller {

    @Override
    protected void updatePaint(Paint paint) {

        paint.setColor(Color.WHITE);
        paint.setTypeface(fontHolder.getFont());
        paint.setAntiAlias(true);
        paint.setTextSize(fontHolder.getFontSize());
    }
}
