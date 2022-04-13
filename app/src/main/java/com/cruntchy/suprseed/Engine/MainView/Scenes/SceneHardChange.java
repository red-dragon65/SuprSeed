package com.cruntchy.suprseed.Engine.MainView.Scenes;

import com.cruntchy.suprseed.Client.Z_ClientTest_Active.GameCode.Scenes.GameScene.MainScene;
import com.cruntchy.suprseed.Client.Z_ClientTest_Active.GameCode.Scenes.HomeScreen.LandingScene;
import com.cruntchy.suprseed.Engine.SpriteObjects.System.LogicSystem;

public class SceneHardChange implements SceneChangeStrategy {

    @Override
    public void changeScene(SceneController controller, Scene oldScene, String... sceneId) {

        for (Scene scene : controller.getScenes()) {

            for (String s : sceneId) {

                if (scene.getId().equals(s)) {

                    // Remove the old scene
                    controller.getScenes().remove(scene);

                    // Clear the system register of any scene or sprites
                    LogicSystem.getInstance().removeAllObjects();


                    // Hold the new scene
                    Scene someScene = null;

                    // Create the new scene
                    switch (s) {
                        case "MainScene":
                            someScene = new MainScene(controller, "MainScene", controller.getContext());
                            break;
                        case "LandingScene":
                            someScene = new LandingScene(controller, "LandingScene");
                            break;
                    }

                    // Enable the new scene
                    if (someScene != null) {

                        someScene.setActive(true);
                        someScene.setHidden(false);

                        controller.getScenes().add(someScene);
                    }

                    break;
                }
            }
        }
    }
}
