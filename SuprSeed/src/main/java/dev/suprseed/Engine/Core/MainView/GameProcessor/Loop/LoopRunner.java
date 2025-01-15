package dev.suprseed.Engine.Core.MainView.GameProcessor.Loop;

import dev.suprseed.Engine.Core.System.LogicSystem;
import dev.suprseed.Engine.Core.System.RenderSystem;

public class LoopRunner implements LoopRunnable<GameView> {

    // TODO: This is temporary. Figure out a better way for client to get loop manager
    //  to handle pausing behavior
    public static LoopRunner loopy = null;
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

        // Run the logic 1 or more times
        for(int i = 0; i < loopRateMultiples.getLogicMultiple(); i++){

            // Run the clients logic code
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
