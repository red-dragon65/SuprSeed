package dev.suprseed.demo.Engine.Core.MainView.EngineSettings;

import android.annotation.SuppressLint;
import android.content.pm.ActivityInfo;
import android.os.Build;
import android.view.View;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;

public class ViewConfig extends BaseConfig<AppCompatActivity> {


    // Enforce only one setting across instance usages
    private static boolean showNotch;
    private static boolean noSleep;
    private static boolean noUI;
    private static boolean landscape;


    // Constructor
    // Allows getting the settings with whatever their default value is
    public ViewConfig() {

        initSettings();
    }


    // Constructor
    // Updates all instances settings data to whatever is passed in
    public ViewConfig(boolean showNotch, boolean noSleep, boolean noUI, boolean landscape) {

        ViewConfig.showNotch = showNotch;
        ViewConfig.noSleep = noSleep;
        ViewConfig.noUI = noUI;
        ViewConfig.landscape = landscape;

        initSettings();
    }


    private void initSettings() {

        //Notch
        settings.add(new Configurable<AppCompatActivity>() {
            @Override
            public void applySettings(AppCompatActivity inputObject) {

                // Go full screen ignoring notch
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
                    inputObject.getWindow().getAttributes().layoutInDisplayCutoutMode = WindowManager.LayoutParams.LAYOUT_IN_DISPLAY_CUTOUT_MODE_SHORT_EDGES;
                }
            }

            @Override
            public boolean active() {
                return ViewConfig.showNotch;
            }

            @Override
            public String getId() {
                return "notch";
            }
        });


        // Sleep
        settings.add(new Configurable<AppCompatActivity>() {
            @Override
            public void applySettings(AppCompatActivity inputObject) {

                //Keep screen from sleeping
                inputObject.getWindow().setFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON, WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

            }

            @Override
            public boolean active() {
                return ViewConfig.noSleep;
            }

            @Override
            public String getId() {
                return "sleep";
            }
        });


        // UI visibility
        settings.add(new Configurable<AppCompatActivity>() {
            @Override
            public void applySettings(AppCompatActivity inputObject) {

                //Hide nav & status bars
                inputObject.getWindow().getDecorView().setSystemUiVisibility(
                        View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                                | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                                | View.SYSTEM_UI_FLAG_FULLSCREEN
                                | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
            }

            @Override
            public boolean active() {
                return ViewConfig.noUI;
            }

            @Override
            public String getId() {
                return "ui";
            }
        });


        // Orientation
        settings.add(new Configurable<AppCompatActivity>() {

            @SuppressLint("SourceLockedOrientationActivity")
            @Override
            public void applySettings(AppCompatActivity inputObject) {

                if (ViewConfig.landscape) {
                    inputObject.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
                }
            }

            @Override
            public boolean active() {
                return ViewConfig.landscape;
            }

            @Override
            public String getId() {
                return "orientation";
            }
        });
    }
}
