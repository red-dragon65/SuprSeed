package com.cruntchy.suprseed.Engine.MainView.GameProcessor.BetterScene;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Resources;

public interface RootScene {

    void initStartingState(Context context, Resources res, SharedPreferences gameData);
}
