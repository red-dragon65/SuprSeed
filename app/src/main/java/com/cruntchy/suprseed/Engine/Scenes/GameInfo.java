package com.cruntchy.suprseed.Engine.Scenes;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Resources;

public class GameInfo implements AppInfo {

    private Context context;
    private Resources res;
    private SharedPreferences gameData;

    public GameInfo(Context context, Resources res, SharedPreferences gameData){

        this.context = context;
        this.res = res;
        this.gameData = gameData;
    }

    @Override
    public Context getContext(){
        return context;
    }

    @Override
    public Resources getResources(){
        return res;
    }

    @Override
    public SharedPreferences getSharedPreferences() {
        return gameData;
    }
}
