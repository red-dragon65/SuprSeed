package dev.suprseed.Engine.Lib.Fonts;

import dev.suprseed.Engine.Core.MainView.GameProcessor.Render.Graphics.RenderHandler;
import dev.suprseed.Engine.Core.SpriteObjects.SpriteBase.Sprite;

public class FontSprite extends Sprite {

    protected String text;
    protected FontHolder fontHolder;

    public FontSprite(String text, float xLoc, float yLoc, FontHolder fontHolder) {
        super(null);
        this.text = text;
        setX(xLoc);
        setY(yLoc);
        this.fontHolder = fontHolder;
    }

    @Override
    public void runLogic() {

    }

    @Override
    public void draw(RenderHandler renderer) {

        // Scale the location, then set the text
        FontPaintRoller.renderFont(renderer, this, text, fontHolder);
    }
}
