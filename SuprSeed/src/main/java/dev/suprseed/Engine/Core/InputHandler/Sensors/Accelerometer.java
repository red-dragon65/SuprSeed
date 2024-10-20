package dev.suprseed.Engine.Core.InputHandler.Sensors;

import static android.content.Context.SENSOR_SERVICE;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;

public class Accelerometer implements SensorEventListener, DeviceSensor<Float[]> {

    private static final Float[] tiltValues = new Float[2];

    // Variables for tilting
    private static SensorManager mSensorManager;
    private static Sensor mAccelerometer;


    // Constructor
    public Accelerometer(Context context) {

        // Initialize variables for tilt
        mSensorManager = (SensorManager) context.getSystemService(SENSOR_SERVICE);
        mAccelerometer = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        mSensorManager.registerListener(this, mAccelerometer, SensorManager.SENSOR_DELAY_GAME);

        // Set starting tilt value values
        tiltValues[0] = 0f;
        tiltValues[1] = 0f;
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {

        // Ignore non-accelerometer input
        if (sensorEvent.sensor.getType() != Sensor.TYPE_ACCELEROMETER) {
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
