package dev.suprseed.demo.Client.DemoScene.Sprites.HudSprite.OverlaySprites;

import android.view.MotionEvent;

import dev.suprseed.demo.Client.DemoScene.Sprites.HudSprite.HudInput.FullScreenPauseInput;
import dev.suprseed.demo.Engine.Core.InputHandler.TouchInput.InputListener;
import dev.suprseed.demo.Engine.Core.InputHandler.TouchInput.InputManager;
import dev.suprseed.demo.Engine.Core.MainView.GameProcessor.Loop.LoopManager;
import dev.suprseed.demo.Engine.Core.MainView.GameProcessor.Render.CanvasData;
import dev.suprseed.demo.Engine.Core.Scenes.SceneHeirarchy.BaseScene;
import dev.suprseed.demo.Engine.Core.SpriteObjects.SpriteBase.ImageHandler;
import dev.suprseed.demo.Engine.Core.SpriteObjects.SpriteBase.Sprite;

public class PauseButton extends Sprite implements InputListener {

    private final InputListener screenListener;

    // Constructor
    public PauseButton(BaseScene parentScene, ImageHandler imageHandler) {
        super(parentScene, imageHandler);

        setLayerDepth(102);
        screenListener = new FullScreenPauseInput();

        InputManager.getInstance().listenerRegister.registerObject(this);
        InputManager.getInstance().listenerRegister.registerObject(screenListener);

        setX(CanvasData.getInstance().getScaledWidth() - imageHandler.getSelectedImageSet().getScaledWidth());

    }

    @Override
    public void runLogic() {

        // Ignore this
    }

    @Override
    public boolean processInput(String action, MotionEvent event) {

        // Toggle pausing if pause button tapped
        if (action.equals("tap")) {

            LoopManager.loopy.toggleSoftPause();
        }

        // Prevent touch fall through
        return true;
    }
}
