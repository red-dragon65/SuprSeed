package dev.suprseed.demo.Sprites.HeroSprite.HeroComponents;

import android.content.Context;

import dev.suprseed.Engine.Core.InputHandler.Sensors.Accelerometer;
import dev.suprseed.Engine.Core.SpriteObjects.DefaultComponents.Component;
import dev.suprseed.Engine.Core.SpriteObjects.SpriteBase.Sprite;

public class TiltMovementComponent implements Component {

    private final Sprite sprite;

    private final Accelerometer tiltometer;
    private final float tiltAmplification = -0.80f;
    private float xValue = 0;

    // Constructor
    public TiltMovementComponent(Sprite sprite, Context context) {

        this.sprite = sprite;
        tiltometer = new Accelerometer(context);
    }

    @Override
    public void update() {

        // Process phone tilt value for horizontal movement
        tilt();
    }

    private void tilt(){

        // Get tilt value
        xValue = tiltometer.getSensorData()[0];
        xValue *= tiltAmplification;

        // Set users velocity
        sprite.setxVel(xValue);
    }
}
