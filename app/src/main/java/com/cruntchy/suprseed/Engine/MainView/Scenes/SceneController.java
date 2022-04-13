package com.cruntchy.suprseed.Engine.MainView.Scenes;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Resources;

import com.cruntchy.suprseed.Engine.SpriteObjects.Register.ListRegister;
import com.cruntchy.suprseed.Engine.SpriteObjects.Register.ObjectRegister;

import java.util.List;

public interface SceneController<T> {

    void changeScene(SceneChangeStrategy<T> strategy, T oldScene, String... sceneId);

    ListRegister<T> getRegister();

    AppInfo getGameInfo();
}
