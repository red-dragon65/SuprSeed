package dev.suprseed.demo.Engine.Core.MainView.GameProcessor.Loop;


import android.view.SurfaceView;

public interface RunnableConfig<T extends SurfaceView> {

    // TODO: Verify this covers the correct responsibilities

    void initLoop(T gameView);

    void run(T gameView);

    boolean isHardPause();

    void setHardPause(boolean pause);

    boolean isSoftPause();

    void setSoftPause(boolean pause);

    void toggleSoftPause();

    void setRefreshSpeed(RefreshTypes refreshSpeed);

    void setLogicRate(LogicRates logicRate);

}
