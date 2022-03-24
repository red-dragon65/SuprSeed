package com.cruntchy.suprseed.Engine.Collisions;

import android.graphics.RectF;

public interface RectCollector {

    void addRect(RectF input);

    void enable();

    void disable();
}
