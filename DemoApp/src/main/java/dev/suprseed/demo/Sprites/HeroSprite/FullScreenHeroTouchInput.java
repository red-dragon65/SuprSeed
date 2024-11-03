package dev.suprseed.demo.Sprites.HeroSprite;

import android.graphics.RectF;
import android.view.MotionEvent;

import dev.suprseed.Engine.Core.MainView.GameProcessor.Render.CanvasData;
import dev.suprseed.Engine.Core.SpriteObjects.SpriteBase.Sprite;
import dev.suprseed.Engine.Core.System.LayerData;
import dev.suprseed.Engine.Core.System.LayerHandler;
import dev.suprseed.Engine.Lib.Input.Dispatchers.TouchTypes;
import dev.suprseed.Engine.Lib.Input.InputListener;
import dev.suprseed.demo.SharedData.GameOverData;

public class FullScreenHeroTouchInput implements InputListener {

    private final Sprite sprite;
    private final GameOverData gameOverData;
    private boolean hold = false;
    private LayerHandler layerInfo = new LayerData(101);

    // Constructor
    public FullScreenHeroTouchInput(Sprite sprite, GameOverData gameOverData) {
        this.sprite = sprite;
        this.gameOverData = gameOverData;
    }

    @Override
    public boolean processInput(String action, MotionEvent event) {

        hold = action.equals(TouchTypes.HOLD.toString()) || action.equals(TouchTypes.DRAG.toString());

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
    public LayerHandler getLayerInfo() {
        return layerInfo;
    }


    public boolean isHold() {
        return hold;
    }

    public void setHold(boolean hold) {
        this.hold = hold;
    }
}
