package dev.suprseed.Engine.Lib.Collisions;

import android.graphics.RectF;

public interface RectCollector {

    void addRect(RectF input);

    void enable();

    void disable();
}
