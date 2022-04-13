package com.cruntchy.suprseed.Engine.MainView.GameProcessor.BetterScene;

import com.cruntchy.suprseed.Engine.Images.Animator;
import com.cruntchy.suprseed.Engine.MainView.GameProcessor.Render.Graphics.RenderHandler;
import com.cruntchy.suprseed.Engine.SpriteObjects.Register.ObjectRegister;
import com.cruntchy.suprseed.Engine.SpriteObjects.Register.RenderRegister;
import com.cruntchy.suprseed.Engine.SpriteObjects.Register.UpdatableRegister;
import com.cruntchy.suprseed.Engine.SpriteObjects.SpriteBase.Sprite;
import com.cruntchy.suprseed.Engine.SpriteObjects.System.AnimationRenderer;
import com.cruntchy.suprseed.Engine.SpriteObjects.System.ImageRenderer;
import com.cruntchy.suprseed.Engine.SpriteObjects.System.LayerableQueueComparator;
import com.cruntchy.suprseed.Engine.SpriteObjects.System.Logic;
import com.cruntchy.suprseed.Engine.SpriteObjects.System.ObjectLogicizer;
import com.cruntchy.suprseed.Engine.SpriteObjects.System.RenderableAndLayerable;

import java.util.ArrayList;

public abstract class BaseScene implements Logic, RenderableAndLayerable, Animator {

    protected String sceneId;
    protected boolean isActive = true;
    protected boolean isDrawable = true;

    public RenderRegister<RenderableAndLayerable> imageRegister;
    public UpdatableRegister<Animator> animationRegister;
    public UpdatableRegister<Logic> logicRegister;

    // Dependency injection
    protected SceneManager parentScene;


    // Constructor
    public BaseScene(SceneManager parentScene, String sceneId){

        this.parentScene = parentScene;
        this.sceneId = sceneId;

        imageRegister = new ImageRenderer(new LayerableQueueComparator());
        animationRegister = new AnimationRenderer();
        logicRegister = new ObjectLogicizer();
    }


    public String getId(){
        return sceneId;
    }

    public void setActive(boolean active){
        isActive = active;
    }

    public void setDrawable(boolean drawable){
        isDrawable = drawable;
    }


    @Override
    public void resetLoop() {

        // TODO: REMOVE THIS! It is not needed here... refactor 'Animator' interface
    }

    @Override
    public void generateNextFrame() {
        animationRegister.update();
    }

    @Override
    public int getLayerDepth() {
        return 0;
    }

    @Override
    public void runLogic() {

        logicRegister.update();
    }

    @Override
    public boolean isActive() {
        return isActive;
    }

    @Override
    public void draw(RenderHandler renderer) {

        imageRegister.update(renderer);
    }

    @Override
    public boolean isDrawable() {
        return isDrawable;
    }
}
