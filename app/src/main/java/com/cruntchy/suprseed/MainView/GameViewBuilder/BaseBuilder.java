package com.cruntchy.suprseed.MainView.GameViewBuilder;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Resources;

import com.cruntchy.suprseed.MainView.GameProcessor.Loop.GameView;

public abstract class BaseBuilder {

    protected Context context;
    protected Resources res;
    protected SharedPreferences gameData;


    // Constructor
    public BaseBuilder(Context context, Resources res, SharedPreferences gameData){

        this.context = context;
        this.res = res;
        this.gameData = gameData;

    }

    public abstract GameView getView();
}
