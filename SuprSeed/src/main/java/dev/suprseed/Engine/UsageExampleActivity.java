package dev.suprseed.Engine;

import android.content.Context;

import androidx.constraintlayout.widget.ConstraintLayout;

import dev.suprseed.Engine.Core.MainView.GameViewBuilder.BaseEngineConfigurator;
import dev.suprseed.Engine.Core.Scenes.SceneHeirarchy.RootScene;

public class UsageExampleActivity extends EngineActivity {

    RootScene mainScene;

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
    protected BaseEngineConfigurator loadConfig(Context context) {
        return new DefaultEngineConfiguration(context, mainScene);
    }
}
