package dev.suprseed.Engine.Core.Scenes.SceneHeirarchy;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import dev.suprseed.Engine.Core.System.ListSyncronizer;
import dev.suprseed.Engine.Core.System.RegisterTypes.SceneRegister;
import dev.suprseed.Engine.Core.System.Registerables.Layerable;
import dev.suprseed.Engine.Core.System.Registers.LayerSyncer;

public class SubSceneRegistry implements SceneRegister<BaseScene> {

    private final List<BaseScene> myScenes;
    private final ListSyncronizer<BaseScene> layerSyncer;


    // Constructor
    public SubSceneRegistry(Comparator<Layerable> layerableComparator) {

        myScenes = new ArrayList<>();
        layerSyncer = new LayerSyncer<>(layerableComparator);
    }


    @Override
    public void registerObject(BaseScene object) {

        myScenes.add(object);
    }

    @Override
    public void removeObject(BaseScene object) {

        myScenes.remove(object);
    }

    @Override
    public void removeAllObjects() {

        myScenes.clear();
    }

    @Override
    public List<BaseScene> getRegisterList() {

        return myScenes;
    }

    @Override
    public void syncLayers() {

        layerSyncer.syncLayers(myScenes);
    }
}
