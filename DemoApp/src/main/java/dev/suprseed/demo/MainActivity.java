package dev.suprseed.demo;

import android.content.Context;

import androidx.constraintlayout.widget.ConstraintLayout;

import dev.suprseed.Engine.Core.MainView.GameViewBuilder.BaseEngineConfigurator;
import dev.suprseed.Engine.Core.Scenes.SceneHeirarchy.RootScene;
import dev.suprseed.Engine.EngineActivity;
import dev.suprseed.demo.Subscenes.GameDemoMainScene;

public class MainActivity extends EngineActivity {

    @Override
    protected int loadView() {
        return R.layout.activity_main;
    }

    @Override
    protected ConstraintLayout loadViewLayout() {
        return findViewById(R.id.game_layout);
    }

    @Override
    protected BaseEngineConfigurator loadEngineConfig(Context context) {
        //return new DefaultEngineConfiguration(context, this);

        // You can specify your custom engine configuration like this
        return new ClientEngineConfigurator(context);
    }

    @Override
    public void initStartingState(RootScene rootScene) {

        // Use this to get the game save data
        //context.getSharedPreferences("GAME_DATA", MODE_PRIVATE);

        // Use this to get the asset resources
        //context.getResources();

        // Starts the scene 1 example package
        new GameDemoMainScene(rootScene, "TopScene");
    }
}
