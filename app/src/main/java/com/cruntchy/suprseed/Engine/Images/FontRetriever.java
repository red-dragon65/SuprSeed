package com.cruntchy.suprseed.Engine.Images;

import android.graphics.Typeface;

public interface FontRetriever<T> {

    Typeface getFont();
    float getFontSize();
    T getFontId();
}
