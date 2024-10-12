package dev.suprseed.Engine.Core.MainView.GameProcessor.Loop;

import android.os.Build;
import android.view.Display;
import android.view.Surface;

import java.util.Arrays;

import dev.suprseed.Engine.Core.ErrorLogger.CentralLogger;
import dev.suprseed.Engine.Core.ErrorLogger.ErrorType;
import dev.suprseed.Engine.Core.MainView.EngineSettings.BaseConfig;
import dev.suprseed.Engine.Core.MainView.GameProcessor.Render.Coordinates.LocationScaler;
import dev.suprseed.Engine.Core.System.LogicSystem;
import dev.suprseed.Engine.Core.System.RenderSystem;

public class LoopManager implements RunnableConfig<GameView> {

    private RefreshTypes refreshSpeed;
    private LogicRates logicRate;

    private int frameCounter = 0;
    private int refreshMultiple = 0;
    private int logicMultiple = 0;

    private boolean softPause = false;
    private boolean hardPause = false;

    // Dependencies
    private final LocationScaler locationScaler;


    // TODO: This is temporary. Figure out a better way for client to get loop manager
    //  to handle pausing behavior
    public static LoopManager loopy = null;

    // Constructor
    public LoopManager(BaseConfig<RunnableConfig<GameView>> config, LocationScaler locationScaler) {

        config.applySettings(this);

        this.locationScaler = locationScaler;

        loopy = this;
    }



    @Override
    public void initLoop(GameView gameView){

        CentralLogger.getInstance().logMessage(ErrorType.INFO, "Initializing loop config...");

        // TODO: Figure out a more general way to set the refresh and logic rate
        //  Currently, only 60 and 120 fps are supported. Find a way to allow more options
        //  such as 90, 144, and 240 hz without causing a logic-refresh mis-match.


        // TODO: Refactor these methods out into separate classes
        initRefreshSpeed(gameView);
        initLogicSpeed();
    }

    private void initRefreshSpeed(GameView gameView) {

        // Display supported display refresh modes
        Display.Mode[] modes = gameView.getDisplay().getSupportedModes();

        CentralLogger.getInstance().logMessage(ErrorType.INFO, "Supported refresh rates:");
        Arrays.stream(modes).forEach(y -> CentralLogger.getInstance().logMessage(ErrorType.INFO, "-> " + y.getRefreshRate()));


        // Set the refresh rate of the display if possible
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {

            // OPTIMIZE: This is not dependable!
            //  Replace this with an instance of 'DisplayRefreshHandler'!
            gameView.getHolder().getSurface().setFrameRate(refreshSpeed.getHertz(), Surface.FRAME_RATE_COMPATIBILITY_DEFAULT);

            /*

             */

            CentralLogger.getInstance().logMessage(ErrorType.INFO, "The refresh rate has been set. RefreshRate: " + refreshSpeed);

        }else{

            CentralLogger.getInstance().logMessage(ErrorType.WARNING, "Cannot set the refresh rate! Android version below 'R'! Reverting to 60hz default value!");
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

            CentralLogger.getInstance().logMessage(ErrorType.WARNING, "The logicRate is less than the refreshSpeed! FPS will be locked to the logicRate!");

            // Verify multiplicity
            if(refreshSpeed.getHertz() % logicRate.getTickRate() == 0){

                // The number of frames the logic should skip
                refreshMultiple = refreshSpeed.getHertz() / logicRate.getTickRate();

            }else{

                CentralLogger.getInstance().logMessage(ErrorType.CONFIG_ERROR, "The given refresh rate (" + refreshSpeed.getHertz() + ") is not a multiple of the logic rate (" + logicRate.getTickRate() + ")!");
                throw new RuntimeException();
            }

        }else if(logicRate.getTickRate() > refreshSpeed.getHertz()) { // logicRate > refreshSpeed

            CentralLogger.getInstance().logMessage(ErrorType.WARNING, "The logicRate is greater than the refreshSpeed! Lag is more likely to occur!");

            // Verify multiplicity
            if(logicRate.getTickRate() % refreshSpeed.getHertz() == 0){

                // Number of times the logic should run in one frame
                logicMultiple = logicRate.getTickRate() / refreshSpeed.getHertz();

            }else{

                CentralLogger.getInstance().logMessage(ErrorType.CONFIG_ERROR, "The given logic rate (" + logicRate.getTickRate() + ") is not a multiple of the refresh rate (" + refreshSpeed.getHertz() + ") !");
                throw new RuntimeException();
            }
        }


        // logicRate == refreshSpeed
        // Do nothing


        // Set the coordinate scalar based on logic rate
        locationScaler.setLocationScaleRatio(logicRate);


        CentralLogger.getInstance().logMessage(ErrorType.INFO, "The logic rate has been set. LogicRate: " + logicRate);
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
                runLogic();
            }

            // Run clients drawing code
            RenderSystem.getInstance().draw(gameView.renderer);


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
    private void runLogic(){

        // Number of times to run logic based on logic tick rate
        // (logic rate is greater than refresh rate)
        if(logicMultiple > 0){

            for(int i = 0; i < logicMultiple; i++){

                // Run client logic code
                //gameView.logicLoop();
                LogicSystem.getInstance().update();
            }
        }else

        // Number of frames to skip
        // (logic rate is less than refresh rate)
        if(refreshMultiple > 0){

            // Track which frame the logic should run
            if(frameCounter == refreshMultiple){

                // Run client logic code
                //gameView.logicLoop();
                LogicSystem.getInstance().update();

                frameCounter = 0;
            }

            frameCounter++;
        }


        // Ignore frame pacing
        else{

            // Run client logic code
            LogicSystem.getInstance().update();
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
    public void setSoftPause(boolean pause) {
        softPause = pause;
    }

    @Override
    public void toggleSoftPause() {
        softPause = !softPause;
    }

    @Override
    public void setRefreshSpeed(RefreshTypes refreshSpeed) {
        this.refreshSpeed = refreshSpeed;
    }

    @Override
    public void setLogicRate(LogicRates logicRate) {
        this.logicRate = logicRate;
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