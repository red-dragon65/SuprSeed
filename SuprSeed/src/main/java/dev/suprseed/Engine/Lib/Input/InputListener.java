package dev.suprseed.Engine.Lib.Input;

import android.view.MotionEvent;

import dev.suprseed.Engine.Core.System.Registerables.ILayerable;
import dev.suprseed.Engine.Lib.Collisions.Boundable;

public interface InputListener extends ILayerable, Boundable {

    boolean processInput(String action, MotionEvent event);
}
