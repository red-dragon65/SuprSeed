package dev.suprseed.Engine.Core.MainView.GameProcessor.Loop;


import android.view.SurfaceView;

public interface LoopRunnable<T extends SurfaceView> {

    void run(T gameView);

    boolean isHardPause();

    void setHardPause(boolean pause);

    boolean isSoftPause();

    void setSoftPause(boolean pause);

    void toggleSoftPause();

    void setLoopRateMultiples(LoopTickRateMultiples loopRateMultiples);
}
