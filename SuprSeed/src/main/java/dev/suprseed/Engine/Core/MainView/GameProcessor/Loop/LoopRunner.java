package dev.suprseed.Engine.Core.MainView.GameProcessor.Loop;

import dev.suprseed.Engine.Core.System.LogicSystem;
import dev.suprseed.Engine.Core.System.RenderSystem;

public class LoopRunner implements LoopRunnable<GameView> {

    // TODO: This is temporary. Figure out a better way for client to get loop manager
    //  to handle pausing behavior
    public static LoopRunner loopy = null;
    // Dependencies
    private int frameCounter = 0;
    private boolean softPause = false;
    private boolean hardPause = false;

    private LoopTickRateMultiples loopRateMultiples;

    // Constructor
    public LoopRunner() {

        loopy = this;

        loopRateMultiples = new LoopTickRateMultiples();
    }

    public void setLoopRateMultiples(LoopTickRateMultiples loopRateMultiples) {
        this.loopRateMultiples = loopRateMultiples;
    }


    @Override
    public void run(GameView gameView) {

        if (!hardPause) { // Stops logic and drawing

            if (!softPause) { // Stops logic, but allows drawing

                // Run the game logic
                runLogic();
            }

            // Run clients drawing code
            RenderSystem.getInstance().draw(gameView.renderer);

            // Clear out old contents of screen
            gameView.invalidate();
        }
    }


    // Handle logic run rate (scaled relative to refresh rate)
    private void runLogic() {

        // Number of times to run logic based on logic tick rate
        // (logic rate is greater than refresh rate)
        if (loopRateMultiples.getLogicMultiple() > 0) {

            for (int i = 0; i < loopRateMultiples.getLogicMultiple(); i++) {

                // Run client logic code
                //gameView.logicLoop();
                LogicSystem.getInstance().runLogic();
            }
        } else

            // Number of frames to skip
            // (logic rate is less than refresh rate)
            if (loopRateMultiples.getRefreshMultiple() > 0) {

                // Track which frame the logic should run
                if (frameCounter == loopRateMultiples.getRefreshMultiple()) {

                    // Run client logic code
                    //gameView.logicLoop();
                    LogicSystem.getInstance().runLogic();

                    frameCounter = 0;
                }

                frameCounter++;
            }


            // Ignore frame pacing
            else {

                // Run client logic code
                LogicSystem.getInstance().runLogic();
            }
    }

    @Override
    public boolean isHardPause() {
        return hardPause;
    }

    @Override
    public void setHardPause(boolean pause) {

        hardPause = pause;
    }

    @Override
    public boolean isSoftPause() {
        return softPause;
    }

    @Override
    public void setSoftPause(boolean pause) {
        softPause = pause;
    }

    @Override
    public void toggleSoftPause() {
        softPause = !softPause;
    }

}
