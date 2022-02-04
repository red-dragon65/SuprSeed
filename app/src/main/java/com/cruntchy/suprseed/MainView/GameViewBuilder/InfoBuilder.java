package com.cruntchy.suprseed.MainView.GameViewBuilder;

import com.cruntchy.suprseed.InputHandler.Sensors.DeviceSensor;
import com.cruntchy.suprseed.InputHandler.TouchInput.TouchHandler;


// Returns the default instantiated objects used for configuring the engine
public interface InfoBuilder <T>{

    // TODO: Finish me!

    public DeviceSensor<T> getDefaultSensor();

    public TouchHandler getDefaultTouchHandler();


}
