package dev.suprseed.Engine.Lib.Input;

import android.view.MotionEvent;

import dev.suprseed.Engine.Core.SpriteObjects.SpriteBase.Boundable;
import dev.suprseed.Engine.Core.System.Registerables.Layerable;

public interface InputListener extends Layerable, Boundable {

    boolean processInput(String action, MotionEvent event);
}
