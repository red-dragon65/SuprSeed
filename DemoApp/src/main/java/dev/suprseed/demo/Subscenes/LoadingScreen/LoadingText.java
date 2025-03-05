package dev.suprseed.demo.Subscenes.LoadingScreen;

import android.content.Context;

import dev.suprseed.Engine.Core.MainView.GameProcessor.Render.Graphics.RenderHandler;
import dev.suprseed.Engine.Core.SpriteObjects.SpriteBase.Sprite;
import dev.suprseed.Engine.Lib.Fonts.FontHolder;
import dev.suprseed.demo.R;

public class LoadingText extends Sprite {

    private FontHolder loadingScreenFont;
    private String text = "Loading...";

    public LoadingText(Context context) {
        super(null);

        loadingScreenFont = new FontHolder(R.font.peaberry_base, 12, context, new LoadingScreenTextPainter());

        // Set drawing location
        setX(20);
        setY(80);
    }

    @Override
    public void runLogic() {
        // Nothing to calculate...
    }

    @Override
    public void draw(RenderHandler renderer) {

        // Set the font settings
        loadingScreenFont.updatePaint(renderer.getPaint());

        // Scale the location, then set the text
        float[] output = renderer.getCoordinateHandler().parseLocation(this);
        renderer.getCanvas().drawText(text, output[0], output[1], renderer.getPaint());
    }
}
