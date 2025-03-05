package dev.suprseed.demo.Sprites.HudSprite.OverlaySprites;

import android.view.MotionEvent;

import dev.suprseed.Engine.Core.MainView.GameProcessor.Loop.LoopRunner;
import dev.suprseed.Engine.Core.SpriteObjects.SpriteBase.AssetBundle;
import dev.suprseed.Engine.Core.SpriteObjects.SpriteBase.Sprite;
import dev.suprseed.Engine.EngineTools;
import dev.suprseed.Engine.Lib.Input.Dispatchers.TouchTypes;
import dev.suprseed.Engine.Lib.Input.InputListener;
import dev.suprseed.demo.Sprites.HudSprite.HudInput.FullScreenPauseInput;

public class PauseButton extends Sprite implements InputListener {

    private final InputListener screenListener;

    // Constructor
    public PauseButton(AssetBundle assetBundle) {
        super(assetBundle, 102);

        screenListener = new FullScreenPauseInput();

        EngineTools.getInputManager().getListenerRegistry().registerObject(this);
        EngineTools.getInputManager().getListenerRegistry().registerObject(screenListener);

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

            LoopRunner.loopy.toggleSoftPause();
        }

        // Prevent touch fall through
        return true;
    }
}
