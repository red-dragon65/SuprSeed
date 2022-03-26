package com.cruntchy.suprseed.Engine.InputHandler.TouchInput;

import android.view.MotionEvent;

import java.util.List;

public interface InputProcessor {

    void processEvent(List<InputListener> listeners, MotionEvent event);
}
