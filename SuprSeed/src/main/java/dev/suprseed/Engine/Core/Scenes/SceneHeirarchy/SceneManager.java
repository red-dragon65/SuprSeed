package dev.suprseed.Engine.Core.Scenes.SceneHeirarchy;

import dev.suprseed.Engine.Core.MainView.GameProcessor.Render.Graphics.RenderHandler;
import dev.suprseed.Engine.Core.Scenes.SceneStrategy.SceneChangeStrategy;
import dev.suprseed.Engine.Core.System.LayerableQueueComparator;
import dev.suprseed.Engine.Core.System.RegisterTypes.SceneRegister;

public abstract class SceneManager extends BaseScene implements SceneController<BaseScene> {

    protected SceneRegister<BaseScene> sceneRegister;

    // Constructor
    public SceneManager(SceneManager parentScene, String sceneId, int layerDepth) {
        super(parentScene, sceneId, layerDepth);
        init();
    }

    // Constructor
    public SceneManager(SceneManager parentScene, String sceneId) {
        super(parentScene, sceneId);
        init();
    }

    // Constructor initializer
    private void init() {
        sceneRegister = new SubSceneRegistry(new LayerableQueueComparator());
    }


    @Override
    public void changeScene(SceneChangeStrategy<BaseScene> strategy, BaseScene oldScene, String... sceneId) {

        strategy.changeScene(this, oldScene, sceneId);
    }

    @Override
    public SceneRegister<BaseScene> getRegister() {
        return sceneRegister;
    }


    /*
    This calls the sub scenes / sceneManagers code
     */
    @Override
    public void generateNextFrame() {
        super.generateNextFrame();

        for (BaseScene scene : sceneRegister.getRegisterList()) {
            scene.generateNextFrame();
        }
    }


    @Override
    public void runLogic() {
        super.runLogic();

        for (BaseScene scene : sceneRegister.getRegisterList()) {
            scene.runLogic();
        }
    }


    @Override
    public void draw(RenderHandler renderer) {
        super.draw(renderer);

        // Re-sort the layers before drawing them
        sceneRegister.syncLayers();

        for (BaseScene scene : sceneRegister.getRegisterList()) {
            scene.draw(renderer);
        }
    }
}
