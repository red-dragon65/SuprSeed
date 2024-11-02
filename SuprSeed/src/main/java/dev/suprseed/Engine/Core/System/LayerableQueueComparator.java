package dev.suprseed.Engine.Core.System;

import java.util.Comparator;

import dev.suprseed.Engine.Core.System.Registerables.Layerable;

public class LayerableQueueComparator implements Comparator<Layerable> {

    @Override
    public int compare(Layerable o1, Layerable o2) {
        return o1.getLayerDepth() - o2.getLayerDepth();
    }
}
