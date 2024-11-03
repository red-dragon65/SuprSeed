package dev.suprseed.Engine.Lib.Collisions;

import android.graphics.Color;
import android.graphics.RectF;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import dev.suprseed.Engine.Core.ErrorLogger.CentralLogger;
import dev.suprseed.Engine.Core.ErrorLogger.ErrorType;
import dev.suprseed.Engine.Core.MainView.GameProcessor.Render.Graphics.CollisionDrawable;
import dev.suprseed.Engine.Core.MainView.GameProcessor.Render.Graphics.RenderHandler;
import dev.suprseed.Engine.Core.SpriteObjects.SpriteBase.Sprite;
import dev.suprseed.Engine.Core.System.RenderSystem;

public class CollisionDiagnosticsOverlay implements CollisionDrawable {

    // Rectangle pool
    private final List<RectF> boundsPool;
    private final int initSize = 10;
    private final int maxSize = 100;
    private boolean isEnabled = true;
    private int rectColor = Color.argb(150, 200, 200, 0); //Yellow
    private int currentSize = initSize;
    private int currentIndex = 0;


    // Constructor
    // Private to prevent client use of 'new' keyword
    public CollisionDiagnosticsOverlay(boolean isEnabled) {

        this.isEnabled = isEnabled;
        boundsPool = new ArrayList<>();
        init();
    }

    public CollisionDiagnosticsOverlay(boolean isEnabled, int rectColor) {

        this.isEnabled = isEnabled;
        this.rectColor = rectColor;
        boundsPool = new ArrayList<>();
        init();
    }


    private void init() {

        // Initialize the pool
        for (int i = 0; i < initSize; i++) {
            boundsPool.add(new RectF());
        }

        RenderSystem.getInstance().getImageRegister().registerObject(this);
    }


    public void setColor(int rectColor) {
        this.rectColor = rectColor;
    }

    private Optional<RectF> checkoutRectF() {

        if (isEnabled) {

            RectF result;

            // Get the box
            if (currentIndex < currentSize - 1) {

                result = boundsPool.get(currentIndex);
                currentIndex++;
                return Optional.ofNullable(result);
            }

            // Resize the pool, then get the box
            if (currentSize < maxSize) {

                boundsPool.add(new RectF());
                currentSize++;
                return checkoutRectF();

            } else {

                CentralLogger.getInstance().logMessage(ErrorType.WARN,
                        "Bounding box draw limit reached!" +
                                "(Max size: " + maxSize + ") == (Current size: " + currentSize + ")" +
                                "No more collision bounds can be added!");
            }
        }

        return Optional.empty();
    }

    @Override
    public void addOverlay(Sprite sprite) {

        // Allow user to ignore collision overlay for their specified sprite
        if (sprite.isAllowCollisionDiagnostic()) {

            /*
            Checkout a pre-instantiated box.
            Set the box to the sprites values.
            The collision will automatically draw any checked boxes.
            (note: the box only gets drawn once, then added back to the pool)
             */
            Optional<RectF> holder = checkoutRectF();
            holder.ifPresent(sprite::getRectF);
        }
    }

    @Override
    public boolean isEnabled() {
        return isEnabled;
    }

    @Override
    public void setEnabled(boolean isEnabled) {
        this.isEnabled = isEnabled;
    }

    @Override
    public void draw(RenderHandler renderer) {

        // Set the rect color
        renderer.getPaint().setColor(rectColor);

        // Draw each rectangle
        for (int i = 0; i <= currentIndex; i++) {

            renderer.getCanvas().drawRect(boundsPool.get(i), renderer.getPaint());
        }

        renderer.getPaint().reset();

        // Reset the pool
        currentIndex = 0;
    }

    @Override
    public int getLayerDepth() {
        return 10;
    }

    @Override
    public boolean isDrawable() {
        return isEnabled;
    }
}
