package dev.suprseed.Engine.Lib.Input.Registers;

import java.util.Comparator;
import java.util.List;

import dev.suprseed.Engine.Core.System.Registerables.Layerable;
import dev.suprseed.Engine.Core.System.Registers.LayerSyncer;
import dev.suprseed.Engine.Lib.Input.InputListener;

public class InputListenerRegistry implements InputRegister<InputListener> {


    private final List<InputListener> listeners;
    private final LayerSyncer<InputListener> layerSyncer;


    // Constructor
    public InputListenerRegistry(List<InputListener> listeners, Comparator<Layerable> layerableComparator) {
        this.listeners = listeners;
        this.layerSyncer = new LayerSyncer<>(layerableComparator);
    }

    @Override
    public void registerObject(InputListener object) {

        listeners.add(object);

        layerSyncer.syncLayers(listeners);
    }

    @Override
    public void update() {

        layerSyncer.syncLayers(listeners);
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
