package com.cruntchy.suprseed.InputHandler.Sensors;

import static android.content.Context.SENSOR_SERVICE;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Accelerometer implements SensorEventListener, DeviceSensor<Float[]> {

    private Float[] tiltValues = new Float[2];

    // Variables for tilting
    private SensorManager mSensorManager;
    private Sensor mAccelerometer;


    // Constructor
    public Accelerometer(Context context){

        // Initialize variables for tilt
        mSensorManager = (SensorManager) context.getSystemService(SENSOR_SERVICE);
        mAccelerometer = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        mSensorManager.registerListener(this, mAccelerometer, SensorManager.SENSOR_DELAY_GAME);
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {

        // Ignore non-accelerometer input
        if(sensorEvent.sensor.getType() != Sensor.TYPE_ACCELEROMETER){
            return;
        }

        // Get the accelerometers value
        tiltValues[0] = sensorEvent.values[0];
        tiltValues[1] = sensorEvent.values[1];
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }


    // Gets the most recent sensor data
    @Override
    public Float[] getSensorData() {

        return tiltValues;
    }
}
