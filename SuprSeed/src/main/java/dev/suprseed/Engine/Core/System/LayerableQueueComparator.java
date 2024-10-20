package dev.suprseed.Engine.Core.System;

import java.util.Comparator;

import dev.suprseed.Engine.Core.System.Registerables.ILayerable;

public class LayerableQueueComparator implements Comparator<ILayerable> {

    @Override
    public int compare(ILayerable o1, ILayerable o2) {
        return o1.getLayerDepth() - o2.getLayerDepth();
    }
}
