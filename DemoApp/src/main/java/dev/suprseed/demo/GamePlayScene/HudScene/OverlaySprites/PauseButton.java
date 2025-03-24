package dev.suprseed.demo.GamePlayScene.HudScene.OverlaySprites;

import android.view.MotionEvent;

import dev.suprseed.Engine.Core.SpriteObjects.SpriteBase.AssetBundle;
import dev.suprseed.Engine.Core.SpriteObjects.SpriteBase.Sprite;
import dev.suprseed.Engine.EngineTools;
import dev.suprseed.Engine.Lib.Input.Dispatchers.TouchTypes;
import dev.suprseed.Engine.Lib.Input.InputListener;

public class PauseButton extends Sprite implements InputListener {

    // Constructor
    public PauseButton(AssetBundle assetBundle) {
        super(assetBundle, 102);

        setX(EngineTools.getViewPort().getWidth() - getWidth());
    }

    @Override
    public void runLogic() {

        // Ignore this
    }

    @Override
    public boolean processInput(String action, MotionEvent event) {

        // Toggle pausing if pause button tapped
        if (action.equals(TouchTypes.TAP.toString())) {

            EngineTools.getLoopController().toggleSoftPause();
        }

        // Prevent touch fall through
        return true;
    }
}
