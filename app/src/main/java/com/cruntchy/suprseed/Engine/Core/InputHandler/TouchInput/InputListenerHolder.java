package com.cruntchy.suprseed.Engine.Core.InputHandler.TouchInput;

import com.cruntchy.suprseed.Engine.Core.System.Layerable;

import java.util.Comparator;
import java.util.List;

public class InputListenerHolder implements InputRegister<InputListener> {


    private final List<InputListener> listeners;
    private final Comparator<Layerable> layerableComparator;


    // Constructor
    public InputListenerHolder(List<InputListener> listeners, Comparator<Layerable> layerableComparator) {
        this.listeners = listeners;
        this.layerableComparator = layerableComparator;
    }


    @Override
    public void registerObject(InputListener object) {

        listeners.add(object);

        listeners.sort(layerableComparator);
    }

    @Override
    public void removeObject(InputListener object) {

        listeners.remove(object);
    }

    @Override
    public void removeAllObjects() {

        listeners.clear();
    }

    @Override
    public List<InputListener> getRegisterList() {
        return listeners;
    }
}
