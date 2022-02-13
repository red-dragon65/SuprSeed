package com.cruntchy.suprseed;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.os.Bundle;

import com.cruntchy.suprseed.ErrorLogger.CatLogger;
import com.cruntchy.suprseed.ErrorLogger.CentralLogger;
import com.cruntchy.suprseed.ErrorLogger.Logable;
import com.cruntchy.suprseed.Z_ClientGame.EngineSetup.ClientEngineBuilder;
import com.cruntchy.suprseed.MainView.GameViewBuilder.BaseEngineBuilder;

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
        engineBuilder = new ClientEngineBuilder(context, res, saveData);

        // Apply the window settings
        engineBuilder.setWindowConfig(this);

        // Add custom view to the layout
        cLayout.addView(engineBuilder.getView());
    }
}