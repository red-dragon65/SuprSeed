package com.cruntchy.suprseed.Client.Scene1.Sprites.HeroSprite;

import com.cruntchy.suprseed.Engine.MainView.GameProcessor.Render.Coordinates.LocationScaler;
import com.cruntchy.suprseed.Engine.MainView.GameProcessor.Render.Coordinates.LocationTemporalScaler;
import com.cruntchy.suprseed.Engine.SpriteObjects.DefaultComponents.Movable;
import com.cruntchy.suprseed.Engine.SpriteObjects.SpriteBase.Sprite;

public class FunctionMovement implements Movable {


    private final Sprite sprite;
    private final LocationScaler locationScaler;
    private float timeStep = 0;


    // Constructor
    public FunctionMovement(Sprite sprite) {

        this.sprite = sprite;
        locationScaler = new LocationTemporalScaler();
    }


    @Override
    public void move() {

        // Update location based on a function
        float xVal = (0.1f) * timeStep + sprite.getX();
        float yVal = (0.0025f) * (timeStep * timeStep) + (-0.5f) * timeStep + sprite.getY();

        // Update sprite location
        sprite.setX(xVal);
        sprite.setY(yVal);

        // Update function time
        timeStep += locationScaler.getLocationScaleRatio();
    }
}
