package dev.suprseed.Engine.Lib.Input.Dispatchers;

import android.view.MotionEvent;

import java.util.List;

import dev.suprseed.Engine.Lib.Input.InputListener;

public interface EventDispatcher {

    void processEvent(List<InputListener> listeners, MotionEvent event);
}
