package dev.suprseed.demo.Sprites.HudSprite.HudInput;

import android.graphics.RectF;
import android.view.MotionEvent;

import dev.suprseed.Engine.Core.MainView.GameProcessor.Loop.LoopRunner;
import dev.suprseed.Engine.Core.MainView.GameProcessor.Render.Screen;
import dev.suprseed.Engine.Core.System.LayerData;
import dev.suprseed.Engine.Core.System.LayerHandler;
import dev.suprseed.Engine.Lib.Input.Dispatchers.TouchTypes;
import dev.suprseed.Engine.Lib.Input.InputListener;

public class FullScreenPauseInput implements InputListener {

    private LayerHandler layerInfo = new LayerData(103);

    // Constructor
    public FullScreenPauseInput() {
    }

    @Override
    public void getRectF(RectF result) {

        float height = Screen.getInstance().getHeight();
        float width = Screen.getInstance().getWidth();

        result.set(0, 0, width, height);
    }

    @Override
    public boolean processInput(String action, MotionEvent event) {

        if (action.equals(TouchTypes.TAP.toString())) {

            LoopRunner.loopy.setSoftPause(false);
        }

        // Input is handled while loop is paused
        return LoopRunner.loopy.isSoftPause();

        // Allow input to fall through to next listener
    }

    @Override
    public LayerHandler getLayerInfo() {
        return layerInfo;
    }
}
