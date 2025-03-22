package dev.suprseed.demo.GamePlayScene.HudScene.OverlaySprites;

import android.content.Context;
import android.content.SharedPreferences;

import dev.suprseed.Engine.Core.MainView.GameProcessor.Render.Graphics.RenderHandler;
import dev.suprseed.Engine.Core.SpriteObjects.SpriteBase.Sprite;
import dev.suprseed.Engine.Core.System.Registerables.LogicRunnable;
import dev.suprseed.Engine.Lib.Fonts.FontHolder;
import dev.suprseed.Engine.Lib.Fonts.FontPaintRoller;
import dev.suprseed.demo.GamePlayScene.SharedData.BounceData;
import dev.suprseed.demo.GamePlayScene.SharedData.GameOverData;
import dev.suprseed.demo.R;
import dev.suprseed.demo.Utils.GameFontPainter;

public class ScoreSprite extends Sprite implements LogicRunnable {

    private final FontHolder scoreFont;
    private final String scoreText = "Score: ";
    private final String highScoreText = "Top: ";
    private final GameOverData gameOverData;

    private final BounceData bounceData;
    private final SharedPreferences gameData;
    private int scoreCounter = 0;
    private boolean saved = false;
    private int highScore = 0;

    public ScoreSprite(Context context, BounceData bounceData, GameOverData gameOverData) {
        super(null);

        this.bounceData = bounceData;

        this.scoreFont = new FontHolder(R.font.peaberry_base, 10, context, new GameFontPainter());

        // Load saved score value
        this.gameData = context.getSharedPreferences("GAME_DATA", Context.MODE_PRIVATE);

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
        //super.draw(renderer);

        // Draw the font
        if (!gameOverData.isStarted() && !gameOverData.isGameOver()) {

            FontPaintRoller.renderFont(renderer, this, highScoreText + highScore, scoreFont);
        } else {

            FontPaintRoller.renderFont(renderer, this, scoreText + scoreCounter, scoreFont);
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
