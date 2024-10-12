package dev.suprseed.Engine.Core.Scenes.SceneHeirarchy;

import java.util.ArrayList;
import java.util.List;

import dev.suprseed.Engine.Core.System.Register.ListRegister;

public class SceneRegister implements ListRegister<BaseScene> {

    private final List<BaseScene> myScenes;


    // Constructor
    public SceneRegister() {

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
