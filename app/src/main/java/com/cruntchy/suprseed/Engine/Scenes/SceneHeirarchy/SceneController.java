package com.cruntchy.suprseed.Engine.Scenes.SceneHeirarchy;

import com.cruntchy.suprseed.Engine.Scenes.AppInfo;
import com.cruntchy.suprseed.Engine.Scenes.SceneStrategy.SceneChangeStrategy;
import com.cruntchy.suprseed.Engine.SpriteObjects.Register.ListRegister;

public interface SceneController<T> {

    void changeScene(SceneChangeStrategy<T> strategy, T oldScene, String... sceneId);

    ListRegister<T> getRegister();

    AppInfo getGameInfo();
}
