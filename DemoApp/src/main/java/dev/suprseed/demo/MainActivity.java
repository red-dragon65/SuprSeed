package dev.suprseed.demo;

import android.os.Bundle;

import androidx.lifecycle.Lifecycle;

import dev.suprseed.Engine.Core.MainView.GameViewBuilder.BaseEngineConfigurator;
import dev.suprseed.Engine.Core.Scenes.SceneHeirarchy.RootScene;
import dev.suprseed.Engine.EngineActivity;

public class MainActivity extends EngineActivity {

    public static Lifecycle lifecycleRegistry;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Allow life cycle to be referenced system wide
        lifecycleRegistry = this.getLifecycle();
    }

    @Override
    protected BaseEngineConfigurator loadEngineConfig() {
        //return new DefaultEngineConfiguration(context, this);

        // You can specify your custom engine configuration like this
        return new ClientEngineConfigurator(this.getApplicationContext(), this);
    }

    @Override
    public void initStartingState(RootScene rootScene) {

        // Use this to get the game save data
        //context.getSharedPreferences("GAME_DATA", MODE_PRIVATE);

        // Use this to get the asset resources
        //context.getResources();

        // Starts the scene 1 example package
        //new GamePlayScene(rootScene, "TopScene");
        rootScene.registerScene(new MainScene(this.getApplicationContext(), "topscene"));
    }
}
