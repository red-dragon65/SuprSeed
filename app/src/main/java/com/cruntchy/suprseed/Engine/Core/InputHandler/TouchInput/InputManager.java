package com.cruntchy.suprseed.Engine.Core.InputHandler.TouchInput;

import android.view.MotionEvent;

import com.cruntchy.suprseed.Engine.Core.ErrorLogger.CentralLogger;
import com.cruntchy.suprseed.Engine.Core.ErrorLogger.ErrorType;
import com.cruntchy.suprseed.Engine.Core.System.LayerableQueueComparator;

import java.util.ArrayList;

public class InputManager implements InputHandler {


    // Dependency injection
    public final InputRegister<InputProcessor> processorRegister;
    public final InputRegister<InputListener> listenerRegister;

    // Eager loading singleton
    private static final InputManager INSTANCE = new InputManager();

    // Constructor
    // Private to prevent client use of 'new' keyword
    private InputManager() {

        processorRegister = new InputProcessorRegister(new ArrayList<>());
        listenerRegister = new InputListenerHolder(new ArrayList<>(), new LayerableQueueComparator());
    }

    public static InputManager getInstance() {
        return INSTANCE;
    }

    @Override
    public void processInput(MotionEvent event) {

        // Check if any processors exist
        if (processorRegister.getRegisterList().size() == 0) {

            CentralLogger.getInstance().logMessage(ErrorType.WARNING, "There are no input processors to handle the given input!");

            return;
        }

        // Get action performed
        for (InputProcessor ih : processorRegister.getRegisterList()) {

            ih.processEvent(listenerRegister.getRegisterList(), event);
        }
    }
}
