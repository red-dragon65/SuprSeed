package dev.suprseed.demo.GamePlayScene.EntityScene.Hero;

import android.graphics.RectF;
import android.view.MotionEvent;

import dev.suprseed.Engine.Core.System.LayerData;
import dev.suprseed.Engine.Core.System.LayerHandler;
import dev.suprseed.Engine.EngineTools;
import dev.suprseed.Engine.Lib.Input.Dispatchers.TouchTypes;
import dev.suprseed.Engine.Lib.Input.InputListener;
import dev.suprseed.demo.GamePlayScene.SharedData.GameOverData;

public class FullScreenHeroTouchInput implements InputListener {

    private final GameOverData gameOverData;
    private boolean isActive;
    private boolean hold = false;
    private LayerHandler layerInfo = new LayerData(101);

    // Constructor
    public FullScreenHeroTouchInput(boolean isActive, GameOverData gameOverData) {
        this.isActive = isActive;
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
        return isActive;
    }

    @Override
    public void getRectF(RectF result) {

        float height = EngineTools.getViewPort().getHeight();
        float width = EngineTools.getViewPort().getWidth();

        result.set(0, 0, width, height);
    }

    // This should be above everything else
    @Override
    public LayerHandler getLayerInfo() {
        return layerInfo;
    }

    public void setActive(boolean isActive) {
        this.isActive = isActive;
    }

    public boolean isHold() {
        return hold;
    }

    public void setHold(boolean hold) {
        this.hold = hold;
    }
}
