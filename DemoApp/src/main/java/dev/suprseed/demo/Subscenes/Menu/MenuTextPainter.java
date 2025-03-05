package dev.suprseed.demo.Subscenes.Menu;

import android.graphics.Color;
import android.graphics.Paint;

import dev.suprseed.Engine.Lib.Fonts.FontPaintRoller;

public class MenuTextPainter extends FontPaintRoller {

    @Override
    protected void updatePaint(Paint paint) {

        paint.setColor(Color.WHITE);
        paint.setColor(Color.WHITE);
        paint.setTypeface(fontHolder.getFont());
        paint.setAntiAlias(true);
        paint.setTextSize(fontHolder.getFontSize());
    }
}
