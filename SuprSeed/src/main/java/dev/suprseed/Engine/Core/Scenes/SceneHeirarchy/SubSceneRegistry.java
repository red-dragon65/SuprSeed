package dev.suprseed.Engine.Core.Scenes.SceneHeirarchy;

import java.util.ArrayList;
import java.util.List;

import dev.suprseed.Engine.Core.System.RegisterTypes.SceneRegister;

public class SubSceneRegistry implements SceneRegister<BaseScene> {

    private final List<BaseScene> myScenes;


    // Constructor
    public SubSceneRegistry() {

        myScenes = new ArrayList<>();
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
}
