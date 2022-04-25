package com.cruntchy.suprseed.Engine;

import android.content.Context;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.cruntchy.suprseed.Client.ClientEngineConfigurator;
import com.cruntchy.suprseed.Engine.Core.MainView.GameViewBuilder.BaseEngineConfigurator;
import com.cruntchy.suprseed.R;

public class MainActivity extends AppCompatActivity {

    private BaseEngineConfigurator engineConfigurator;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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
    private void loadGameView(Context context){

        // Get layout
        ConstraintLayout cLayout = findViewById(R.id.game_layout);

        // Create a builder
        engineConfigurator = new ClientEngineConfigurator(context);

        // Apply the window settings
        engineConfigurator.setWindowConfig(this);

        // Add custom view to the layout
        cLayout.addView(engineConfigurator.buildView());
    }
}