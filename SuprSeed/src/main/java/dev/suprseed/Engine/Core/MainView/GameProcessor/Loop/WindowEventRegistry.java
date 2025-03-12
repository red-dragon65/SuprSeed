package dev.suprseed.Engine.Core.MainView.GameProcessor.Loop;

import java.util.ArrayList;
import java.util.List;

import dev.suprseed.Engine.Core.System.RegisterTypes.ObjectRegister;

public class WindowEventRegistry implements ObjectRegister<WindowEventListener>, WindowEventListener {

    private List<WindowEventListener> windowEventListeners;

    public WindowEventRegistry() {
        windowEventListeners = new ArrayList<>();
    }

    @Override
    public void onFocusChanged(boolean hasFocus) {

        for (WindowEventListener wel : windowEventListeners) {
            wel.onFocusChanged(hasFocus);
        }
    }

    @Override
    public void registerObject(WindowEventListener object) {
        windowEventListeners.add(object);
    }

    @Override
    public void removeObject(WindowEventListener object) {
        windowEventListeners.remove(object);
    }

    @Override
    public void removeAllObjects() {
        windowEventListeners.clear();
    }
}
