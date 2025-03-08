package dev.suprseed.Engine.Core.Scenes.SceneStrategy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.stream.Collectors;

import dev.suprseed.Engine.Core.Scenes.SceneHeirarchy.BaseScene;
import dev.suprseed.Engine.Core.Scenes.SceneHeirarchy.SceneManager;

public class SceneHardChange {

    private ExecutorService executorService;
    private List<Future<BaseScene>> pendingScenes;
    private SceneManager parentScene;
    private BaseScene sceneSpinner;
    private long minLoadingScreenTimeInMs = 0;
    private long startTime = 0L;
    private long elapsedTime = 0;

    public SceneHardChange(SceneManager parentScene, BaseScene sceneSpinner, long minLoadingScreenTimeInMs) {
        this.minLoadingScreenTimeInMs = minLoadingScreenTimeInMs;
        init(parentScene, sceneSpinner);
    }

    public SceneHardChange(SceneManager parentScene, BaseScene sceneSpinner) {
        init(parentScene, sceneSpinner);
    }

    private void init(SceneManager parentScene, BaseScene sceneSpinner) {
        this.parentScene = parentScene;
        this.sceneSpinner = sceneSpinner;
        executorService = Executors.newSingleThreadExecutor();
        pendingScenes = new ArrayList<>(1);
    }

    public void requestSceneChange(Callable<BaseScene> lateInitScene, String... removalIds) {

        requestMassSceneChange(List.of(lateInitScene), removalIds);
    }

    public void requestMassSceneChange(List<Callable<BaseScene>> lateInitScene, String... removalIds) {

        // Get the old scenes
        List<BaseScene> oldScenes = parentScene.getRegister().getRegisterList().stream()
                .filter(s -> Arrays.stream(removalIds).anyMatch(r -> s.getId().equals(r)))
                .collect(Collectors.toList());


        // Remove the old scenes
        for (BaseScene s : oldScenes) {

            // Cleanup the scene
            // Removes itself from the parent by default
            s.onDestroy();

            // De-reference for garbage collection
            parentScene.getRegister().removeObject(s);
            s = null;
        }


        // Stop all running scenes
        for (BaseScene s : parentScene.getRegister().getRegisterList()) {
            s.setActive(false);
            s.setDrawable(false);
        }

        // Show the spinner
        parentScene.registerScene(sceneSpinner);

        for (Callable<BaseScene> call : lateInitScene) {
            Future<BaseScene> futureScene = executorService.submit(call);
            pendingScenes.add(futureScene);
        }

        startTime = System.currentTimeMillis();
    }

    public void joinScenes() throws ExecutionException, InterruptedException {

        // See if any scenes are pending
        if (pendingScenes.isEmpty()) {

            // See if a spinner exists
            if (parentScene.getRegister().getRegisterList().stream().anyMatch(s -> s.getId().equals(sceneSpinner.getId()))) {

                elapsedTime = System.currentTimeMillis() - startTime;

                // Only stop the spinner if the min time has elapsed
                if (startTime == 0 || elapsedTime > minLoadingScreenTimeInMs) {

                    startTime = 0L;

                    parentScene.getRegister().removeObject(sceneSpinner);

                    // Resume all stopped scenes
                    for (BaseScene s : parentScene.getRegister().getRegisterList()) {
                        s.setActive(true);
                        s.setDrawable(true);
                    }

                }
            }

            return;
        }

        // Load any background loading scenes
        for (Future<BaseScene> threadWork : pendingScenes) {

            // See if the scene has been created
            if (threadWork.isDone()) {

                // Add the new scene
                BaseScene finishedScene = threadWork.get();
                finishedScene.onPost();

                // Will get active after spinner gets removed
                finishedScene.setActive(false);
                finishedScene.setDrawable(false);
                parentScene.registerScene(finishedScene);
            }
        }

        // Remove anything that is finished
        pendingScenes.removeIf(Future::isDone);
    }
}
