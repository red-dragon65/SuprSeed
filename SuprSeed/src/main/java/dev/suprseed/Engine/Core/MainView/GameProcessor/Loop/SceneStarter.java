package dev.suprseed.Engine.Core.MainView.GameProcessor.Loop;


import dev.suprseed.Engine.Core.Scenes.SceneHeirarchy.RootScene;

public interface SceneStarter {

    void setRootScene(RootScene rootScene);

    void initStartingState(RootScene rootScene);
}
