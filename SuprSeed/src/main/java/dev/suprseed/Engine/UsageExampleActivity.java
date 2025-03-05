package dev.suprseed.Engine;

import androidx.constraintlayout.widget.ConstraintLayout;

import dev.suprseed.Engine.Core.MainView.GameViewBuilder.BaseEngineConfigurator;
import dev.suprseed.Engine.Core.Scenes.SceneHeirarchy.RootScene;

public class UsageExampleActivity extends EngineActivity {

    @Override
    protected int loadView() {
        // This should be the main activity of your app
        //return R.layout.activity_main;
        return 0;
    }

    @Override
    protected ConstraintLayout loadViewLayout() {
        // this should should be the id that is set for your main activity
        //return findViewById(R.id.main);
        return null;
    }

    @Override
    protected BaseEngineConfigurator loadEngineConfig() {
        return new DefaultEngineConfiguration(this.getApplicationContext(), this);
    }

    @Override
    public void initStartingState(RootScene rootScene) {

        // Start your game engine here
        //new UserMainScene(this, "SomeSceneId")
    }
}
