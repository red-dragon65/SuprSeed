package com.cruntchy.suprseed.Client.Z_ClientTest_Active.GameCode.Sprites.HudSprite;

import android.content.Context;
import android.graphics.Color;

import com.cruntchy.suprseed.Engine.Images.FontHolder;
import com.cruntchy.suprseed.Engine.Images.FontRetriever;
import com.cruntchy.suprseed.Engine.MainView.EngineSettings.BaseConfig;
import com.cruntchy.suprseed.Engine.MainView.EngineSettings.CanvasConfig;
import com.cruntchy.suprseed.Engine.MainView.GameProcessor.Render.Coordinates.CartesianHandler;
import com.cruntchy.suprseed.Engine.MainView.GameProcessor.Render.Coordinates.CoordinateHandler;
import com.cruntchy.suprseed.Engine.MainView.GameProcessor.Render.Coordinates.CoordinateProcessor;
import com.cruntchy.suprseed.Engine.MainView.GameProcessor.Render.Coordinates.LocationHandler;
import com.cruntchy.suprseed.Engine.MainView.GameProcessor.Render.Coordinates.LocationScaler;
import com.cruntchy.suprseed.Engine.MainView.GameProcessor.Render.Coordinates.LocationTemporalScaler;
import com.cruntchy.suprseed.Engine.MainView.GameProcessor.Render.Graphics.RenderHandler;
import com.cruntchy.suprseed.Engine.Scenes.SceneHeirarchy.BaseScene;
import com.cruntchy.suprseed.Engine.SpriteObjects.SpriteBase.Sprite;
import com.cruntchy.suprseed.Engine.SpriteObjects.System.Logic;
import com.cruntchy.suprseed.R;

public class ScoreSprite extends Sprite implements Logic {

    private FontRetriever<String> scoreFont;
    private String text = "-- Frog Dance --";

    public ScoreSprite(BaseScene parentScene, Context context) {
        super(parentScene, null);

        scoreFont = new FontHolder(R.font.peaberry_base, 5, context);
        disableCamera();


        // TODO: Set the correct location!
        //  Calculate text offset value to center text!

        /*
        8 chars = ~20 units
        size 50 = ~20 units
         */


        setX(30); // Should be middle of screen
        setY(10); // Reasonable margin from top
    }

    @Override
    public void runLogic() {

    }

    @Override
    public void draw(RenderHandler renderer) {

        // Don't draw the 'image handler!' It is null...
        //super.draw(renderer);


        // Draw the score text here


        // Set the font
        renderer.getPaint().setColor(Color.GREEN);
        renderer.getPaint().setTypeface(scoreFont.getFont());
        renderer.getPaint().setAntiAlias(true);
        renderer.getPaint().setTextSize(scoreFont.getFontSize());

        float[] output = renderer.getCoordinateHandler().parseLocation(this);

        // TODO: Get the correctly scaled location!
        // Draw the font
        renderer.getCanvas().drawText(text, output[0], output[1], renderer.getPaint());

    }
}
