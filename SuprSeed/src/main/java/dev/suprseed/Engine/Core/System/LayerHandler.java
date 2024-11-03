package dev.suprseed.Engine.Core.System;

import dev.suprseed.Engine.Core.System.Registerables.Layerable;

public interface LayerHandler {

    int getLayerDepth();

    void setLayerDepth(int layerDepth);

    boolean isChanged();

    void notifySynced();

    void inFrontOf(Layerable layerable);

    void behind(Layerable layerable);
}
