package dev.suprseed.Engine.Core.Scenes.SceneHeirarchy;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import dev.suprseed.Engine.Core.ErrorLogger.ErrorType;
import dev.suprseed.Engine.Core.System.ListSyncronizer;
import dev.suprseed.Engine.Core.System.RegisterTypes.SceneRegister;
import dev.suprseed.Engine.Core.System.Registerables.Layerable;
import dev.suprseed.Engine.Core.System.Registers.LayerSyncer;
import dev.suprseed.Engine.EngineContext;

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

        if (myScenes.stream().anyMatch(s -> s.getId().equals(object.getId()))) {

            EngineContext.getLogger().logMessage(ErrorType.ERROR, "A scene with id: " + object.getId() + "already exists! Not adding scene!");

            return;
        }

        myScenes.add(object);
    }

    @Override
    public void removeObject(BaseScene object) {

        if (myScenes.stream().noneMatch(s -> s.getId().equals(object.getId()))) {

            EngineContext.getLogger().logMessage(ErrorType.ERROR, "No scene with id: " + object.getId() + " was found! Could not remove the scene!");

            return;
        }

        myScenes.remove(object);
    }

    @Override
    public void removeObject(String sceneId) {

        if (myScenes.stream().noneMatch(s -> s.getId().equals(sceneId))) {

            EngineContext.getLogger().logMessage(ErrorType.ERROR, "No scene with id: " + sceneId + " was found! Could not remove the scene!");

            return;
        }

        myScenes.removeIf(s -> s.getId().equals(sceneId));
    }

    @Override
    public Optional<BaseScene> getScene(String sceneId) {

        if (myScenes.stream().noneMatch(s -> s.getId().equals(sceneId))) {

            EngineContext.getLogger().logMessage(ErrorType.ERROR, "No scene with id: " + sceneId + " was found! Could not retrieve the scene!");
            return Optional.empty();
        }

        return myScenes.stream().filter(s -> s.getId().equals(sceneId)).findFirst();
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
