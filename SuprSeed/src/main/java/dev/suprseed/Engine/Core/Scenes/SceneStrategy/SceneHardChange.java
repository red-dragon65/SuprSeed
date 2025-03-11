package dev.suprseed.Engine.Core.Scenes.SceneStrategy;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import dev.suprseed.Engine.Core.Scenes.SceneHeirarchy.BaseScene;
import dev.suprseed.Engine.Core.Scenes.SceneHeirarchy.SceneManager;

public class SceneHardChange extends SceneBackgroundLoader {

    private List<Future<BaseScene>> pendingScenes;
    private boolean doJoin = false;

    public SceneHardChange(SceneManager parentScene, BaseScene sceneSpinner, long minLoadingScreenTimeInMs) {
        super(parentScene, sceneSpinner, minLoadingScreenTimeInMs);
        pendingScenes = new ArrayList<>(1);
    }

    public void requestSceneChange(Callable<BaseScene> lateInitScene, List<String> removalIds) {

        requestMassSceneChange(List.of(lateInitScene), removalIds);
    }

    public void requestMassSceneChange(List<Callable<BaseScene>> lateInitScene, List<String> removalIds) {

        removeScenes(removalIds);

        // Start trying to load in the background scenes
        for (Callable<BaseScene> call : lateInitScene) {
            Future<BaseScene> futureScene = executorService.submit(call);
            pendingScenes.add(futureScene);
        }

        // Start trying to load in the background scenes
        doJoin = true;
    }

    public void joinScenes() throws ExecutionException, InterruptedException {

        if (doJoin) {

            // Track each scene
            Iterator<Future<BaseScene>> iterator = pendingScenes.iterator();
            Future<BaseScene> current;

            // Load any background loading scenes
            while (iterator.hasNext()) {

                // Get the next item in the list
                current = iterator.next();

                // See if the scene has been created
                if (current.isDone()) {

                    // Add the new scene
                    BaseScene finishedScene = current.get();
                    finishedScene.onPost();

                    // Will get active after spinner gets removed
                    finishedScene.setActive(false);
                    finishedScene.setDrawable(false);
                    parentScene.registerScene(finishedScene);

                    // Remove the processed callable
                    iterator.remove();
                }
            }

            // See if any scenes are pending
            if (pendingScenes.isEmpty()) {
                doJoin = !removeSpinner();
            }
        }
    }
}
