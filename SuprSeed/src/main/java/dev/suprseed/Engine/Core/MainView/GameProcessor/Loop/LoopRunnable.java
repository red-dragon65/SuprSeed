package dev.suprseed.Engine.Core.MainView.GameProcessor.Loop;


import dev.suprseed.Engine.Core.MainView.GameProcessor.Render.Graphics.RenderHandler;

public interface LoopRunnable {

    void run(RenderHandler renderer);

    boolean isHardPause();

    void setHardPause(boolean pause);

    boolean isSoftPause();

    void setSoftPause(boolean pause);

    void toggleSoftPause();

    void setLoopRateMultiples(LoopTickRateMultiples loopRateMultiples);
}
