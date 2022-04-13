package com.cruntchy.suprseed.Engine.MainView.Scenes;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Resources;

import com.cruntchy.suprseed.Engine.SpriteObjects.Register.ObjectRegister;

import java.util.List;

public interface SceneController extends ObjectRegister<Scene> {

    void initStartingState(Context context, Resources res, SharedPreferences gameData);

    void changeScene(SceneChangeStrategy strategy, Scene oldScene, String... sceneId);

    Context getContext();
    Resources getResources();
    SharedPreferences getSharedPreferences();

    List<Scene> getScenes();
}
