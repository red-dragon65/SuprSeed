package dev.suprseed.demo.Client.DemoScene.Sprites.HudSprite.HudFontPaintStrategies;

import android.graphics.Color;
import android.graphics.Paint;

import dev.suprseed.Engine.Lib.Fonts.FontPaintRoller;

public class ScorePaintStrategy extends FontPaintRoller {

    @Override
    public void updatePaint(Paint paint) {

        paint.setColor(Color.WHITE);
        paint.setTypeface(fontHolder.getFont());
        paint.setAntiAlias(true);
        paint.setTextSize(fontHolder.getFontSize());
    }
}
