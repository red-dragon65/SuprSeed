package dev.suprseed.Engine.Core.MainView.GameProcessor.Loop;

import dev.suprseed.Engine.Core.MainView.GameProcessor.Render.Graphics.RenderHandler;
import dev.suprseed.Engine.EngineContext;
import dev.suprseed.Engine.EngineTools;

public class LoopRunner implements LoopRunnable {

    private LoopTickRateMultiples loopRateMultiples;

    // Constructor
    public LoopRunner() {

        loopRateMultiples = new LoopTickRateMultiples();
    }

    @Override
    public void setLoopRateMultiples(LoopTickRateMultiples loopRateMultiples) {
        this.loopRateMultiples = loopRateMultiples;
    }


    @Override
    public void run(RenderHandler renderer) {

        if (!EngineTools.getLoopController().isHardPause()) { // Stops logic and drawing

            if (!EngineTools.getLoopController().isSoftPause()) { // Stops logic, but allows drawing

                // Run the game logic
                runLogic();
            }

            // Run clients drawing code
            EngineContext.getRenderSystem().draw(renderer);
        }
    }


    // Handle logic run rate (scaled relative to refresh rate)
    private void runLogic() {

        // Run the logic 1 or more times
        for (int i = 0; i < loopRateMultiples.getLogicMultiple(); i++) {

            // Run the clients logic code
            EngineContext.getLogicSystem().runLogic();
        }
    }
}
