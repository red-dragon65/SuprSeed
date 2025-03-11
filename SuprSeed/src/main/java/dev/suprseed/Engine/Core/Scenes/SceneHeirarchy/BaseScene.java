package dev.suprseed.Engine.Core.Scenes.SceneHeirarchy;

import dev.suprseed.Engine.Core.MainView.GameProcessor.Render.Graphics.RenderHandler;
import dev.suprseed.Engine.Core.SpriteObjects.DefaultComponents.Resetable;
import dev.suprseed.Engine.Core.SpriteObjects.SpriteBase.Animator;
import dev.suprseed.Engine.Core.SpriteObjects.SpriteBase.Sprite;
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

public abstract class BaseScene implements LogicRunnable, RenderableAndLayerable, Animator, Resetable, Threadable {

    protected final String sceneId;
    public ImageRegister imageRegister;
    public AnimationRegister animationRegister;
    public LogicRegister logicRegister;
    protected boolean isActive = true;
    protected boolean isDrawable = true;
    // Dependency injection
    private LayerHandler layerInfo;

    // Constructor
    public BaseScene(String sceneId, int layerDepth) {

        this.sceneId = sceneId;
        this.layerInfo = new LayerData(layerDepth);
        init();
    }

    // Constructor
    public BaseScene(String sceneId) {

        this.sceneId = sceneId;
        this.layerInfo = new LayerData();
        init();
    }

    // Constructor initializer
    private void init() {

        imageRegister = new ImageRegistry(new LayerableQueueComparator());
        animationRegister = new AnimationRegistry();
        logicRegister = new LogicRegistry();
    }

    public void registerSprite(Sprite s) {
        imageRegister.registerObject(s);
        logicRegister.registerObject(s);
    }


    public String getId() {
        return sceneId;
    }

    @Override
    public void resetState() {

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

    @Override
    public void onPost() {
        // Run code once it joins back with main thread
    }

    @Override
    public void onDestroy() {
        // Run code before this scenes gets destroyed
    }
}
