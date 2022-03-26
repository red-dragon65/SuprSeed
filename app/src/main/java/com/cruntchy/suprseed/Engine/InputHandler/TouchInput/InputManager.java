package com.cruntchy.suprseed.Engine.InputHandler.TouchInput;

import android.view.MotionEvent;

import com.cruntchy.suprseed.Engine.ErrorLogger.CentralLogger;
import com.cruntchy.suprseed.Engine.ErrorLogger.ErrorType;
import com.cruntchy.suprseed.Engine.SpriteObjects.System.Layerable;
import com.cruntchy.suprseed.Engine.SpriteObjects.System.LayerableQueueComparator;

import java.util.Comparator;
import java.util.List;

public class InputManager implements InputHandler {

    // TODO: Make this a singleton

    // Dependency injection
    private final Comparator<Layerable> layerComparer;
    private List<InputProcessor> inputs;
    private List<InputListener> listeners;


    /*
    // Constructor
    public InputManager(List<InputProcessor> inputs, Comparator<Layerable> layerComparer){

        this.inputs = inputs;
        this.layerComparer = layerComparer;
    }

    // Constructor
    public InputManager(Comparator<Layerable> layerComparer){

        inputs = new ArrayList<>();
        this.layerComparer = layerComparer;
    }*/

    // Constructor
    public InputManager() {
        this.layerComparer = new LayerableQueueComparator();
    }


    // VERIFY: is this correct?
    public static InputManager getInstance() {
        return InputSingleton.INSTANCE;
    }

    @Override
    public void processInput(MotionEvent event) {

        // Check if any processors exist
        if (inputs.size() == 0) {

            CentralLogger.logMessage(ErrorType.WARNING, "There are no input processors to handle the given input!");

            return;
        }

        // Get action performed
        for (InputProcessor ih : inputs) {

            ih.processEvent(listeners, event);
        }
    }

    @Override
    public void addInputHandler(InputProcessor inputMethod) {
        inputs.add(inputMethod);
    }

    @Override
    public void registerListener(InputListener listener) {

        // Add the listener
        listeners.add(listener);

        // Resort the listener based on layer depth
        listeners.sort(layerComparer);
    }

    // VERIFY: is this correct?
    private static class InputSingleton {
        private static final InputManager INSTANCE = new InputManager();
    }
}
