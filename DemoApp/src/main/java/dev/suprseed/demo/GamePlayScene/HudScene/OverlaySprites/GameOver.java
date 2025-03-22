package dev.suprseed.demo.GamePlayScene.HudScene.OverlaySprites;


import android.content.Context;

import dev.suprseed.Engine.Core.MainView.GameProcessor.Render.Graphics.RenderHandler;
import dev.suprseed.Engine.Core.SpriteObjects.SpriteBase.Sprite;
import dev.suprseed.Engine.Lib.Fonts.FontHolder;
import dev.suprseed.Engine.Lib.Fonts.FontPaintRoller;
import dev.suprseed.demo.GamePlayScene.SharedData.GameOverData;
import dev.suprseed.demo.R;
import dev.suprseed.demo.Utils.GameFontPainter;

public class GameOver extends Sprite {

    private final GameOverData gameOverData;
    private final FontHolder gameOverFont;

    private final int gameOverTextLocY = 100;
    private final int gameOverTextLocX = 20;

    private final int restartTextLocY = 130;
    private final int restartTextLocX = 3;

    private final String gameOverText = "Game Over";
    private final String restartText = "Tap to Restart";


    public GameOver(Context context, GameOverData gameOverData) {
        super(null);

        gameOverFont = new FontHolder(R.font.peaberry_base, 12, context, new GameFontPainter());

        // Start hidden until game over occurs
        setDrawable(false);

        this.gameOverData = gameOverData;
    }

    @Override
    public void runLogic() {

        // Show game over image if game over occurs
        if (gameOverData.isGameOver() && !isDrawable()) {
            setDrawable(true);
        }
    }


    @Override
    public void draw(RenderHandler renderer) {
        //super.draw(renderer);

        // Scale the location then draw text
        setX(gameOverTextLocX);
        setY(gameOverTextLocY);
        FontPaintRoller.renderFont(renderer, this, gameOverText, gameOverFont);


        // Scale the location then draw text
        setX(restartTextLocX);
        setY(restartTextLocY);
        FontPaintRoller.renderFont(renderer, this, restartText, gameOverFont);
    }
}
