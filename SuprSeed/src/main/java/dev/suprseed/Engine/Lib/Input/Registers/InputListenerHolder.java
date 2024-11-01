package dev.suprseed.Engine.Lib.Input.Registers;

import java.util.Comparator;
import java.util.List;

import dev.suprseed.Engine.Core.System.Registerables.ILayerable;
import dev.suprseed.Engine.Lib.Input.InputListener;

public class InputListenerHolder implements InputRegister<InputListener> {


    private final List<InputListener> listeners;
    private final Comparator<ILayerable> layerableComparator;


    // Constructor
    public InputListenerHolder(List<InputListener> listeners, Comparator<ILayerable> layerableComparator) {
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
