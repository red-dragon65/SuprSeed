package dev.suprseed.Engine;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import dev.suprseed.Engine.Core.MainView.GameProcessor.Loop.SceneStarter;
import dev.suprseed.Engine.Core.MainView.GameViewBuilder.BaseEngineConfigurator;
import dev.suprseed.Engine.Core.Scenes.SceneHeirarchy.RootScene;
import dev.suprseed.R;

public abstract class EngineActivity extends AppCompatActivity implements SceneStarter {

    private BaseEngineConfigurator engineConfigurator;
    private RootScene rootScene;

    public void setRootScene(RootScene rootScene) {
        this.rootScene = rootScene;
    }

    /**
     * Specify the activity to use for the engine (hint: it's probably 'activity_main')
     * Uses the default game activity xml layout specified as port of the engine.
     *
     * @return The user specified view to load in the 'onCreate()' function
     */
    protected int loadView() {
        return R.layout.game_activity;
    }

    /**
     * Specify the activity's id to use for the engine (hint: it's probably 'main')
     * Uses the default game activity xml layout specified as part of the engine.
     *
     * @return The layout from the user's specified 'View's id
     */
    protected ConstraintLayout loadViewLayout() {
        return findViewById(R.id.game_layout);
    }

    /**
     * Specify the configuration settings for the engine to use
     *
     * @return The user specified engine configuration to initialize the engine with
     */
    protected abstract BaseEngineConfigurator loadEngineConfig();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(loadView());

        // Load the game view
        loadGameView();
    }

    @Override
    protected void onResume() {
        super.onResume();

        // Re-apply window config
        if (engineConfigurator != null) {
            engineConfigurator.setWindowConfig(this);
        }
    }

    @Override
    protected void onDestroy() {

        // Destroy all registered user scenes, bottom up
        if (rootScene != null) {
            rootScene.onDestroy();
        }
        super.onDestroy();
    }

    // Load the game view based on builder results
    private void loadGameView() {

        // Get layout
        ConstraintLayout cLayout = loadViewLayout();

        // Create a builder
        engineConfigurator = loadEngineConfig();

        // Apply the window settings
        engineConfigurator.setWindowConfig(this);

        // Add custom view to the layout
        cLayout.addView(engineConfigurator.buildView());
    }
}