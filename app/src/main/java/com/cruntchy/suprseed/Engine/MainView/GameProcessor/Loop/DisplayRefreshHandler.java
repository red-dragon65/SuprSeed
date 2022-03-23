package com.cruntchy.suprseed.Engine.MainView.GameProcessor.Loop;

import android.hardware.display.DisplayManager;
import android.os.Build;
import android.view.Display;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;


// TODO: Finish me!
public class DisplayRefreshHandler {


    public DisplayRefreshHandler(AppCompatActivity activity) {

        // TODO: Inject this from another class
        DisplayManager.DisplayListener refreshListener = new DisplayManager.DisplayListener() {

            @Override
            public void onDisplayAdded(int displayId) {

            }

            @Override
            public void onDisplayRemoved(int displayId) {

            }

            @Override
            public void onDisplayChanged(int displayId) {

                // TODO: Reset the loop managers refresh/tick rates if the refresh rate
                //  of the screen gets changed

            }
        };

    }


    // TODO: Figure out if the refresh rate requested by the user is
    //  an option for the given display
    public void applyDisplayRefresh(AppCompatActivity inputObject) {

        // Note: This code is not guaranteed to change the displays refresh speed
        //gameView.getHolder().getSurface().setFrameRate(refreshSpeed.getHertz(), Surface.FRAME_RATE_COMPATIBILITY_DEFAULT);


        // This code prevents the phone from over-riding the refresh rate that is set
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {

            Display.Mode m = inputObject.getDisplay().getMode();
            Display.Mode[] modes = inputObject.getDisplay().getSupportedModes();
            WindowManager.LayoutParams lm = new WindowManager.LayoutParams();

            // TODO: Get the correct display mode
            //  The display mode should match the given refresh rate the user has set in the engine
            lm.preferredDisplayModeId = modes[3].getModeId();


            inputObject.getWindow().setAttributes(lm);
        }
    }
}
