package dev.suprseed.Engine.Core.Scenes.SceneStrategy;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

import dev.suprseed.Engine.Core.Scenes.SceneHeirarchy.BaseScene;
import dev.suprseed.Engine.Core.Scenes.SceneHeirarchy.SceneManager;

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

        // TODO: This shit should be handled by the register! An error should be logged if the scene does not exist!
        // Get the old scenes
        List<BaseScene> oldScenes = parentScene.getRegister().getRegisterList().stream()
                .filter(s -> removalIds.stream().anyMatch(r -> s.getId().equals(r)))
                .collect(Collectors.toList());


        // Remove the old scenes
        for (BaseScene s : oldScenes) {

            // Cleanup the scene
            // Removes itself from the parent by default
            s.onDestroy();

            // De-reference for garbage collection
            parentScene.getRegister().removeObject(s);
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

