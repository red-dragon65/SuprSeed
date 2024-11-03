package dev.suprseed.Engine.Core.System;

import dev.suprseed.Engine.Core.System.Registerables.Layerable;
import kotlin.NotImplementedError;

public class LayerData implements LayerHandler {

    private int layerDepth;
    private boolean isChanged = true;


    public LayerData() {
        layerDepth = 0;
    }

    public LayerData(int layerDepth) {

        this.layerDepth = layerDepth;
    }

    @Override
    public int getLayerDepth() {
        return layerDepth;
    }

    @Override
    public void setLayerDepth(int layerDepth) {
        this.layerDepth = layerDepth;
        isChanged = true;
    }

    @Override
    public boolean isChanged() {
        return isChanged;
    }

    @Override
    public void notifySynced() {
        isChanged = false;
    }

    @Override
    public void inFrontOf(Layerable layerable) {
        throw new NotImplementedError();
    }

    @Override
    public void behind(Layerable layerable) {
        throw new NotImplementedError();
    }
}
