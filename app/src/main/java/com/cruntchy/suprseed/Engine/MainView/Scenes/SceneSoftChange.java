package com.cruntchy.suprseed.Engine.MainView.Scenes;

import com.cruntchy.suprseed.Engine.MainView.GameProcessor.BetterScene.BaseScene;
import com.cruntchy.suprseed.Engine.MainView.GameProcessor.BetterScene.SceneManager;
import com.cruntchy.suprseed.Engine.SpriteObjects.Register.ListRegister;
import com.cruntchy.suprseed.Engine.SpriteObjects.System.LogicSystem;

public class SceneSoftChange implements SceneChangeStrategy<BaseScene> {

    @Override
    public void changeScene(SceneManager parentScene, BaseScene oldScene, String... sceneId) {

        for(BaseScene scene : parentScene.getRegister().getRegisterList()){

            for(String s : sceneId) {

                if (scene.getId().equals(s)) {

                    // Disable the old scene
                    oldScene.setActive(false);
                    oldScene.setDrawable(false);

                    // Clear the system register
                    LogicSystem.getInstance().removeAllObjects();

                    // Enable the new scene
                    scene.setActive(true);
                    scene.setDrawable(true);

                    break;
                }
            }
        }
    }
}
