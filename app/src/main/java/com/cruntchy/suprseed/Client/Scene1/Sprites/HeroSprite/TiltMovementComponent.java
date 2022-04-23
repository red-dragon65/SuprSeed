package com.cruntchy.suprseed.Client.Scene1.Sprites.HeroSprite;

import android.content.Context;

import com.cruntchy.suprseed.Engine.InputHandler.Sensors.Accelerometer;
import com.cruntchy.suprseed.Engine.SpriteObjects.DefaultComponents.Component;
import com.cruntchy.suprseed.Engine.SpriteObjects.SpriteBase.Sprite;

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
