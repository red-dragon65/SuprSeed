package com.cruntchy.suprseed.Engine.MainView.GameViewFactory;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public abstract class BaseEngineConfigurator {

    protected Context context;
    protected Resources res;
    protected SharedPreferences gameData;


    // Constructor
    public BaseEngineConfigurator(Context context, Resources res, SharedPreferences gameData) {

        this.context = context;
        this.res = res;
        this.gameData = gameData;
    }


    public abstract View buildView();

    public abstract void setWindowConfig(AppCompatActivity mainActivity);

}
