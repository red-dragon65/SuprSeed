package com.cruntchy.suprseed.Engine.MainView.GameViewBuilder;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Resources;

import androidx.appcompat.app.AppCompatActivity;

import com.cruntchy.suprseed.Engine.MainView.GameProcessor.Loop.GameView;

public abstract class BaseEngineBuilder {

    protected Context context;
    protected Resources res;
    protected SharedPreferences gameData;


    // Constructor
    public BaseEngineBuilder(Context context, Resources res, SharedPreferences gameData){

        this.context = context;
        this.res = res;
        this.gameData = gameData;

    }

    public abstract GameView getView();

    public abstract void setWindowConfig(AppCompatActivity mainActivity);
}
