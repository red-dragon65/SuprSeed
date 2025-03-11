package dev.suprseed.demo.Subscenes.SplashScreen;

import android.graphics.Paint;

public class AlphaController {

    private int currentAlpha = 0;
    private int steps; // Note: this effectively gets capped to 255
    private int channelMax = 255;
    private int channelSteps;
    private boolean start = false;

    // It's recommended to use our own paint instead of the renderer.getPaint() method.
    // This is because the render.getPaint() should be returned to its original state after
    // it is modified to prevent changes from being carried over to other rendered items.
    private Paint alphaPaint = new Paint();


    public AlphaController(int steps) {
        this.steps = steps;
        channelSteps = (int) Math.ceil((double) channelMax / steps);
    }


    public void toggleAnimation(boolean start) {
        this.start = start;
    }

    public void resetAnimation() {
        currentAlpha = 0;
        start = false;
    }

    public boolean isDone() {
        return (currentAlpha >= channelMax);
    }

    public boolean isStarted() {
        return start;
    }

    public Paint getCurrentAlphaPaint() {

        alphaPaint.setAlpha(currentAlpha);

        return alphaPaint;
    }

    public int getCurrentAlpha() {
        return currentAlpha;
    }

    public void update() {
        if (start && currentAlpha < channelMax) {
            currentAlpha += channelSteps;
        }
    }
}
