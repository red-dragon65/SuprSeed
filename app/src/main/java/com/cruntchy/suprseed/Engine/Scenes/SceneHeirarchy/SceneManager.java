package com.cruntchy.suprseed.Engine.Scenes.SceneHeirarchy;

import com.cruntchy.suprseed.Engine.MainView.GameProcessor.Render.Graphics.RenderHandler;
import com.cruntchy.suprseed.Engine.Scenes.AppInfo;
import com.cruntchy.suprseed.Engine.Scenes.SceneStrategy.SceneChangeStrategy;
import com.cruntchy.suprseed.Engine.SpriteObjects.Register.ListRegister;

public abstract class SceneManager extends BaseScene implements SceneController<BaseScene> {

    protected ListRegister<BaseScene> sceneRegister;
    protected static AppInfo gameInfo;


    // Constructor
    public SceneManager(SceneManager parentScene, String sceneId){
        super(parentScene, sceneId);

        sceneRegister = new SceneRegister();
    }


    @Override
    public void changeScene(SceneChangeStrategy<BaseScene> strategy, BaseScene oldScene, String... sceneId) {

        strategy.changeScene(this, oldScene, sceneId);
    }

    @Override
    public ListRegister<BaseScene> getRegister() {
        return sceneRegister;
    }

    @Override
    public AppInfo getGameInfo() {
        return gameInfo;
    }







    /*
    This calls the sub scenes / sceneManagers code
     */
    @Override
    public void generateNextFrame() {

        for(BaseScene scene : sceneRegister.getRegisterList()){
            scene.generateNextFrame();
        }
    }


    @Override
    public void runLogic() {

        for(BaseScene scene : sceneRegister.getRegisterList()){
            scene.runLogic();
        }
    }


    @Override
    public void draw(RenderHandler renderer) {

        imageRegister.update(renderer);

        for(BaseScene scene : sceneRegister.getRegisterList()){
            scene.draw(renderer);
        }
    }
}
