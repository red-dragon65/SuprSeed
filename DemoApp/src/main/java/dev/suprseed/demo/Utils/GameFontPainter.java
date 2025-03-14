package dev.suprseed.demo.Utils;

import android.graphics.Color;
import android.graphics.Paint;

import dev.suprseed.Engine.Lib.Fonts.FontPaintRoller;

public class GameFontPainter extends FontPaintRoller {

    @Override
    protected void updatePaint(Paint paint) {

        paint.setColor(Color.WHITE);
        paint.setTypeface(fontHolder.getFont());
        paint.setAntiAlias(true);
        paint.setTextSize(fontHolder.getFontSize());
    }
}
