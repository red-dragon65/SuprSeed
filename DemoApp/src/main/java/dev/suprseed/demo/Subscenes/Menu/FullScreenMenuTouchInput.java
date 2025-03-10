package dev.suprseed.demo.Subscenes.Menu;

import android.graphics.RectF;
import android.view.MotionEvent;

import dev.suprseed.Engine.Core.System.LayerData;
import dev.suprseed.Engine.Core.System.LayerHandler;
import dev.suprseed.Engine.EngineTools;
import dev.suprseed.Engine.Lib.Input.InputListener;
import dev.suprseed.demo.Subscenes.ChangeSceneRequestDTO;

public class FullScreenMenuTouchInput implements InputListener {

    private LayerHandler layerInfo = new LayerData(101);

    private ChangeSceneRequestDTO changeSceneRequestDTO;


    public FullScreenMenuTouchInput(ChangeSceneRequestDTO changeSceneRequestDTO) {
        this.changeSceneRequestDTO = changeSceneRequestDTO;
    }

    @Override
    public boolean processInput(String action, MotionEvent event) {

        // Trigger to change scene
        changeSceneRequestDTO.isChangeRequested = true;

        return true;
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
}
