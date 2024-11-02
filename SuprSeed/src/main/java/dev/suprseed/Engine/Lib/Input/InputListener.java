package dev.suprseed.Engine.Lib.Input;

import android.view.MotionEvent;

import dev.suprseed.Engine.Core.System.Registerables.Layerable;
import dev.suprseed.Engine.Lib.Collisions.Boundable;

public interface InputListener extends Layerable, Boundable {

    boolean processInput(String action, MotionEvent event);
}
