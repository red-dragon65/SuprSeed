package dev.suprseed.Engine.Core.System.Registers;

import java.util.Comparator;
import java.util.List;

import dev.suprseed.Engine.Core.System.ListSyncronizer;
import dev.suprseed.Engine.Core.System.Registerables.Layerable;

public class LayerSyncer<T extends Layerable> implements ListSyncronizer<T> {

    private final Comparator<Layerable> layerableComparator;

    public LayerSyncer(Comparator<Layerable> layerableComparator) {
        this.layerableComparator = layerableComparator;
    }

    @Override
    public void syncLayers(List<T> layerableQueue) {

        boolean isLayerSynced = true;

        // See if any layer changes occurred
        for (T item : layerableQueue) {
            if (item.getLayerInfo().isChanged()) {
                isLayerSynced = false;
                break;
            }
        }

        // Resort the queue
        if (!isLayerSynced) {

            layerableQueue.sort((Comparator<Layerable>) (t, t1) -> {

                // Update the synced status of the items
                t1.getLayerInfo().notifySynced();
                t.getLayerInfo().notifySynced();

                // Calculate the new order
                return layerableComparator.compare(t, t1);
            });
        }

    }
}
