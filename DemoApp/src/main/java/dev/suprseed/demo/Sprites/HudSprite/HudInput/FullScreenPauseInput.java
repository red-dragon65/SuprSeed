package dev.suprseed.demo.Sprites.HudSprite.HudInput;

import android.graphics.RectF;
import android.view.MotionEvent;

import dev.suprseed.Engine.Core.MainView.GameProcessor.Loop.LoopManager;
import dev.suprseed.Engine.Core.MainView.GameProcessor.Render.CanvasData;
import dev.suprseed.Engine.Lib.Input.TouchInput.InputListener;

public class FullScreenPauseInput implements InputListener {

    // Constructor
    public FullScreenPauseInput() {
    }

    @Override
    public void getRectF(RectF result) {

        float height = CanvasData.getInstance().getOriginalHeight();
        float width = CanvasData.getInstance().getOriginalWidth();

        result.set(0, 0, width, height);
    }

    @Override
    public boolean processInput(String action, MotionEvent event) {

        if (action.equals("tap")) {

            LoopManager.loopy.setSoftPause(false);
        }

        // Input is handled while loop is paused
        return LoopManager.loopy.isSoftPause();

        // Allow input to fall through to next listener
    }

    @Override
    public int getLayerDepth() {

        // This should be the top most listener
        return 103;
    }
}
