package com.cruntchy.suprseed.Engine.MainView.GameProcessor.Loop;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Resources;

public interface SceneController {

    void initStartingState(Context context, Resources res, SharedPreferences gameData);

    void changeScene(Scene oldScene, String sceneId);

}
