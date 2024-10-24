package dev.suprseed.Engine.Lib.Input.TouchInput;

import android.view.MotionEvent;

import java.util.ArrayList;

import dev.suprseed.Engine.Core.ErrorLogger.CentralLogger;
import dev.suprseed.Engine.Core.ErrorLogger.ErrorType;
import dev.suprseed.Engine.Core.MainView.GameProcessor.Loop.InputHandler;
import dev.suprseed.Engine.Core.System.LayerableQueueComparator;

public class InputManager implements InputHandler {


    // Eager loading singleton
    private static final InputManager INSTANCE = new InputManager();
    // Dependency injection
    public final InputRegister<InputProcessor> processorRegister;
    public final InputRegister<InputListener> listenerRegister;

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

            CentralLogger.getInstance().logMessage(ErrorType.WARN, "There are no input processors to handle the given input!");

            return;
        }

        // Get action performed
        for (InputProcessor ih : processorRegister.getRegisterList()) {

            ih.processEvent(listenerRegister.getRegisterList(), event);
        }
    }
}
