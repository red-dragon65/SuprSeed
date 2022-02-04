package com.cruntchy.suprseed;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;

import com.cruntchy.suprseed.Z_ClientGame.ClientBuilder;
import com.cruntchy.suprseed.MainView.GameViewBuilder.BaseBuilder;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Load the window settings
        initWindowSettings(false, false);

        // Load the game view
        loadGameView(this.getApplicationContext(), this.getResources(), this.getSharedPreferences("GAME_DATA", MODE_PRIVATE));

    }

    @Override
    protected void onResume(){
        super.onResume();

        //Hide nav & status bars.
        setWindowValues();
    }



    /**
     * This method initializes the app to go fullscreen as well as sets additional
     * window parameters.
     * @param hideNotch If set to true, a black bar will display at the top of the screen
     *                  which will hide the phones notch.
     * @param allowSleep If set to true, the phone will be allowed to sleep if screen has
     *                   not been touched after some time.
     */
    private void initWindowSettings(boolean hideNotch, boolean allowSleep){

        //Hide nav & status bars
        setWindowValues();

        //Keep screen from sleeping
        if(!allowSleep){
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON, WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        }

        // Go full screen ignoring notch
        if (!hideNotch && Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            getWindow().getAttributes().layoutInDisplayCutoutMode = WindowManager.LayoutParams.LAYOUT_IN_DISPLAY_CUTOUT_MODE_SHORT_EDGES;
        }
    }

    /**
     * This method makes the app go fullscreen. The 'notification bar' and
     * 'navigation bar' will both be hidden.
     */
    private void setWindowValues(){

        //Hide nav & status bars
        getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
    }


    private void loadGameView(Context context, Resources res, SharedPreferences saveData){

        // Get layout
        ConstraintLayout cLayout = findViewById(R.id.game_layout);

        // Create a builder
        BaseBuilder builder = new ClientBuilder(context, res, saveData);

        // Add custom view to the layout
        cLayout.addView(builder.getView());
    }
}