package dev.suprseed.Engine.Core.Scenes.SceneHeirarchy;

import android.content.Context;

import dev.suprseed.Engine.Core.MainView.GameProcessor.Render.Graphics.RenderHandler;
import dev.suprseed.Engine.Core.SpriteObjects.DefaultComponents.Resetable;
import dev.suprseed.Engine.Core.System.AnimationRenderer;
import dev.suprseed.Engine.Core.System.ImageRenderer;
import dev.suprseed.Engine.Core.System.LayerableQueueComparator;
import dev.suprseed.Engine.Core.System.Logic;
import dev.suprseed.Engine.Core.System.ObjectLogicizer;
import dev.suprseed.Engine.Core.System.Register.RenderRegister;
import dev.suprseed.Engine.Core.System.Register.UpdatableRegister;
import dev.suprseed.Engine.Core.System.RenderableAndLayerable;
import dev.suprseed.Engine.Lib.Images.Animator;

public abstract class BaseScene implements Logic, RenderableAndLayerable, Animator, Resetable {

    protected String sceneId;
    protected boolean isActive = true;
    protected boolean isDrawable = true;
    protected static Context context;

    public RenderRegister<RenderableAndLayerable> imageRegister;
    public UpdatableRegister<Animator> animationRegister;
    public UpdatableRegister<Logic> logicRegister;

    // Dependency injection
    protected SceneManager parentScene;


    // Constructor
    public BaseScene(SceneManager parentScene, String sceneId, Context appContext) {

        init(parentScene, sceneId, appContext);
    }

    // Constructor
    public BaseScene(SceneManager parentScene, String sceneId) {

        init(parentScene, sceneId, null);
    }

    // Constructor initializer
    private void init(SceneManager parentScene, String sceneId, Context appContext) {

        this.parentScene = parentScene;
        this.sceneId = sceneId;

        if (appContext != null) {
            context = appContext;
        }

        imageRegister = new ImageRenderer(new LayerableQueueComparator());
        animationRegister = new AnimationRenderer();
        logicRegister = new ObjectLogicizer();

        // Register the base scene to it's parent
        if (parentScene != null) {
            this.parentScene.sceneRegister.registerObject(this);
        }
    }


    public String getId() {
        return sceneId;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public void setDrawable(boolean drawable) {
        isDrawable = drawable;
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
