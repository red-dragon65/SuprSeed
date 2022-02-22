package com.cruntchy.suprseed.Engine.MainView.GameProcessor.Loop;

import android.os.Build;
import android.view.Display;
import android.view.Surface;


import com.cruntchy.suprseed.Engine.ErrorLogger.CentralLogger;
import com.cruntchy.suprseed.Engine.ErrorLogger.ErrorType;
import com.cruntchy.suprseed.Engine.MainView.GameProcessor.Render.Coordinates.LocationScaler;

import java.util.Arrays;

public class LoopConfig implements RunnableConfig<GameView> {

    private RefreshTypes refreshSpeed;
    private LogicRates logicRate;

    private int frameCounter = 0;
    private int refreshMultiple = 0;
    private int logicMultiple = 0;

    private boolean softPause;
    private boolean hardPause;

    // Dependencies
    private LocationScaler locationScaler;



    // Constructor
    public LoopConfig(RefreshTypes refreshSpeed, LogicRates logicRate, LocationScaler locationScaler){

        this.refreshSpeed = refreshSpeed;
        this.logicRate = logicRate;

        this.locationScaler = locationScaler;
    }



    @Override
    public void initLoop(GameView gameView){

        CentralLogger.logMessage(ErrorType.INFO, "Initializing loop config...");

        // TODO: Figure out a more general way to set the refresh and logic rate
        //  Currently, only 60 and 120 fps are supported. Find a way to allow more options
        //  such as 90, 144, and 240 hz without causing a logic-refresh mis-match.

        initRefreshSpeed(gameView);
        initLogicSpeed();
    }

    private void initRefreshSpeed(GameView gameView){

        // Display supported display refresh modes
        Display.Mode[] modes = gameView.getDisplay().getSupportedModes();

        CentralLogger.logMessage(ErrorType.INFO, "Supported refresh rates:");
        Arrays.stream(modes).forEach(y->CentralLogger.logMessage(ErrorType.INFO, "-> " + y.getRefreshRate()));


        // Set the refresh rate of the display if possible
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.R){

            // TODO: Verify that this is dependable!!!!
            gameView.getHolder().getSurface().setFrameRate(refreshSpeed.getHertz(), Surface.FRAME_RATE_COMPATIBILITY_DEFAULT);

            /*

             */

            CentralLogger.logMessage(ErrorType.INFO, "The refresh rate has been set. RefreshRate: " + refreshSpeed);

        }else{

            CentralLogger.logMessage(ErrorType.WARNING, "Cannot set the refresh rate! Android version below 'R'! Reverting to 60hz default value!");
            refreshSpeed = RefreshTypes.SIXTY_FPS;
        }
    }

    private void initLogicSpeed(){

        /*
        Verify speeds make sense

        Calculate how many times to call logic loop
        - Less: Every other frame
        - More: Twice per frame

         */


        if(logicRate.getTickRate() < refreshSpeed.getHertz()){ // logicRate < refreshSpeed

            CentralLogger.logMessage(ErrorType.WARNING, "The logicRate is less than the refreshSpeed! FPS will be locked to the logicRate!");

            // Verify multiplicity
            if(refreshSpeed.getHertz() % logicRate.getTickRate() == 0){

                // The number of frames the logic should skip
                refreshMultiple = refreshSpeed.getHertz() / logicRate.getTickRate();

            }else{

                CentralLogger.logMessage(ErrorType.CONFIG_ERROR, "The given refresh rate (" + refreshSpeed.getHertz() + ") is not a multiple of the logic rate (" + logicRate.getTickRate() + ")!");
                throw new RuntimeException();
            }

        }else if(logicRate.getTickRate() > refreshSpeed.getHertz()) { // logicRate > refreshSpeed

            CentralLogger.logMessage(ErrorType.WARNING, "The logicRate is greater than the refreshSpeed! Lag is more likely to occur!");

            // Verify multiplicity
            if(logicRate.getTickRate() % refreshSpeed.getHertz() == 0){

                // Number of times the logic should run in one frame
                logicMultiple = logicRate.getTickRate() / refreshSpeed.getHertz();

            }else{

                CentralLogger.logMessage(ErrorType.CONFIG_ERROR, "The given logic rate (" + logicRate.getTickRate() + ") is not a multiple of the refresh rate (" + refreshSpeed.getHertz() + ") !");
                throw new RuntimeException();
            }
        }


        // logicRate == refreshSpeed
        // Do nothing


        // Set the coordinate scalar based on logic rate
        locationScaler.setLocationScaleRatio(logicRate);


        CentralLogger.logMessage(ErrorType.INFO, "The logic rate has been set. LogicRate: " + logicRate);
    }





    @Override
    public void run(GameView gameView){

        if(hardPause){

            // Stop logic and drawing

        } else{

            if(softPause){

                // Stop logic but allow drawing

            }else{

                // Run the game logic
                runLogic(gameView);
            }

            // Run clients drawing code
            gameView.drawingLoop();


            // Clear out old contents of screen
            gameView.invalidate();
        }


        /*
        // Simplified form of above code
        if(!hardPause){

            if(!softPause){

                gameView.logicLoop();;
            }

            gameView.drawingLoop();
        }
        */
    }


    // Handle logic run rate (scaled relative to refresh rate)
    private void runLogic(GameView gameView){

        // Number of times to run logic based on logic tick rate
        // (logic rate is greater than refresh rate)
        if(logicMultiple > 0){

            for(int i = 0; i < logicMultiple; i++){

                // Run client logic code
                gameView.logicLoop();
            }
        }

        // Number of frames to skip
        // (logic rate is less than refresh rate)
        if(refreshMultiple > 0){

            // Track which frame the logic should run
            if(frameCounter == refreshMultiple){

                // Run client logic code
                gameView.logicLoop();
            }

            frameCounter++;
        }
    }

    @Override
    public boolean isHardPause(){
        return hardPause;
    }

    @Override
    public boolean isSoftPause(){
        return softPause;
    }

    @Override
    public void setHardPause(boolean pause){

        hardPause = pause;
    }

    @Override
    public void setSoftPause(boolean pause){
        softPause = pause;
    }

    @Override
    public void toggleSoftPause(){
        softPause = !softPause;
    }

}


/*

// This is how in game pausing can be used by the client

pausedButton.touched(){

    RunnableConfig.toggleLoop();

}


gameView.focusChanged(){

    RunnableConfig.pauseLoop();
}

gameView.hasFocus(){

    RunnableConfig.resumeLoop();
}

 */