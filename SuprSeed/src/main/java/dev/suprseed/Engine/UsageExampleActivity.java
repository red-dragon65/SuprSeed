package dev.suprseed.Engine;

import android.content.Context;
import android.view.View;

import androidx.constraintlayout.widget.ConstraintLayout;

import dev.suprseed.Engine.Core.MainView.GameViewBuilder.BaseEngineConfigurator;
import dev.suprseed.Engine.Core.Scenes.SceneHeirarchy.RootScene;

public class UsageExampleActivity extends EngineActivity{

    RootScene mainScene;

    @Override
    protected View loadView() {
        //return setContentView(R.layout.activity_main);
        return null;
    }

    @Override
    protected ConstraintLayout loadViewLayout() {
        //return findViewById(R.id.game_layoupt);
        return null;
    }

    @Override
    protected BaseEngineConfigurator loadConfig(Context context) {
        return new DefaultEngineConfiguration(context, mainScene);
    }
}
