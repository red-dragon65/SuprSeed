package dev.suprseed.Engine.Core.Scenes.SceneStrategy;

import dev.suprseed.Engine.Core.Scenes.SceneHeirarchy.BaseScene;
import dev.suprseed.Engine.Core.Scenes.SceneHeirarchy.SceneManager;

public class SceneSoftChange {

    public void changeActiveScene(SceneManager parentScene, BaseScene oldScene, String... sceneId) {

        for (BaseScene scene : parentScene.getRegister().getRegisterList()) {

            for (String s : sceneId) {

                if (scene.getId().equals(s)) {

                    // Disable the old scene
                    oldScene.setActive(false);
                    oldScene.setDrawable(false);

                    // Enable the new scene
                    scene.setActive(true);
                    scene.setDrawable(true);

                    break;
                }
            }
        }
    }
}
