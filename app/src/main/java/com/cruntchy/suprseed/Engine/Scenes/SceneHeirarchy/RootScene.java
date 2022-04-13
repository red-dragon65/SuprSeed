package com.cruntchy.suprseed.Engine.Scenes.SceneHeirarchy;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Resources;

public interface RootScene {

    void initStartingState(Context context, Resources res, SharedPreferences gameData);
}
