package com.cruntchy.suprseed.Engine.Scenes;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Resources;

public interface AppInfo {

    Context getContext();
    Resources getResources();
    SharedPreferences getSharedPreferences();
}
