package dev.suprseed.Engine;

import android.content.Context;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import dev.suprseed.Engine.Core.MainView.GameProcessor.Loop.SceneStarter;
import dev.suprseed.Engine.Core.MainView.GameViewBuilder.BaseEngineConfigurator;
import dev.suprseed.R;

public abstract class EngineActivity extends AppCompatActivity implements SceneStarter {

    private BaseEngineConfigurator engineConfigurator;

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
     * @param context The application context
     * @return The user specified engine configuration to initialize the engine with
     */
    protected abstract BaseEngineConfigurator loadEngineConfig(Context context);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(loadView());

        // Load the game view
        loadGameView(this.getApplicationContext());
    }

    @Override
    protected void onResume() {
        super.onResume();

        // Re-apply window config
        if (engineConfigurator != null) {
            engineConfigurator.setWindowConfig(this);
        }
    }


    // Load the game view based on builder results
    private void loadGameView(Context context) {

        // Get layout
        ConstraintLayout cLayout = loadViewLayout();

        // Create a builder
        engineConfigurator = loadEngineConfig(context);

        // Apply the window settings
        engineConfigurator.setWindowConfig(this);

        // Add custom view to the layout
        cLayout.addView(engineConfigurator.buildView());
    }
}