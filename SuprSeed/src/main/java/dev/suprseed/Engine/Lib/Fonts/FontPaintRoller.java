package dev.suprseed.Engine.Lib.Fonts;

import android.graphics.Paint;

import dev.suprseed.Engine.Core.MainView.GameProcessor.Render.Graphics.RenderHandler;
import dev.suprseed.Engine.Core.SpriteObjects.SpriteBase.Sprite;


public abstract class FontPaintRoller {

    protected FontHolder fontHolder;

    public static void renderFont(RenderHandler renderHandler, Sprite sprite, String text, FontHolder fontHolder) {

        // Set the font settings
        fontHolder.updatePaint(renderHandler.getPaint());

        // Scale the location, then set the text
        float[] output = renderHandler.getCoordinateHandler().parseLocation(sprite);
        renderHandler.getCanvas().drawText(text, output[0], output[1], renderHandler.getPaint());
    }

    protected abstract void updatePaint(Paint paint);

    public void setFontHolder(FontHolder fontHolder) {
        this.fontHolder = fontHolder;
    }
}
