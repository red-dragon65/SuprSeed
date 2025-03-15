package dev.suprseed.demo.GamePlayScene.HudScene.HudInput;

import android.graphics.RectF;
import android.view.MotionEvent;

import dev.suprseed.Engine.Core.SpriteObjects.SpriteBase.Sprite;
import dev.suprseed.Engine.Core.System.LayerData;
import dev.suprseed.Engine.Core.System.LayerHandler;
import dev.suprseed.Engine.EngineTools;
import dev.suprseed.Engine.Lib.Input.Dispatchers.TouchTypes;
import dev.suprseed.Engine.Lib.Input.InputListener;
import dev.suprseed.demo.GamePlayScene.SharedData.GameOverData;

public class FullScreenRestartInput implements InputListener {

    private final Sprite gameOverText;
    private final GameOverData gameOverData;
    private LayerHandler layerInfo = new LayerData(100);

    public FullScreenRestartInput(Sprite gameOverText, GameOverData gameOverData) {

        this.gameOverText = gameOverText;
        this.gameOverData = gameOverData;
    }

    @Override
    public void getRectF(RectF result) {

        float height = EngineTools.getViewPort().getHeight();
        float width = EngineTools.getViewPort().getWidth();

        result.set(0, 0, width, height);
    }

    @Override
    public boolean processInput(String action, MotionEvent event) {

        // Signal that game has been restarted
        // Hide game over image
        if (action.equals(TouchTypes.TAP.toString())) {

            gameOverData.setGameOver(false);
            gameOverData.setRestart(true);
            gameOverText.setDrawable(false);

            return true;
        }

        return false;
    }

    @Override
    public LayerHandler getLayerInfo() {
        return layerInfo;
    }
}
