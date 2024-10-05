package dev.suprseed.demo.Engine.Core.InputHandler.TouchInput;

import android.view.MotionEvent;

import dev.suprseed.demo.Engine.Core.System.Layerable;
import dev.suprseed.demo.Engine.Lib.Collisions.Boundable;

public interface InputListener extends Layerable, Boundable {

    boolean processInput(String action, MotionEvent event);
}
