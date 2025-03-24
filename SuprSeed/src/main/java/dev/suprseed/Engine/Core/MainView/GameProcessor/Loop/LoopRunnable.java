package dev.suprseed.Engine.Core.MainView.GameProcessor.Loop;


import dev.suprseed.Engine.Core.MainView.GameProcessor.Render.Graphics.RenderHandler;

public interface LoopRunnable {

    void run(RenderHandler renderer);

    void setLoopRateMultiples(LoopTickRateMultiples loopRateMultiples);
}
