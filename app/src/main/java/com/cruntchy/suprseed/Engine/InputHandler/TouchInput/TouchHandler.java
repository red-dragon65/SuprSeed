package com.cruntchy.suprseed.Engine.InputHandler.TouchInput;

import android.graphics.RectF;
import android.view.InputDevice;
import android.view.MotionEvent;

import com.cruntchy.suprseed.Engine.ErrorLogger.CentralLogger;
import com.cruntchy.suprseed.Engine.ErrorLogger.ErrorType;

import java.util.List;

public class TouchHandler implements InputProcessor {

    private int lastDownX;
    private int lastDownY;

    private String actionPerformed;

    @Override
    public void processEvent(List<InputListener> listeners, MotionEvent event) {

        // Check input type
        if (!event.isFromSource(InputDevice.SOURCE_TOUCHSCREEN)) {

            CentralLogger.logMessage(ErrorType.INFO, "Ignored irrelevant event.");

            return;
        }

        // Check if any listeners exist
        if (listeners.size() == 0) {

            CentralLogger.logMessage(ErrorType.WARNING, "There are no listeners taking input!");

            return;
        }

        // Get top most listener being touched
        InputListener il = getTopListener(listeners, event);

        // Notify listener
        if (il != null) {
            il.processInput(actionPerformed, event);

            CentralLogger.logMessage(ErrorType.INFO, "An event was passed to the client!");
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

        // TODO: Set this up properly
        //RectF rect = listener.getRectangle();
        RectF rect = new RectF();

        // See if event occurred on the listener
        if (!rect.contains(event.getX(), event.getY())) {
            return false;
        }


        // Calculate the touch type
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                actionPerformed = "hold";
                return true;
            case MotionEvent.ACTION_UP:

                if (lastDownX == event.getX() && lastDownY == event.getY()) {

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
