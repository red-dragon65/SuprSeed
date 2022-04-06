package com.cruntchy.suprseed.Engine.Collisions;

import android.graphics.Color;
import android.graphics.RectF;

import com.cruntchy.suprseed.Engine.MainView.GameProcessor.Render.Graphics.RenderHandler;
import com.cruntchy.suprseed.Engine.SpriteObjects.System.RenderSystem;
import com.cruntchy.suprseed.Engine.SpriteObjects.System.Renderable;

import java.util.ArrayList;
import java.util.List;

public class CollisionDiagnosticsOverlay implements Renderable, RectCollector {

    private final List<RectF> collisionBounds;
    private boolean isEnabled = false;

    // Eager loading singleton
    private static final CollisionDiagnosticsOverlay INSTANCE = new CollisionDiagnosticsOverlay();


    // Constructor
    // Private to prevent client use of 'new' keyword
    private CollisionDiagnosticsOverlay() {
        collisionBounds = new ArrayList<>();

        RenderSystem.getInstance().registerRenderSprite(this);
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

        // See if this is allowed to run
        if (!isEnabled) {
            return;
        }

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
}
