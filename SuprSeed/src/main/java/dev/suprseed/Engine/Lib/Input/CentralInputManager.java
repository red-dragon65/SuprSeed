package dev.suprseed.Engine.Lib.Input;

import android.view.MotionEvent;

import java.util.ArrayList;

import dev.suprseed.Engine.Core.ErrorLogger.CentralLogger;
import dev.suprseed.Engine.Core.ErrorLogger.ErrorType;
import dev.suprseed.Engine.Core.MainView.GameProcessor.Loop.InputHandler;
import dev.suprseed.Engine.Core.System.LayerableQueueComparator;
import dev.suprseed.Engine.Lib.Input.Dispatchers.EventDispatcher;
import dev.suprseed.Engine.Lib.Input.Registers.InputDispatchHolder;
import dev.suprseed.Engine.Lib.Input.Registers.InputListenerHolder;
import dev.suprseed.Engine.Lib.Input.Registers.InputRegister;

public class CentralInputManager implements InputHandler {

    // Eager loading singleton
    private static final CentralInputManager INSTANCE = new CentralInputManager();

    // Hold one or more pre-processors that dispatch relevant events that have occurred
    public final InputRegister<EventDispatcher> dispatcherRegister;

    // Hold one or more listeners that get called when an event occurs
    public final InputRegister<InputListener> listenerRegister;

    // Constructor
    // Private to prevent client use of 'new' keyword
    private CentralInputManager() {

        dispatcherRegister = new InputDispatchHolder(new ArrayList<>());
        listenerRegister = new InputListenerHolder(new ArrayList<>(), new LayerableQueueComparator());
    }

    public static CentralInputManager getInstance() {
        return INSTANCE;
    }

    @Override
    public void processInput(MotionEvent event) {

        // Check if any dispatch processors exist
        if (dispatcherRegister.getRegisterList().size() == 0) {

            CentralLogger.getInstance().logMessage(ErrorType.WARN, "There are no input processors to handle the given input!");

            return;
        }

        // Tell dispatchers to process events that have occurred
        for (EventDispatcher ih : dispatcherRegister.getRegisterList()) {

            ih.processEvent(listenerRegister.getRegisterList(), event);
        }
    }
}
