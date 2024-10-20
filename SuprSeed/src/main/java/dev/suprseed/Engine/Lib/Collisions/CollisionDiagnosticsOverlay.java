package dev.suprseed.Engine.Lib.Collisions;

import android.graphics.Color;
import android.graphics.RectF;

import java.util.ArrayList;
import java.util.List;

import dev.suprseed.Engine.Core.MainView.GameProcessor.Render.Graphics.RenderHandler;
import dev.suprseed.Engine.Core.System.Registerables.IRenderableAndILayerable;
import dev.suprseed.Engine.Core.System.RenderSystem;

public class CollisionDiagnosticsOverlay implements IRenderableAndILayerable, RectCollector {

    // Eager loading singleton
    private static final CollisionDiagnosticsOverlay INSTANCE = new CollisionDiagnosticsOverlay();
    private final List<RectF> collisionBounds;
    private boolean isEnabled = false;


    // Constructor
    // Private to prevent client use of 'new' keyword
    private CollisionDiagnosticsOverlay() {
        collisionBounds = new ArrayList<>();

        RenderSystem.getInstance().getImageRegister().registerObject(this);
    }

    public static CollisionDiagnosticsOverlay getInstance() {
        return INSTANCE;
    }

    @Override
    public void addRect(RectF input) {

        // See if this is allowed to run
        if (isEnabled) {
            collisionBounds.add(input);
        }
    }

    @Override
    public void enable() {
        isEnabled = true;
    }

    @Override
    public void disable() {
        isEnabled = false;
    }

    @Override
    public void draw(RenderHandler renderer) {

        // Check if rectangles exist
        if (!(collisionBounds != null && collisionBounds.size() > 0)) {

            return;
        }

        // Set the rect color
        renderer.getPaint().setColor(Color.BLUE);

        // Draw each rectangle
        for (RectF r : collisionBounds) {

            renderer.getCanvas().drawRect(r, renderer.getPaint());
        }

        // Clear out all old rectangles
        collisionBounds.clear();
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
