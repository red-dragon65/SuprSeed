package dev.suprseed.Engine.Core.InputHandler.TouchInput;

import java.util.List;

public class InputProcessorRegister implements InputRegister<InputProcessor> {


    private final List<InputProcessor> inputs;


    // Constructor
    public InputProcessorRegister(List<InputProcessor> inputs) {
        this.inputs = inputs;
    }

    @Override
    public List<InputProcessor> getRegisterList() {
        return inputs;
    }

    @Override
    public void registerObject(InputProcessor object) {

        inputs.add(object);
    }

    @Override
    public void removeObject(InputProcessor object) {

        inputs.remove(object);
    }

    @Override
    public void removeAllObjects() {

        inputs.clear();
    }
}
