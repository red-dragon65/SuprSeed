package com.cruntchy.suprseed.Engine.Core.InputHandler.TouchInput;

import android.view.MotionEvent;

import java.util.List;

public interface InputProcessor {

    void processEvent(List<InputListener> listeners, MotionEvent event);
}
