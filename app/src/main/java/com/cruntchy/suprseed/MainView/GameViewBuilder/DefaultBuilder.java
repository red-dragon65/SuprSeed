package com.cruntchy.suprseed.MainView.GameViewBuilder;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Resources;

import com.cruntchy.suprseed.InputHandler.Sensors.DeviceSensor;
import com.cruntchy.suprseed.InputHandler.TouchInput.TouchHandler;
import com.cruntchy.suprseed.MainView.GameProcessor.GameView;

public class DefaultBuilder extends BaseBuilder implements InfoBuilder<Float[]>{


    // Constructor
    public DefaultBuilder(Context context, Resources res, SharedPreferences gameData){
        super(context, res, gameData);
    }


    @Override
    public GameView getView() {

        // TODO: Initialize the game view

        /*
        GameView defaultView = new GameView(this.getDefaultTouchHandler, this.getDefaultSensor);

        return defaultView;
         */


        return null;
    }


    @Override
    public DeviceSensor<Float[]> getDefaultSensor() {

        // TODO: Initialize the default modules state

        return null;
    }

    @Override
    public TouchHandler getDefaultTouchHandler() {

        // TODO: Initialize the default modules state

        return null;
    }

}
