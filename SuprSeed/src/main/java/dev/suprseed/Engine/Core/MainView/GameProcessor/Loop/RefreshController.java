package dev.suprseed.Engine.Core.MainView.GameProcessor.Loop;

import android.view.Display;
import android.view.SurfaceView;

import dev.suprseed.Engine.Core.ErrorLogger.ErrorType;
import dev.suprseed.Engine.Core.MainView.EngineSettings.LoopConfig;
import dev.suprseed.Engine.EngineContext;
import dev.suprseed.Engine.EngineTools;

/*
Scales logic tick rate across different device refresh rates.

Note: this method uses a combination of:
- scaling the dt (velocity scaler) to "upgrade" tick rates to the nearest refresh rate multiple
- running the logic code multiple times in one frame

Pros:
- easy implementation
- better visual accuracy across refresh rates
- lower input latency
- smooth frame rate at all refresh rates

Cons:
- not the most computationally efficient method
- makes the game run in a non-deterministic way
- lag will occur if rendering takes too long
- clipping can occur at lower tick rates, but this is negated by preferring lag to occur instead
    by upgrading the tick rate to the next refresh rate multiple


TODO: provide an option to have the logic de-coupled from the rendering.
    - use interpolation techniques to sync the logic with the renderer

Interpolation

Pros:
- physics stays accurate, even if rendering skips frames
    - aka, rendering based lag can occur without causing physics to lag
- smooth frame rate at all refresh rates
- deterministic physics simulations

Cons:
- more cpu usage: in between frames have to calculate physics in betweens
- more memory usage: multiple physics frames have to be kept in memory
- latency: physics frames are not immediately displayed to the user
- more complicated to implement
    - multiple interpolation algorithms might have to be used in order to ensure smooth rendering
        output. Non-linear movement benefits from non-linear interpolation.
- the engine will have to be adjusted in order to keep track of previous physics steps (position,
    and velocity) in order to calculate the in between positions.
 */
public class RefreshController implements RefreshHandler {

    // The user specified refresh & tick rates (these are the target values)
    private final LoopConfig userLoopConfig;
    // The classes we need to notify of changes
    private final LoopRunnable loopRunner;
    // The engine specified surface view
    private SurfaceView gameView;
    // Hold the actual logic rates and refresh rates the engine running on an actual phone can use
    private LoopTickRateMultiples tickRateMultiples;

    private int deviceRefreshSpeed = 0;
    private int actualTargetTickRate = 0;


    public RefreshController(LoopConfig userLoopConfig, LoopRunnable loopRunner) {
        this.userLoopConfig = userLoopConfig;
        this.loopRunner = loopRunner;

        actualTargetTickRate = userLoopConfig.getMinLogicRate();
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
        if ((int) m.getRefreshRate() != deviceRefreshSpeed) {
            EngineContext.getLogger().logMessage(ErrorType.WARN, "The refresh rate changed! Updating loop tick variables!");
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

        // Get the current supported refresh rate of the phone

        // Get the current mode of the display
        Display.Mode m = gameView.getDisplay().getMode();

        deviceRefreshSpeed = (int) m.getRefreshRate();

        EngineContext.getRenderSystem().notifyRefreshRate(deviceRefreshSpeed);

        consolidateRefreshRate();
    }


    /*
    This method scales the dt (velocity scaler) in order to match the physics to the tick rate.
    The tick rate is tied to the refresh rate as a multiple.
     */
    private void consolidateRefreshRate() {

        EngineContext.getLogger().logMessage(ErrorType.INFO, "Attempting to set loop tick rate variables.");

        // Reset data
        tickRateMultiples.setLogicMultiple(1);
        EngineTools.getVelocityScaler().setVelocityScaler(1);


        int bufferZone = 3;

        // See if device refresh rate is "close enough" (ie 59.0000009hz vs 60hz)
        if (actualTargetTickRate > deviceRefreshSpeed - bufferZone
                && actualTargetTickRate < deviceRefreshSpeed + bufferZone) {

            // If tick rate == refresh rate

            float scaler = 1;
            scaler *= userLoopConfig.getLogicScaleMultiple();
            EngineTools.getVelocityScaler().setVelocityScaler(scaler);
            tickRateMultiples.setLogicMultiple(1);
            loopRunner.setLoopRateMultiples(tickRateMultiples);

            EngineContext.getLogger().logMessage(ErrorType.INFO, "The tick rate matches the refresh rate!");

            return;
        }


        // Calculate correct tick rate for given refresh rate
        if (actualTargetTickRate < deviceRefreshSpeed) {

            EngineContext.getLogger().logMessage(ErrorType.WARN, "The target tick rate is LESS than the devices refresh speed! Upgrading the tick rate to be a multiple of the device refresh rate!");

            EngineContext.getLogger().logMessage(ErrorType.WARN, "User min target tick rate: " + userLoopConfig.getMinLogicRate()
                    + "    Current target tick rate: " + actualTargetTickRate
                    + "    Device refresh speed: " + deviceRefreshSpeed);


            // Tick rate < refresh rate
            /*
            Tick rate gets upgraded to the refresh speed.
            The velocity must scale down to match motion to the new tick rate.
             */

            // Scale down the velocity

            float scaler = 1;

            scaler = scaler / ((float) deviceRefreshSpeed / actualTargetTickRate);
            scaler *= userLoopConfig.getLogicScaleMultiple();

            EngineTools.getVelocityScaler().setVelocityScaler(scaler);

            tickRateMultiples.setLogicMultiple(1);
            loopRunner.setLoopRateMultiples(tickRateMultiples);

            /*
            Example:

            60 tick < 90 refresh

            velocity = velocity / 90/60
             */

        } else {

            EngineContext.getLogger().logMessage(ErrorType.WARN, "The tick rate is GREATER than the device refresh rate! Upgrading the tick rate to be a multiple of the device refresh rate if necessary!");

            EngineContext.getLogger().logMessage(ErrorType.WARN, "User min target tick rate: " + userLoopConfig.getMinLogicRate()
                    + "    Current target tick rate: " + actualTargetTickRate
                    + "    Device refresh speed: " + deviceRefreshSpeed);

            // Tick rate > refresh rate
            /*
            The tick rate must be a multiple of the refresh rate.
            Logic runs twice per frame.
            The velocity must scale down to match the new tick rate.
            The new tick rate must be greater than the users minimum target tick rate.
             */

            // Scale down the velocity, double the tick/logic rate

            int newTickRate = deviceRefreshSpeed * 2;

            while (newTickRate < actualTargetTickRate) {

                newTickRate += deviceRefreshSpeed;
            }

            float scaler = 1;

            if (newTickRate == actualTargetTickRate) {
                EngineContext.getLogger().logMessage(ErrorType.WARN, "Valid! The tick rate (" + actualTargetTickRate + ") is already a multiple of the device refresh rate! (" + deviceRefreshSpeed + ")");
            } else {
                EngineContext.getLogger().logMessage(ErrorType.WARN, "Invalid! The tick rate (" + actualTargetTickRate + ") is not a multiple of the device refresh rate! (" + deviceRefreshSpeed + ")");
                EngineContext.getLogger().logMessage(ErrorType.WARN, "The upgraded tick rate is now: " + newTickRate);

                // Only scale if the new tick rate is not a multiple of the original target tick rate
                scaler = scaler / ((float) newTickRate / actualTargetTickRate);
            }

            scaler *= userLoopConfig.getLogicScaleMultiple();
            EngineTools.getVelocityScaler().setVelocityScaler(scaler);

            actualTargetTickRate = newTickRate;

            // Run logic multiple times per frame
            tickRateMultiples.setLogicMultiple(newTickRate / deviceRefreshSpeed);
            loopRunner.setLoopRateMultiples(tickRateMultiples);

            /*
            One possible worst case scenario:

            Target tick rate = 200
            Refresh rate = 90

            200 target tick > 90 refresh

            New tick = 90 * 2 = 180

            180 new tick > 90 refresh
            180 new tick < 200 target tick rate

            New tick = 180 + 90 = 270

            270 new tick > 90 refresh
            270 new tick > 200 target tick rate

             */
        }
    }
}
