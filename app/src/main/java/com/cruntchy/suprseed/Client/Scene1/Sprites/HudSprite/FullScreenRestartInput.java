package com.cruntchy.suprseed.Client.Scene1.Sprites.HudSprite;

import android.graphics.RectF;
import android.view.MotionEvent;

import com.cruntchy.suprseed.Client.Scene1.Data.GameOverData;
import com.cruntchy.suprseed.Engine.Core.InputHandler.TouchInput.InputListener;
import com.cruntchy.suprseed.Engine.Core.MainView.GameProcessor.Render.CanvasData;
import com.cruntchy.suprseed.Engine.Core.SpriteObjects.SpriteBase.Sprite;

public class FullScreenRestartInput implements InputListener {

    private final Sprite gameOverText;
    private final GameOverData gameOverData;

    public FullScreenRestartInput(Sprite gameOverText, GameOverData gameOverData) {

        this.gameOverText = gameOverText;
        this.gameOverData = gameOverData;
    }

    @Override
    public void getRectF(RectF result) {

        float height = CanvasData.getInstance().getOriginalHeight();
        float width = CanvasData.getInstance().getOriginalWidth();

        result.set(0, 0, width, height);
    }

    @Override
    public boolean processInput(String action, MotionEvent event) {

        // Signal that game has been restarted
        // Hide game over image
        if (action.equals("tap")) {

            gameOverData.setGameOver(false);
            gameOverData.setRestart(true);
            gameOverText.setDrawable(false);

            return true;
        }

        return false;
    }

    @Override
    public int getLayerDepth() {

        // This should be above the users hero input
        // This should be above the pause button
        return 100;
    }
}
