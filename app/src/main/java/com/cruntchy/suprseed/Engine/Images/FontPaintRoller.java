package com.cruntchy.suprseed.Engine.Images;

import android.graphics.Paint;


public abstract class FontPaintRoller {

    protected FontHolder fontHolder;

    protected abstract void updatePaint(Paint paint);

    public void setFontHolder(FontHolder fontHolder) {
        this.fontHolder = fontHolder;
    }
}
