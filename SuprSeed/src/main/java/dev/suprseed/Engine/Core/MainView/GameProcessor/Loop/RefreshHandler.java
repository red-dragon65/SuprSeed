package dev.suprseed.Engine.Core.MainView.GameProcessor.Loop;

import android.view.SurfaceView;

public interface RefreshHandler {

    void setGameView(SurfaceView gameView);

    void monitorRefreshRate();

    void updateRefreshRate();
}
