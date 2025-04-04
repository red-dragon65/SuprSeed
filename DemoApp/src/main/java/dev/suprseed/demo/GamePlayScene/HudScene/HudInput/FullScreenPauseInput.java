package dev.suprseed.demo.GamePlayScene.HudScene.HudInput;

import android.graphics.RectF;
import android.view.MotionEvent;

import dev.suprseed.Engine.Core.System.LayerData;
import dev.suprseed.Engine.Core.System.LayerHandler;
import dev.suprseed.Engine.EngineTools;
import dev.suprseed.Engine.Lib.Input.Dispatchers.TouchTypes;
import dev.suprseed.Engine.Lib.Input.InputListener;

public class FullScreenPauseInput implements InputListener {

    private LayerHandler layerInfo = new LayerData(103);

    // Constructor
    public FullScreenPauseInput() {
    }

    @Override
    public void getRectF(RectF result) {

        float height = EngineTools.getViewPort().getHeight();
        float width = EngineTools.getViewPort().getWidth();

        result.set(0, 0, width, height);
    }

    @Override
    public boolean processInput(String action, MotionEvent event) {

        if (action.equals(TouchTypes.TAP.toString())) {

            EngineTools.getLoopController().setSoftPause(false);
        }

        // Input is handled while loop is paused
        return EngineTools.getLoopController().isSoftPause();

        // Allow input to fall through to next listener
    }

    @Override
    public LayerHandler getLayerInfo() {
        return layerInfo;
    }
}
