package com.cruntchy.suprseed.MainView.GameViewBuilder;

import android.os.Build;
import android.view.View;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;

public class DefaultWindowConfig implements WindowConfigurator {

    /**
     * If set to true, a black bar will be displayed at the top of the screen
     * which will hide the phones notch.
     */
    private boolean hideNotch;

    /**
     * If set to true, the phone will be allowed to sleep if the screen has not been touched
     * after some time.
     */
    private boolean allowSleep;



    // Constructor
    public DefaultWindowConfig(boolean hideNotch, boolean allowSleep){

        this.hideNotch = hideNotch;
        this.allowSleep = allowSleep;
    }


    /**
     * This method initializes the app to go fullscreen as well as sets additional
     * window parameters.
     * @param mainActivity The activity whose window setting will be changed
     */
    @Override
    public void applyWindowSettings(AppCompatActivity mainActivity){

        // Hide nave and status bars
        setWindowValues(mainActivity);

        //Keep screen from sleeping
        if(!allowSleep){
            mainActivity.getWindow().setFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON, WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        }

        // Go full screen ignoring notch
        if (!hideNotch && Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            mainActivity.getWindow().getAttributes().layoutInDisplayCutoutMode = WindowManager.LayoutParams.LAYOUT_IN_DISPLAY_CUTOUT_MODE_SHORT_EDGES;
        }
    }

    /**
     * This method makes the app go fullscreen. The 'notification bar' and
     * 'navigation bar' will both be hidden.
     */
    private void setWindowValues(AppCompatActivity mainActivity){

        //Hide nav & status bars
        mainActivity.getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
    }

}