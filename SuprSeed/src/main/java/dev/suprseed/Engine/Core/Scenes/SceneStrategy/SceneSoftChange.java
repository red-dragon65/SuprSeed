package dev.suprseed.Engine.Core.Scenes.SceneStrategy;

import dev.suprseed.Engine.Core.Scenes.SceneHeirarchy.BaseScene;
import dev.suprseed.Engine.Core.Scenes.SceneHeirarchy.SceneManager;
import dev.suprseed.Engine.Core.System.LogicSystem;

public class SceneSoftChange implements SceneChangeStrategy<BaseScene> {

    @Override
    public void changeScene(SceneManager parentScene, BaseScene oldScene, String... sceneId) {

        for (BaseScene scene : parentScene.getRegister().getRegisterList()) {

            for (String s : sceneId) {

                if (scene.getId().equals(s)) {

                    // Disable the old scene
                    oldScene.setActive(false);
                    oldScene.setDrawable(false);

                    // Clear the system register
                    LogicSystem.getInstance().getLogicRegister().removeAllObjects();

                    // Enable the new scene
                    scene.setActive(true);
                    scene.setDrawable(true);

                    break;
                }
            }
        }
    }
}
