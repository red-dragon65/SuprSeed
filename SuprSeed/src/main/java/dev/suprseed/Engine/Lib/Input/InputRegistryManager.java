package dev.suprseed.Engine.Lib.Input;

import android.view.MotionEvent;

import java.util.ArrayList;

import dev.suprseed.Engine.Core.ErrorLogger.ErrorType;
import dev.suprseed.Engine.Core.MainView.GameProcessor.Loop.InputProcessor;
import dev.suprseed.Engine.Core.System.LayerableQueueComparator;
import dev.suprseed.Engine.EngineContext;
import dev.suprseed.Engine.Lib.Input.Dispatchers.EventDispatcher;
import dev.suprseed.Engine.Lib.Input.Registers.InputDispatchRegistry;
import dev.suprseed.Engine.Lib.Input.Registers.InputListenerRegistry;
import dev.suprseed.Engine.Lib.Input.Registers.InputRegister;

public class InputRegistryManager implements InputProcessor, InputManager {

    // Hold one or more pre-processors that dispatch relevant events that have occurred
    private final InputRegister<EventDispatcher> dispatcherRegistry;

    // Hold one or more listeners that get called when an event occurs
    private final InputRegister<InputListener> listenerRegistry;

    // Constructor
    // Private to prevent client use of 'new' keyword
    public InputRegistryManager() {

        dispatcherRegistry = new InputDispatchRegistry(new ArrayList<>());
        listenerRegistry = new InputListenerRegistry(new ArrayList<>(), new LayerableQueueComparator());
    }

    @Override
    public void processInput(MotionEvent event) {

        // Check if any dispatch processors exist
        if (dispatcherRegistry.getRegisterList().isEmpty()) {

            EngineContext.getLogger().logMessage(ErrorType.WARN, "There are no input processors to handle the given input!");

            return;
        }

        // Re-sync touch layers
        listenerRegistry.update();

        // Tell dispatchers to process events that have occurred
        for (EventDispatcher ih : dispatcherRegistry.getRegisterList()) {

            ih.processEvent(listenerRegistry.getRegisterList(), event);
        }
    }

    @Override
    public InputRegister<EventDispatcher> getDispatcherRegistry() {
        return dispatcherRegistry;
    }

    @Override
    public InputRegister<InputListener> getListenerRegistry() {
        return listenerRegistry;
    }
}
