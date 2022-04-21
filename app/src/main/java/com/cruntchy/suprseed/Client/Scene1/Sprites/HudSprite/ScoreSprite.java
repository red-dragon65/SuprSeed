package com.cruntchy.suprseed.Client.Scene1.Sprites.HudSprite;

import android.content.Context;
import android.graphics.Color;

import com.cruntchy.suprseed.Engine.Images.FontHolder;
import com.cruntchy.suprseed.Engine.Images.FontRetriever;
import com.cruntchy.suprseed.Engine.MainView.GameProcessor.Render.Graphics.RenderHandler;
import com.cruntchy.suprseed.Engine.Scenes.SceneHeirarchy.BaseScene;
import com.cruntchy.suprseed.Engine.SpriteObjects.SpriteBase.Sprite;
import com.cruntchy.suprseed.Engine.SpriteObjects.System.Logic;
import com.cruntchy.suprseed.R;

public class ScoreSprite extends Sprite implements Logic {

    private FontRetriever<String> scoreFont;
    private String text = "Score: 0000";

    public ScoreSprite(BaseScene parentScene, Context context) {
        super(parentScene, null);

        scoreFont = new FontHolder(R.font.peaberry_base, 10, context);
        disableCamera();


        // TODO: Set the correct location!
        //  Calculate text offset value to center text!

        /*
        FontSize: 5
        8 chars = ~20 units
        size 50 = ~20 units
         */


        setX(5); // Should be middle of screen
        setY(20); // Reasonable margin from top
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
        renderer.getPaint().setColor(Color.WHITE);
        renderer.getPaint().setTypeface(scoreFont.getFont());
        renderer.getPaint().setAntiAlias(true);
        renderer.getPaint().setTextSize(scoreFont.getFontSize());

        float[] output = renderer.getCoordinateHandler().parseLocation(this);

        // TODO: Get the correctly scaled location!
        // TODO: Make 'RenderProcessor' draw text! This allows the camera to offset the text.
        // Draw the font
        renderer.getCanvas().drawText(text, output[0], output[1], renderer.getPaint());

    }
}
