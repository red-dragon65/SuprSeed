package com.cruntchy.suprseed.Engine.Core.Scenes.SceneHeirarchy;

import com.cruntchy.suprseed.Engine.Core.System.Register.ListRegister;

import java.util.ArrayList;
import java.util.List;

public class SceneRegister implements ListRegister<BaseScene> {

    private final List<BaseScene> myScenes;


    // Constructor
    public SceneRegister(){

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
