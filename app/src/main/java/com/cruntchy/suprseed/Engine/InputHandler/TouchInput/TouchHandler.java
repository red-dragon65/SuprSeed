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
        InputListener il = getTopListener(listeners, event);

        // Notify listener
        if (il != null) {
            il.processInput(actionPerformed, event);

            CentralLogger.getInstance().logMessage(ErrorType.INFO, "An event was passed to the client!");
        }


    }


    private InputListener getTopListener(List<InputListener> listeners, MotionEvent event) {

        // Loop through listeners top down
        for (int i = listeners.size() - 1; i >= 0; i--) {

            // See if the listener was touched
            if (isTouching(listeners.get(i), event)) {

                // Return the listener for processing
                return listeners.get(i);
            }
        }

        // No listener was touched
        return null;
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
                return true;
            case MotionEvent.ACTION_UP:

                if (bounds.contains(lastDownX, lastDownY)) {

                    // Hold and lift are at the same point
                    actionPerformed = "tap";
                } else {

                    // Left is at a different point then hold
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
