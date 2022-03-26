package com.cruntchy.suprseed.Engine.InputHandler.TouchInput;

import android.view.MotionEvent;

public interface InputHandler {

    void processInput(MotionEvent event);

    void addInputHandler(InputProcessor inputMethod);

    void registerListener(InputListener listener);
}
