package dev.suprseed.Engine.Core.Scenes.SceneHeirarchy;

import java.util.ArrayList;
import java.util.List;

import dev.suprseed.Engine.Core.System.RegisterTypes.ISceneRegister;

public class SubSceneRegister implements ISceneRegister<BaseScene> {

    private final List<BaseScene> myScenes;


    // Constructor
    public SubSceneRegister(){

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
    public List<BaseScene> getRegisterList(){

        return myScenes;
    }
}
