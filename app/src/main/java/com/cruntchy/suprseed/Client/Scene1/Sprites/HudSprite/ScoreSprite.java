package com.cruntchy.suprseed.Client.Scene1.Sprites.HudSprite;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;

import com.cruntchy.suprseed.Client.Scene1.Data.BounceData;
import com.cruntchy.suprseed.Engine.Images.FontHolder;
import com.cruntchy.suprseed.Engine.Images.FontRetriever;
import com.cruntchy.suprseed.Engine.MainView.GameProcessor.Render.Graphics.RenderHandler;
import com.cruntchy.suprseed.Engine.Scenes.SceneHeirarchy.BaseScene;
import com.cruntchy.suprseed.Engine.SpriteObjects.SpriteBase.Sprite;
import com.cruntchy.suprseed.Engine.SpriteObjects.System.Logic;
import com.cruntchy.suprseed.R;

public class ScoreSprite extends Sprite implements Logic {

    private final FontRetriever<String> scoreFont;
    private final String text = "Score: ";
    private final BounceData bounceData;
    private final SharedPreferences gameData;
    private int scoreCounter = 0;
    private boolean saved = false;

    public ScoreSprite(BaseScene parentScene, BounceData bounceData) {
        super(parentScene, null);

        this.bounceData = bounceData;

        this.scoreFont = new FontHolder(R.font.peaberry_base, 10, parentScene.getContext());

        // Load saved score value
        this.gameData = parentScene.getContext().getSharedPreferences("GAME_DATA", Context.MODE_PRIVATE);

        // TODO: Load everytime user dies and restarts
        // TODO: Reset counter if when user begins game
        loadScore();

        // Set text margins
        setX(5); // Reasonable margin from left
        setY(20); // Reasonable margin from top
    }

    @Override
    public void runLogic() {

        if (bounceData.getBounceValue() != 0) {

            // TODO: Dynamically calculate this using the logic rate!
            //  Otherwise value will increase faster if logic rate is changed!
            scoreCounter++;

            saved = false;

        } else if (!saved) {

            // Save data
            saveScore();
            saved = true;
        }
    }

    @Override
    public void draw(RenderHandler renderer) {

        // Don't draw the 'image handler!' It is null...
        //super.draw(renderer);

        // Draw the score text here instead


        // Set the font
        renderer.getPaint().setColor(Color.WHITE);
        renderer.getPaint().setTypeface(scoreFont.getFont());
        renderer.getPaint().setAntiAlias(true);
        renderer.getPaint().setTextSize(scoreFont.getFontSize());

        // Scale the location
        float[] output = renderer.getCoordinateHandler().parseLocation(this);

        // Draw the font
        renderer.getCanvas().drawText(text + scoreCounter, output[0], output[1], renderer.getPaint());
    }


    private void saveScore() {

        SharedPreferences.Editor editor = gameData.edit();

        editor.putInt("score", scoreCounter);

        editor.apply();
    }

    private void loadScore() {

        //scoreCounter = gameData.getInt("score", 0);
    }
}
