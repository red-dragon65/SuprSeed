package dev.suprseed.Engine.Lib.Input.Registers;

import java.util.List;

import dev.suprseed.Engine.Lib.Input.Dispatchers.EventDispatcher;

public class InputDispatchHolder implements InputRegister<EventDispatcher> {


    private final List<EventDispatcher> inputs;


    // Constructor
    public InputDispatchHolder(List<EventDispatcher> inputs) {
        this.inputs = inputs;
    }

    @Override
    public List<EventDispatcher> getRegisterList() {
        return inputs;
    }

    @Override
    public void registerObject(EventDispatcher object) {

        inputs.add(object);
    }

    @Override
    public void removeObject(EventDispatcher object) {

        inputs.remove(object);
    }

    @Override
    public void removeAllObjects() {

        inputs.clear();
    }
}
