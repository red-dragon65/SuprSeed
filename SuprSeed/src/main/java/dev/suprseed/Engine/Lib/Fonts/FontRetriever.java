package dev.suprseed.Engine.Lib.Fonts;

import android.graphics.Typeface;

public interface FontRetriever<T> {

    Typeface getFont();

    float getFontSize();

    T getFontId();
}
