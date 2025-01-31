package dev.suprseed.demo.Sprites.HudSprite.OverlaySprites;

import android.view.MotionEvent;

import dev.suprseed.Engine.Core.MainView.GameProcessor.Loop.LoopRunner;
import dev.suprseed.Engine.Core.MainView.GameProcessor.Render.ViewPort;
import dev.suprseed.Engine.Core.Scenes.SceneHeirarchy.BaseScene;
import dev.suprseed.Engine.Core.SpriteObjects.SpriteBase.AssetBundle;
import dev.suprseed.Engine.Core.SpriteObjects.SpriteBase.Sprite;
import dev.suprseed.Engine.Lib.Input.CentralInputManager;
import dev.suprseed.Engine.Lib.Input.Dispatchers.TouchTypes;
import dev.suprseed.Engine.Lib.Input.InputListener;
import dev.suprseed.demo.Sprites.HudSprite.HudInput.FullScreenPauseInput;

public class PauseButton extends Sprite implements InputListener {

    private final InputListener screenListener;

    // Constructor
    public PauseButton(BaseScene parentScene, AssetBundle assetBundle) {
        super(parentScene, assetBundle, 102);

        screenListener = new FullScreenPauseInput();

        CentralInputManager.getInstance().listenerRegister.registerObject(this);
        CentralInputManager.getInstance().listenerRegister.registerObject(screenListener);

        setX(ViewPort.getInstance().getWidth() - getWidth());

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
