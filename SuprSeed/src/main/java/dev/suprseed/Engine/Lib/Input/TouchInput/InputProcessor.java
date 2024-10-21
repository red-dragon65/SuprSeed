package dev.suprseed.Engine.Lib.Input.TouchInput;

import android.view.MotionEvent;

import java.util.List;

public interface InputProcessor {

    void processEvent(List<InputListener> listeners, MotionEvent event);
}
