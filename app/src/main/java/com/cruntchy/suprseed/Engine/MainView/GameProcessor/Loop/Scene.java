package com.cruntchy.suprseed.Engine.MainView.GameProcessor.Loop;

import com.cruntchy.suprseed.Engine.SpriteObjects.SpriteExtensions.Logic;
import com.cruntchy.suprseed.Engine.SpriteObjects.System.SpriteSystem;

public abstract class Scene implements Logic {

    private String id;
    private boolean hidden;
    private boolean active;
    protected static SceneController sceneManager;


    public Scene(SceneController sceneManager, String id){

        this.id = id;
        Scene.sceneManager = sceneManager;

        hidden = true;
        active = false;

        // Register logic
        SpriteSystem.getInstance().registerLogicSprite(this);
    }


    public String getId(){
        return id;
    }

    public boolean isHidden(){
        return isHidden();
    }
    public boolean isActive(){
        return isActive();
    }

    public void setHidden(boolean hidden){
        this.hidden = hidden;
    }

    public void setActive(boolean active){
        this.active = active;
    }

}
