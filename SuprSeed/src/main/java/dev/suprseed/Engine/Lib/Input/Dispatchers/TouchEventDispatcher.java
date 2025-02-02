package dev.suprseed.Engine.Lib.Input.Dispatchers;

import android.graphics.RectF;
import android.view.InputDevice;
import android.view.MotionEvent;

import java.util.List;

import dev.suprseed.Engine.Core.ErrorLogger.CentralLogger;
import dev.suprseed.Engine.Core.ErrorLogger.ErrorType;
import dev.suprseed.Engine.Core.MainView.GameProcessor.Render.Screen;
import dev.suprseed.Engine.Lib.Input.InputListener;

public class TouchEventDispatcher implements EventDispatcher {

    private final RectF bounds = new RectF();
    private float lastDownX;
    private float lastDownY;
    private float eventX;
    private float eventY;

    private TouchTypes actionPerformed;

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

            CentralLogger.getInstance().logMessage(ErrorType.WARN, "There are no listeners taking input!");

            return;
        }

        // Get top most listener being touched


        // Loop through listeners top down
        for (int i = listeners.size() - 1; i >= 0; i--) {

            // See if the listener was touched
            if (isTouching(listeners.get(i), event)) {

                // Notify listener
                processed = listeners.get(i).processInput(actionPerformed.toString(), event);

                // If listener handled request, end input processing
                if (processed) {
                    return;
                }

                CentralLogger.getInstance().logMessage(ErrorType.INFO, "An event was passed to the client!");
            }
        }


    }

    private boolean isTouching(InputListener listener, MotionEvent event) {

        // Convert screen location into view port terms
        eventX = Screen.getInstance().convertCanvasToViewPort(event.getX());
        eventY = Screen.getInstance().convertCanvasToViewPort(event.getY());

        // Get the bounds
        listener.getRectF(bounds);

        // See if event occurred on the listener
        if (!bounds.contains(eventX, eventY)) {
            return false;
        }

        // Calculate the touch type
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                actionPerformed = TouchTypes.HOLD;

                // Hold the last location
                lastDownX = eventX;
                lastDownY = eventY;

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
                    actionPerformed = TouchTypes.TAP;

                } else {

                    // Lift is at a different point then hold
                    actionPerformed = TouchTypes.LIFT;
                }
                return true;
            case MotionEvent.ACTION_MOVE:
                actionPerformed = TouchTypes.DRAG;
                return true;
        }


        // Event given was not handled
        return false;
    }
}
