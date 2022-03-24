package com.cruntchy.suprseed.Engine.SpriteObjects.System;

import java.util.Comparator;

public class RenderQueueComparator implements Comparator<Renderable> {

    @Override
    public int compare(Renderable o1, Renderable o2) {
        return o1.getLayerDepth() - o2.getLayerDepth();
    }
}
