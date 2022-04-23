package com.cruntchy.suprseed.Engine.InputHandler.TouchInput;

import android.view.MotionEvent;

import com.cruntchy.suprseed.Engine.Collisions.Boundable;
import com.cruntchy.suprseed.Engine.SpriteObjects.System.Layerable;

public interface InputListener extends Layerable, Boundable {

    boolean processInput(String action, MotionEvent event);
}
