package com.cruntchy.suprseed.MainView.GameProcessor.Loop;


import android.view.SurfaceView;

public interface RunnableConfig <T extends SurfaceView>{

    // TODO: Verify this covers the correct responsibilities

    public void initLoop(T gameView);

    public void run(T gameView);

    public boolean isHardPause();
    public boolean isSoftPause();
    public void setHardPause(boolean pause);
    public void setSoftPause(boolean pause);
    public void toggleSoftPause();

}
