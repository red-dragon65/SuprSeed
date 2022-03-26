package com.cruntchy.suprseed.Engine.InputHandler.TouchInput;

import android.view.MotionEvent;

import com.cruntchy.suprseed.Engine.SpriteObjects.System.Layerable;

public interface InputListener extends Layerable {

    void processInput(String action, MotionEvent event);
}
