package dev.suprseed.Engine.Core.Scenes.SceneStrategy;

import dev.suprseed.Engine.Core.Scenes.SceneHeirarchy.BaseScene;
import dev.suprseed.Engine.Core.Scenes.SceneHeirarchy.SceneManager;
import dev.suprseed.Engine.EngineContext;

public class SceneHardChange implements SceneChangeStrategy<BaseScene> {

    @Override
    public void changeScene(SceneManager parentScene, BaseScene oldScene, String... sceneId) {

        for (BaseScene scene : parentScene.getRegister().getRegisterList()) {

            for (String s : sceneId) {

                if (scene.getId().equals(s)) {

                    // Remove the old scene
                    parentScene.getRegister().removeObject(scene);

                    // Clear the system register of any scene or sprites
                    EngineContext.getLogicSystem().getLogicRegister().removeAllObjects();


                    // Hold the new scene
                    BaseScene someScene = null;

                    // Create the new scene
                    /*switch (s) {
                        // Note: The main scene should never have to be instantiated, instead, sub scenes should be removed and built as needed
                        //case "MainScene":
                            //someScene = new GameDemoMainScene(parentScene, "MainScene");
                            //break;
                        //case "LandingScene":
                            //someScene = new LandingScene(parentScene, "LandingScene");
                            //break;
                    }*/

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
