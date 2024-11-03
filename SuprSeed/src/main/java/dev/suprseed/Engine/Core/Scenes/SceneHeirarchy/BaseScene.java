package dev.suprseed.Engine.Core.Scenes.SceneHeirarchy;

import android.content.Context;

import dev.suprseed.Engine.Core.MainView.GameProcessor.Render.Graphics.RenderHandler;
import dev.suprseed.Engine.Core.SpriteObjects.DefaultComponents.Resetable;
import dev.suprseed.Engine.Core.System.LayerData;
import dev.suprseed.Engine.Core.System.LayerHandler;
import dev.suprseed.Engine.Core.System.LayerableQueueComparator;
import dev.suprseed.Engine.Core.System.RegisterTypes.AnimationRegister;
import dev.suprseed.Engine.Core.System.RegisterTypes.ImageRegister;
import dev.suprseed.Engine.Core.System.RegisterTypes.LogicRegister;
import dev.suprseed.Engine.Core.System.Registerables.LogicRunnable;
import dev.suprseed.Engine.Core.System.Registerables.RenderableAndLayerable;
import dev.suprseed.Engine.Core.System.Registers.AnimationRegistry;
import dev.suprseed.Engine.Core.System.Registers.ImageRegistry;
import dev.suprseed.Engine.Core.System.Registers.LogicRegistry;
import dev.suprseed.Engine.Lib.Images.Animator;

public abstract class BaseScene implements LogicRunnable, RenderableAndLayerable, Animator, Resetable {

    public ImageRegister imageRegister;
    public AnimationRegister animationRegister;
    public LogicRegister logicRegister;
    protected String sceneId;
    protected boolean isActive = true;
    protected boolean isDrawable = true;
    protected Context context;
    // Dependency injection
    protected SceneManager parentScene;
    private LayerHandler layerInfo;

    // Constructor
    public BaseScene(SceneManager parentScene, String sceneId, int layerDepth) {

        this.layerInfo = new LayerData(layerDepth);
        init(parentScene, sceneId);
    }

    // Constructor
    public BaseScene(SceneManager parentScene, String sceneId) {

        this.layerInfo = new LayerData();
        init(parentScene, sceneId);
    }

    // Constructor initializer
    private void init(SceneManager parentScene, String sceneId) {

        this.parentScene = parentScene;
        this.sceneId = sceneId;

        if (parentScene != null) {
            context = parentScene.getContext();
        }

        imageRegister = new ImageRegistry(new LayerableQueueComparator());
        animationRegister = new AnimationRegistry();
        logicRegister = new LogicRegistry();

        // Register the base scene to it's parent
        if (parentScene != null) {
            this.parentScene.sceneRegister.registerObject(this);
        }
    }


    public String getId() {
        return sceneId;
    }

    public Context getContext() {
        return context;
    }

    @Override
    public void resetState() {

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
    public LayerHandler getLayerInfo() {
        return layerInfo;
    }

    @Override
    public void runLogic() {

        logicRegister.update();
    }

    @Override
    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    @Override
    public void draw(RenderHandler renderer) {

        imageRegister.update(renderer);
    }

    @Override
    public boolean isDrawable() {
        return isDrawable;
    }

    public void setDrawable(boolean drawable) {
        isDrawable = drawable;
    }
}
