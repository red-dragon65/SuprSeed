package dev.suprseed.Engine.Core.Scenes.SceneHeirarchy;

import dev.suprseed.Engine.Core.MainView.GameProcessor.Render.Graphics.RenderHandler;
import dev.suprseed.Engine.Core.System.LayerableQueueComparator;
import dev.suprseed.Engine.Core.System.RegisterTypes.SceneRegister;

public abstract class SceneManager extends BaseScene implements SceneController<BaseScene> {

    private SceneRegister<BaseScene> sceneRegister;

    // Constructor
    public SceneManager(String sceneId, int layerDepth) {
        super(sceneId, layerDepth);
        init();
    }

    // Constructor
    public SceneManager(String sceneId) {
        super(sceneId);
        init();
    }

    // Constructor initializer
    private void init() {
        sceneRegister = new SubSceneRegistry(new LayerableQueueComparator());
    }

    @Override
    public SceneRegister<BaseScene> getRegister() {
        return sceneRegister;
    }

    public void registerScene(BaseScene scene) {
        sceneRegister.registerObject(scene);
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

        // Run the logic for all sub scenes
        for (BaseScene scene : sceneRegister.getRegisterList()) {
            if (scene.isActive) {
                scene.runLogic();
            }
        }
    }


    @Override
    public void draw(RenderHandler renderer) {
        super.draw(renderer);

        // Re-sort the layers before drawing them
        sceneRegister.syncLayers();

        // Draw all sub scenes
        for (BaseScene scene : sceneRegister.getRegisterList()) {

            if (scene.isDrawable) {
                scene.draw(renderer);
            }
        }
    }

    @Override
    public void onDestroy() {

        for (BaseScene s : sceneRegister.getRegisterList()) {
            s.onDestroy();
        }

        sceneRegister.removeAllObjects();
    }
}
