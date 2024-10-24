package dev.suprseed.demo.Sprites.HeroSprite;

import android.graphics.RectF;
import android.view.MotionEvent;

import dev.suprseed.Engine.Core.MainView.GameProcessor.Render.CanvasData;
import dev.suprseed.Engine.Core.SpriteObjects.SpriteBase.Sprite;
import dev.suprseed.Engine.Lib.Input.TouchInput.InputListener;
import dev.suprseed.demo.SharedData.GameOverData;

public class FullScreenHeroTouchInput implements InputListener {

    private final Sprite sprite;
    private final GameOverData gameOverData;
    private boolean hold = false;

    // Constructor
    public FullScreenHeroTouchInput(Sprite sprite, GameOverData gameOverData) {
        this.sprite = sprite;
        this.gameOverData = gameOverData;
    }

    @Override
    public boolean processInput(String action, MotionEvent event) {

        hold = action.equals("hold") || action.equals("drag");

        // Notify that user has triggered game to start
        if (!gameOverData.isStarted() && !gameOverData.isGameOver()) {
            gameOverData.setStarted(true);
        }

        // Pass to 'gameRestart' input if hero is inactive
        return sprite.isActive();
    }

    @Override
    public void getRectF(RectF result) {

        float height = CanvasData.getInstance().getOriginalHeight();
        float width = CanvasData.getInstance().getOriginalWidth();

        result.set(0, 0, width, height);
    }

    // This should be above everything else
    @Override
    public int getLayerDepth() {
        return 101;
    }


    public boolean isHold() {
        return hold;
    }

    public void setHold(boolean hold) {
        this.hold = hold;
    }
}
