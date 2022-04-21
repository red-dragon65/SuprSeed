package com.cruntchy.suprseed.Client.Scene1.Sprites.HudSprite;

import android.view.MotionEvent;

import com.cruntchy.suprseed.Engine.InputHandler.TouchInput.InputListener;
import com.cruntchy.suprseed.Engine.InputHandler.TouchInput.InputManager;
import com.cruntchy.suprseed.Engine.MainView.GameProcessor.Loop.LoopManager;
import com.cruntchy.suprseed.Engine.MainView.GameProcessor.Render.CanvasData;
import com.cruntchy.suprseed.Engine.Scenes.SceneHeirarchy.BaseScene;
import com.cruntchy.suprseed.Engine.SpriteObjects.SpriteBase.ImageHandler;
import com.cruntchy.suprseed.Engine.SpriteObjects.SpriteBase.Sprite;

public class PauseButton extends Sprite implements InputListener {

    // Constructor
    public PauseButton(BaseScene parentScene, ImageHandler imageHandler) {
        super(parentScene, imageHandler);

        setLayerDepth(200);

        InputManager.getInstance().listenerRegister.registerObject(this);

        setX(CanvasData.getInstance().getScaledWidth() - imageHandler.getSelectedImageSet().getScaledWidth());

    }

    @Override
    public void runLogic() {

        // Ignore this
    }

    @Override
    public void processInput(String action, MotionEvent event) {

        if(action.equals("tap")){

            LoopManager.loopy.toggleSoftPause();
        }
    }
}
