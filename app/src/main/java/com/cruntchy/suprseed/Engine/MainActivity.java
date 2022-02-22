package com.cruntchy.suprseed.Engine;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.os.Bundle;


import com.cruntchy.suprseed.Engine.ErrorLogger.CatLogger;
import com.cruntchy.suprseed.Engine.ErrorLogger.CentralLogger;
import com.cruntchy.suprseed.Engine.ErrorLogger.Logable;
import com.cruntchy.suprseed.Engine.MainView.GameViewBuilder.BaseEngineBuilder;
import com.cruntchy.suprseed.R;
import com.cruntchy.suprseed.Client.Z_ClientTest_Active.EngineSetup.ClientEngineBuilderTest;

public class MainActivity extends AppCompatActivity {

    // Initialize the logger singleton
    private Logable catLogger = new CatLogger();
    private CentralLogger centralLogger = new CentralLogger(catLogger);

    private BaseEngineBuilder engineBuilder;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Load the game view
        loadGameView(this.getApplicationContext(), this.getResources(), this.getSharedPreferences("GAME_DATA", MODE_PRIVATE));

    }

    @Override
    protected void onResume(){
        super.onResume();

        // Re-apply window config
        if(engineBuilder != null){
            engineBuilder.setWindowConfig(this);
        }
    }



    private void loadGameView(Context context, Resources res, SharedPreferences saveData){

        // Get layout
        ConstraintLayout cLayout = findViewById(R.id.game_layout);

        // Create a builder
        engineBuilder = new ClientEngineBuilderTest(context, res, saveData);

        // Apply the window settings
        engineBuilder.setWindowConfig(this);

        // Add custom view to the layout
        cLayout.addView(engineBuilder.getView());
    }
}