package dev.suprseed.Engine.Core.Scenes.SceneHeirarchy;

import android.content.Context;

import dev.suprseed.Engine.Core.MainView.GameProcessor.Render.Graphics.RenderHandler;
import dev.suprseed.Engine.Core.Scenes.SceneStrategy.SceneChangeStrategy;
import dev.suprseed.Engine.Core.System.RegisterTypes.ISceneRegister;

public abstract class SceneManager extends BaseScene implements SceneController<BaseScene> {

    protected ISceneRegister<BaseScene> sceneRegister;


    // Constructor
    public SceneManager(SceneManager parentScene, String sceneId, Context context) {
        super(parentScene, sceneId, context);

        init();
    }

    // Constructor
    public SceneManager(SceneManager parentScene, String sceneId) {
        super(parentScene, sceneId, parentScene.getContext());

        init();
    }

    // Constructor initializer
    private void init() {
        sceneRegister = new SubSceneRegister();
    }


    @Override
    public void changeScene(SceneChangeStrategy<BaseScene> strategy, BaseScene oldScene, String... sceneId) {

        strategy.changeScene(this, oldScene, sceneId);
    }

    @Override
    public ISceneRegister<BaseScene> getRegister() {
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

        for (BaseScene scene : sceneRegister.getRegisterList()) {
            scene.draw(renderer);
        }
    }
}
