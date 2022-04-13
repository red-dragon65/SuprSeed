package com.cruntchy.suprseed.Engine.MainView.Scenes;

import com.cruntchy.suprseed.Engine.SpriteObjects.System.LogicSystem;

public class SceneSoftChange implements SceneChangeStrategy {

    @Override
    public void changeScene(SceneController controller, Scene oldScene, String... sceneId) {

        for(Scene scene : controller.getScenes()){

            for(String s : sceneId) {

                if (scene.getId().equals(s)) {

                    // Disable the old scene
                    oldScene.setActive(false);
                    oldScene.setHidden(true);

                    // Clear the system register
                    LogicSystem.getInstance().removeAllObjects();

                    // Enable the new scene
                    scene.setActive(true);
                    scene.setHidden(false);

                    break;
                }
            }
        }
    }
}
