package com.cruntchy.suprseed.Engine.Core.Scenes.SceneStrategy;

import com.cruntchy.suprseed.Client.DemoScene.Subscenes.GameDemoMainScene;
import com.cruntchy.suprseed.Engine.Core.Scenes.SceneHeirarchy.BaseScene;
import com.cruntchy.suprseed.Engine.Core.Scenes.SceneHeirarchy.SceneManager;
import com.cruntchy.suprseed.Engine.Core.System.LogicSystem;

public class SceneHardChange implements SceneChangeStrategy<BaseScene> {

    @Override
    public void changeScene(SceneManager parentScene, BaseScene oldScene, String... sceneId) {

        for (BaseScene scene : parentScene.getRegister().getRegisterList()) {

            for (String s : sceneId) {

                if (scene.getId().equals(s)) {

                    // Remove the old scene
                    parentScene.getRegister().removeObject(scene);

                    // Clear the system register of any scene or sprites
                    LogicSystem.getInstance().removeAllObjects();


                    // Hold the new scene
                    BaseScene someScene = null;

                    // Create the new scene
                    switch (s) {
                        case "MainScene":
                            someScene = new GameDemoMainScene(parentScene, "MainScene");
                            break;
                        //case "LandingScene":
                            //someScene = new LandingScene(parentScene, "LandingScene");
                            //break;
                    }

                    // Enable the new scene
                    if (someScene != null) {

                        parentScene.getRegister().registerObject(someScene);
                    }

                    break;
                }
            }
        }
    }
}
