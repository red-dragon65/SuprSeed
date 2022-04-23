package com.cruntchy.suprseed.Engine.InputHandler.TouchInput;

import android.graphics.RectF;
import android.view.InputDevice;
import android.view.MotionEvent;

import com.cruntchy.suprseed.Engine.ErrorLogger.CentralLogger;
import com.cruntchy.suprseed.Engine.ErrorLogger.ErrorType;

import java.util.List;

public class TouchHandler implements InputProcessor {

    private final RectF bounds = new RectF();
    private float lastDownX;
    private float lastDownY;

    private String actionPerformed;

    private boolean processed = false;

    private InputListener lastListenerHeld;

    @Override
    public void processEvent(List<InputListener> listeners, MotionEvent event) {

        // Check input type
        if (!event.isFromSource(InputDevice.SOURCE_TOUCHSCREEN)) {

            CentralLogger.getInstance().logMessage(ErrorType.INFO, "Ignored irrelevant event.");

            return;
        }

        // Check if any listeners exist
        if (listeners.size() == 0) {

            CentralLogger.getInstance().logMessage(ErrorType.WARNING, "There are no listeners taking input!");

            return;
        }

        // Get top most listener being touched


        // Loop through listeners top down
        for (int i = listeners.size() - 1; i >= 0; i--) {

            // See if the listener was touched
            if (isTouching(listeners.get(i), event)) {

                // Notify listener
                processed = listeners.get(i).processInput(actionPerformed, event);

                // If listener handled request, end input processing
                if (processed) {
                    return;
                }

                CentralLogger.getInstance().logMessage(ErrorType.INFO, "An event was passed to the client!");
            }
        }


    }

    private boolean isTouching(InputListener listener, MotionEvent event) {

        // Get the bounds
        listener.getRectF(bounds);

        // See if event occurred on the listener
        if (!bounds.contains(event.getX(), event.getY())) {
            return false;
        }

        // Calculate the touch type
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                actionPerformed = "hold";

                // Hold the last location
                lastDownX = event.getX();
                lastDownY = event.getY();

                // Hold the listener that is being helded
                lastListenerHeld = listener;

                return true;
            case MotionEvent.ACTION_UP:

                // Make sure same listener that was holding is same listener lifting
                if (lastListenerHeld != null && listener != lastListenerHeld) {

                    break;
                }

                if (bounds.contains(lastDownX, lastDownY)) {

                    // Hold and lift are at the same point
                    actionPerformed = "tap";

                } else {

                    // Lift is at a different point then hold
                    actionPerformed = "lift";
                }
                return true;
            case MotionEvent.ACTION_MOVE:
                actionPerformed = "drag";
                return true;
        }


        // Event given was not handled
        return false;
    }
}
