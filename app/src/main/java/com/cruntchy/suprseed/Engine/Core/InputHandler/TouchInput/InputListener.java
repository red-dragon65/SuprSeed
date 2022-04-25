package com.cruntchy.suprseed.Engine.Core.InputHandler.TouchInput;

import android.view.MotionEvent;

import com.cruntchy.suprseed.Engine.Core.System.Layerable;
import com.cruntchy.suprseed.Engine.Lib.Collisions.Boundable;

public interface InputListener extends Layerable, Boundable {

    boolean processInput(String action, MotionEvent event);
}
