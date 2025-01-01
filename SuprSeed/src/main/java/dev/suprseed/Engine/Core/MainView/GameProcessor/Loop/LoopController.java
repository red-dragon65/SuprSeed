package dev.suprseed.Engine.Core.MainView.GameProcessor.Loop;

import android.view.Display;
import android.view.SurfaceView;

import dev.suprseed.Engine.Core.ErrorLogger.CentralLogger;
import dev.suprseed.Engine.Core.ErrorLogger.ErrorType;
import dev.suprseed.Engine.Core.MainView.EngineSettings.LoopConfig;

public class LoopController<T extends SurfaceView> implements RefreshHandler {

    // The user specified refresh & tick rates (these are the target values)
    private final LoopConfig userLoopConfig;
    // The classes we need to notify of changes
    private final LoopRunnable<T> loopRunner;
    // The engine specified surface view
    private SurfaceView gameView;
    // Hold the actual logic rates and refresh rates the engine running on an actual phone can use
    private LoopConfig actualLoopConfig;
    private LoopTickRateMultiples tickRateMultiples;


    public LoopController(LoopConfig userLoopConfig, LoopRunnable<T> loopRunner) {
        this.userLoopConfig = userLoopConfig;
        this.loopRunner = loopRunner;

        actualLoopConfig = new LoopConfig(userLoopConfig.getRefreshSpeed(), userLoopConfig.getLogicRate(), 1, true);
        tickRateMultiples = new LoopTickRateMultiples();
    }

    // This is here to avoid a dependency loop with the GameView class
    public void setGameView(SurfaceView gameView) {
        this.gameView = gameView;
    }

    /*
    Gets called by:
    - GameView: onDraw()
     */
    @Override
    public void monitorRefreshRate() {

        // Get the current mode of the display
        Display.Mode m = gameView.getDisplay().getMode();

        // See if the current refresh rate is in conflict with the current target refresh rate
        if ((int) m.getRefreshRate() != actualLoopConfig.getRefreshSpeed().getHertz()) {
            updateRefreshRate();
        }
    }


    /*
    Gets called by:
    - GameView: onSizeChanged()
    - GameView: surfaceChanged()
     */
    @Override
    public void updateRefreshRate() {


        // TODO: Figure out a more general way to set the refresh and logic rate
        //  Currently, only 60 and 120 fps are supported. Find a way to allow more options
        //  such as 90, 144, and 240 hz without causing a logic-refresh mis-match.

        // This code prevents the phone from over-riding the refresh rate that is set

        // This will attempt to run the game at the highest refresh rate supported by the phone!

        // Get the current mode of the display
        Display.Mode m = gameView.getDisplay().getMode();

        int refreshRate = (int) m.getRefreshRate();

        if (refreshRate == 60) { // 60hz detected

            // Set target fps
            actualLoopConfig.setRefreshSpeed(RefreshTypes.SIXTY_FPS);
        } else if (refreshRate == 120) { // 120hz detected

            // Set target fps
            actualLoopConfig.setRefreshSpeed(RefreshTypes.ONE_TWENTY_FPS);
        }


        // Try and set the correct screen mode
        //TODO: Implement this
        /*if(actualLoopConfig.getRefreshSpeed() != userLoopConfig.getRefreshSpeed()){

            gameView.getHolder().getSurface().setFrameRate(userLoopConfig.getRefreshSpeed(), Surface.FRAME_RATE_COMPATIBILITY_DEFAULT);

            boolean success = Display.setMode(userLoopConfig.getRefreshSpeed());

            // Honer users config if possible
            if(success){
                actualLoopConfig.setRefreshSpeed(userLoopConfig.getRefreshSpeed());
            }
        }*/


        consolidateRefreshRate();
    }

    /*
    private void initRefreshSpeed(GameView gameView) {

        // Display supported display refresh modes
        Display.Mode[] modes = gameView.getDisplay().getSupportedModes();

        CentralLogger.getInstance().logMessage(ErrorType.INFO, "Getting supported refresh rates...");
        Arrays.stream(modes).forEach(y -> CentralLogger.getInstance().logMessage(ErrorType.INFO, "-> " + y.getRefreshRate() + " hz is supported"));


        // Set the refresh rate of the display if possible
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {

            // OPTIMIZE: This is not dependable!
            //  Replace this with an instance of 'DisplayRefreshHandler'!
            gameView.getHolder().getSurface().setFrameRate(config.getActualRefreshSpeed().getHertz(), Surface.FRAME_RATE_COMPATIBILITY_DEFAULT);

            CentralLogger.getInstance().logMessage(ErrorType.INFO, "The refresh rate has been set. Refresh rate: " + config.getActualRefreshSpeed());

        } else {

            CentralLogger.getInstance().logMessage(ErrorType.WARN, "Cannot set the refresh rate! Android version below 'R'! Reverting to 60hz default value!");
            config.getActualRefreshSpeed(RefreshTypes.SIXTY_FPS);
        }
    }
    */

    private void consolidateRefreshRate() {

        // TODO: Should multiplicity be removed?
        //  This won't work with 90hz refresh rates! for both logic<refresh and logic>refresh, the VelocityScaler must be used to upgrade the logic rate.
        //  This also won't work with unexpected refresh rates like 144hz, 240hz, 180hz, 360hz etc.
        if (actualLoopConfig.getLogicRate().getTickRate() <= actualLoopConfig.getRefreshSpeed().getHertz()) { // logicRate < refreshSpeed

            CentralLogger.getInstance().logMessage(ErrorType.WARN, "The logicRate is less than the refreshSpeed! FPS will be locked to the logicRate!");

            // Verify multiplicity
            if (actualLoopConfig.getRefreshSpeed().getHertz() % actualLoopConfig.getLogicRate().getTickRate() == 0) {

                // Reset data
                tickRateMultiples.setLogicMultiple(0);
                tickRateMultiples.setRefreshMultiple(0);
                VelocityScaler.setVelocityScaler(1);

                if (userLoopConfig.isAccurateTickRate()) { // This will upgrade the user given logic rate

                    // Logic runs 1 to 1 with refresh rate
                    // (the logic rate probably needs to double, so scaler should be half)

                    float scaler = (float) actualLoopConfig.getLogicRate().getTickRate() / actualLoopConfig.getRefreshSpeed().getHertz();

                    // TODO: Figure out why this is needed for the app to run at the same rate as the frame skip
                    // shouldn't the scaler just be 0.5? Not 0.25?
                    scaler /= 2;
                    scaler *= userLoopConfig.getLogicScaleMultiple();

                    // This is MORE computationally expensive, but runs smoother
                    VelocityScaler.setVelocityScaler(scaler);

                } else { // This will honor the users logic rate, but fps will get locked to the logic rate rather than the refresh rate

                    int scaler = actualLoopConfig.getRefreshSpeed().getHertz() / actualLoopConfig.getLogicRate().getTickRate();
                    scaler = (int) (scaler / userLoopConfig.getLogicScaleMultiple());

                    // The number of frames the logic should skip
                    tickRateMultiples.setRefreshMultiple(scaler);
                }

            } else {

                String message = "The given refresh rate (" + actualLoopConfig.getRefreshSpeed().getHertz() + ") is not a multiple of the logic rate (" + actualLoopConfig.getLogicRate().getTickRate() + ")!";

                CentralLogger.getInstance().logMessage(ErrorType.FATAL, message);
                throw new TickRefreshMismatch(message);
            }

        } else if (actualLoopConfig.getLogicRate().getTickRate() > actualLoopConfig.getRefreshSpeed().getHertz()) { // logicRate > refreshSpeed

            CentralLogger.getInstance().logMessage(ErrorType.WARN, "The logicRate is greater than the refreshSpeed! Lag is more likely to occur!");

            // Verify multiplicity
            if (actualLoopConfig.getLogicRate().getTickRate() % actualLoopConfig.getRefreshSpeed().getHertz() == 0) {

                // Reset data
                tickRateMultiples.setLogicMultiple(0);
                tickRateMultiples.setRefreshMultiple(0);
                VelocityScaler.setVelocityScaler(1);

                int scaler = actualLoopConfig.getLogicRate().getTickRate() / actualLoopConfig.getRefreshSpeed().getHertz();
                scaler = (int) (scaler * userLoopConfig.getLogicScaleMultiple());

                if (userLoopConfig.isAccurateTickRate()) {

                    // Number of times the logic should run in one frame
                    tickRateMultiples.setLogicMultiple(scaler);
                } else {
                    // Logic runs 1 to 1 with refresh rate

                    // This is LESS computationally expensive, but may cause collision clipping / other accuracy issues!
                    VelocityScaler.setVelocityScaler(scaler);
                }

            } else {

                String message = "The given logic rate (" + actualLoopConfig.getLogicRate().getTickRate() + ") is not a multiple of the refresh rate (" + actualLoopConfig.getRefreshSpeed().getHertz() + ") !";

                CentralLogger.getInstance().logMessage(ErrorType.FATAL, message);
                throw new TickRefreshMismatch(message);
            }
        } // logicRate == refreshSpeed -> do nothing


        // Update the loop runner values
        loopRunner.setLoopRateMultiples(tickRateMultiples);

        // TODO: update the animations to scale with tick rates!
        //ImageCollectionAnimator.setTargetRefreshRate(targetFps);
        //Effects.setTargetFps(targetFps);


        //CentralLogger.getInstance().logMessage(ErrorType.INFO, "multiples:    Logic: " + tickRateMultiples.getLogicMultiple() + "    Refresh: " + tickRateMultiples.getRefreshMultiple());
        //CentralLogger.getInstance().logMessage(ErrorType.INFO, "The logic rate has been set. LogicRate: " + actualLoopConfig.getLogicRate());
    }
}
