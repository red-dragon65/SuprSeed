package com.cruntchy.suprseed.Engine.Lib.Fonts;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

public abstract class FontManager {

    private final List<FontHolder> fonts;

    // Constructor
    public FontManager(Context context) {

        fonts = new ArrayList<>();

        loadFonts(context);
    }

    protected abstract void loadFonts(Context context);


    public FontHolder getFont(String fontId) {

        for (FontHolder f : fonts) {

            if (f.getFontId().equals(fontId)) {

                return f;
            }
        }

        return null;
    }
}
