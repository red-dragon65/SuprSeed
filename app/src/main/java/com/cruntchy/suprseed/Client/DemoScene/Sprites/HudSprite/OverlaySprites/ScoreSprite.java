package com.cruntchy.suprseed.Client.DemoScene.Sprites.HudSprite.OverlaySprites;

import android.content.Context;
import android.content.SharedPreferences;

import com.cruntchy.suprseed.Client.DemoScene.SharedData.BounceData;
import com.cruntchy.suprseed.Client.DemoScene.SharedData.GameOverData;
import com.cruntchy.suprseed.Client.DemoScene.Sprites.HudSprite.HudFontPaintStrategies.ScorePaintStrategy;
import com.cruntchy.suprseed.Engine.Core.MainView.GameProcessor.Render.Graphics.RenderHandler;
import com.cruntchy.suprseed.Engine.Core.Scenes.SceneHeirarchy.BaseScene;
import com.cruntchy.suprseed.Engine.Core.SpriteObjects.SpriteBase.Sprite;
import com.cruntchy.suprseed.Engine.Core.System.Logic;
import com.cruntchy.suprseed.Engine.Lib.Fonts.FontHolder;
import com.cruntchy.suprseed.R;

public class ScoreSprite extends Sprite implements Logic {

    private final FontHolder scoreFont;
    private final String scoreText = "Score: ";
    private final String highScoreText = "Top: ";
    private final GameOverData gameOverData;

    private final BounceData bounceData;
    private final SharedPreferences gameData;
    private int scoreCounter = 0;
    private boolean saved = false;
    private int highScore = 0;

    public ScoreSprite(BaseScene parentScene, BounceData bounceData, GameOverData gameOverData) {
        super(parentScene, null);

        this.bounceData = bounceData;

        this.scoreFont = new FontHolder(R.font.peaberry_base, 10, parentScene.getContext(), new ScorePaintStrategy());

        // Load saved score value
        this.gameData = parentScene.getContext().getSharedPreferences("GAME_DATA", Context.MODE_PRIVATE);

        this.gameOverData = gameOverData;

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
        scoreFont.updatePaint(renderer.getPaint());

        // Scale the location
        float[] output = renderer.getCoordinateHandler().parseLocation(this);


        // Draw the font
        if (!gameOverData.isStarted() && !gameOverData.isGameOver()) {

            renderer.getCanvas().drawText(highScoreText + highScore, output[0], output[1], renderer.getPaint());
        } else {

            renderer.getCanvas().drawText(scoreText + scoreCounter, output[0], output[1], renderer.getPaint());
        }
    }


    private void saveScore() {

        // Only save new high scores
        if (scoreCounter > highScore) {

            SharedPreferences.Editor editor = gameData.edit();

            editor.putInt("score", scoreCounter);

            editor.apply();

            // Load new score into system
            loadScore();
        }
    }

    private void loadScore() {

        highScore = gameData.getInt("score", 0);
    }

    public void resetState() {

        loadScore();
        scoreCounter = 0;
    }
}
