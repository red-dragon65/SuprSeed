package dev.suprseed.Engine.Core.Scenes.SceneStrategy;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import dev.suprseed.Engine.Core.ErrorLogger.ErrorType;
import dev.suprseed.Engine.Core.Scenes.SceneHeirarchy.BaseScene;
import dev.suprseed.Engine.Core.Scenes.SceneHeirarchy.SceneManager;
import dev.suprseed.Engine.EngineContext;

abstract public class SceneBackgroundLoader {

    protected ExecutorService executorService;
    protected SceneManager parentScene;
    protected BaseScene sceneSpinner;
    protected long minLoadingScreenTimeInMs = 0;
    protected long startTime = 0L;
    protected long elapsedTime = 0;

    public SceneBackgroundLoader(SceneManager parentScene, BaseScene sceneSpinner, long minLoadingScreenTimeInMs) {
        this.minLoadingScreenTimeInMs = minLoadingScreenTimeInMs;
        this.parentScene = parentScene;
        this.sceneSpinner = sceneSpinner;
        sceneSpinner.setActive(false);
        sceneSpinner.setDrawable(false);
        executorService = Executors.newSingleThreadExecutor();
    }


    protected void removeScenes(List<String> removalIds) {

        Optional<BaseScene> current;

        for (String s : removalIds) {

            current = parentScene.getRegister().getScene(s);

            if (current.isPresent()) {

                // Cleanup the scene
                current.get().onDestroy();

                // De-reference for garbage collection
                parentScene.getRegister().removeObject(s);

            } else {
                EngineContext.getLogger().logMessage(ErrorType.ERROR, "Could not remove scene with removalId: " + s + " when attempting scene change strategy!");
            }

        }


        // Stop all running scenes
        for (BaseScene s : parentScene.getRegister().getRegisterList()) {
            s.setActive(false);
            s.setDrawable(false);
        }

        // Show the spinner
        parentScene.registerScene(sceneSpinner);
        sceneSpinner.setActive(true);
        sceneSpinner.setDrawable(true);

        startTime = System.currentTimeMillis();
    }

    protected boolean removeSpinner() {

        // See if a spinner exists
        if (parentScene.getRegister().getRegisterList().stream().anyMatch(s -> s.getId().equals(sceneSpinner.getId()))) {

            elapsedTime = System.currentTimeMillis() - startTime;

            // Only stop the spinner if the min time has elapsed
            if (startTime == 0 || elapsedTime > minLoadingScreenTimeInMs) {

                startTime = 0L;

                parentScene.getRegister().removeObject(sceneSpinner);
                sceneSpinner.setActive(false);
                sceneSpinner.setDrawable(false);

                // Resume all stopped scenes
                for (BaseScene s : parentScene.getRegister().getRegisterList()) {
                    s.setActive(true);
                    s.setDrawable(true);
                }

                return true;
            }
            return false;
        }
        return true;
    }
}

