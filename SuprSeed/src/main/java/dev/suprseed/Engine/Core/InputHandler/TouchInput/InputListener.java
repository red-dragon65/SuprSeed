package dev.suprseed.Engine.Core.InputHandler.TouchInput;

import android.view.MotionEvent;

import dev.suprseed.Engine.Core.System.Layerable;
import dev.suprseed.Engine.Lib.Collisions.Boundable;

public interface InputListener extends Layerable, Boundable {

    boolean processInput(String action, MotionEvent event);
}
