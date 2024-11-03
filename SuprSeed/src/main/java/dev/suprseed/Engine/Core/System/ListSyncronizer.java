package dev.suprseed.Engine.Core.System;

import java.util.List;

public interface ListSyncronizer<T> {

    void syncLayers(List<T> queue);
}
