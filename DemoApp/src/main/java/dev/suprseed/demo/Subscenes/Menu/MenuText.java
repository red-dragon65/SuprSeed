package dev.suprseed.demo.Subscenes.Menu;

import android.content.Context;

import dev.suprseed.Engine.Core.MainView.GameProcessor.Render.Graphics.RenderHandler;
import dev.suprseed.Engine.Core.SpriteObjects.SpriteBase.Sprite;
import dev.suprseed.Engine.Lib.Fonts.FontHolder;
import dev.suprseed.demo.R;
import dev.suprseed.demo.Subscenes.LoadingScreen.LoadingScreenTextPainter;

public class MenuText extends Sprite {

    private FontHolder loadingScreenFont;

    private String text1 = "SuprSeed Demo";
    private int[] text1Loc = {10, 50};

    private String text2 = "Tap To Start";
    private int[] text2Loc = {15, 80};

    public MenuText(Context context) {
        super(null);

        loadingScreenFont = new FontHolder(R.font.peaberry_base, 10, context, new LoadingScreenTextPainter());

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


        // Set the location
        setX(text1Loc[0]);
        setY(text1Loc[1]);

        // Scale the location, then set the text
        float[] output = renderer.getCoordinateHandler().parseLocation(this);
        renderer.getCanvas().drawText(text1, output[0], output[1], renderer.getPaint());


        // Set the location
        setX(text2Loc[0]);
        setY(text2Loc[1]);

        // Scale the location, then set the text
        output = renderer.getCoordinateHandler().parseLocation(this);
        renderer.getCanvas().drawText(text2, output[0], output[1], renderer.getPaint());
    }
}
