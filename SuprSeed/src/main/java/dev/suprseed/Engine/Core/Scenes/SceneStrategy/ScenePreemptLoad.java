package dev.suprseed.Engine.Core.Scenes.SceneStrategy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.stream.Collectors;

import dev.suprseed.Engine.Core.ErrorLogger.ErrorType;
import dev.suprseed.Engine.Core.Scenes.SceneHeirarchy.BaseScene;
import dev.suprseed.Engine.Core.Scenes.SceneHeirarchy.SceneManager;
import dev.suprseed.Engine.EngineContext;

public class ScenePreemptLoad extends SceneBackgroundLoader {

    private Map<String, Future<BaseScene>> pendingScenes;
    private boolean doJoin = false;
    private List<String> scenesToLoad;
    private List<String> processedScenes;

    public ScenePreemptLoad(SceneManager parentScene, BaseScene sceneSpinner, long minLoadingScreenTimeInMs, Callable<BaseScene> backgroundInitScene, String sceneId) {
        super(parentScene, sceneSpinner, minLoadingScreenTimeInMs);
        pendingScenes = new HashMap<>(1);
        scenesToLoad = new ArrayList<>();
        processedScenes = new ArrayList<>();
        queueSceneLoad(backgroundInitScene, sceneId);
    }

    public ScenePreemptLoad(SceneManager parentScene, BaseScene sceneSpinner, long minLoadingScreenTimeInMs, Map<String, Callable<BaseScene>> backgroundInitScenes) {
        super(parentScene, sceneSpinner, minLoadingScreenTimeInMs);
        pendingScenes = new HashMap<>(1);
        scenesToLoad = new ArrayList<>();
        processedScenes = new ArrayList<>();
        queueMassSceneLoad(backgroundInitScenes);
    }

    public void queueSceneLoad(Callable<BaseScene> backgroundInitScene, String sceneId) {

        queueMassSceneLoad(Map.of(sceneId, backgroundInitScene));
    }

    public void queueMassSceneLoad(Map<String, Callable<BaseScene>> backgroundInitScenes) {

        for (Map.Entry<String, Callable<BaseScene>> call : backgroundInitScenes.entrySet()) {
            Future<BaseScene> futureScene = executorService.submit(call.getValue());
            pendingScenes.put(call.getKey(), futureScene);
        }
    }

    public boolean isSceneReady(String sceneId) {

        if (!pendingScenes.containsKey(sceneId)) {
            EngineContext.getLogger().logMessage(ErrorType.ERROR, "The scene pre-empt loader does not contain a scene with id: " + sceneId + " !" +
                    "\nUnable to check if scene is ready!");
            return false;
        }

        return (Objects.requireNonNull(pendingScenes.get(sceneId)).isDone());
    }

    public Optional<BaseScene> get(String sceneId) throws ExecutionException, InterruptedException {

        if (!pendingScenes.containsKey(sceneId)) {
            EngineContext.getLogger().logMessage(ErrorType.ERROR, "The scene pre-empt loader does not contain a scene with id: " + sceneId + " !" +
                    "\nAn empty optional scene is being returned!");
            return Optional.empty();
        }

        return Optional.ofNullable((Objects.requireNonNull(pendingScenes.get(sceneId))).get());
    }


    public void requestSceneChange(String scenesToLoad, List<String> removalIds) {

        requestMassSceneChange(List.of(scenesToLoad), removalIds);
    }

    public void requestMassSceneChange(List<String> scenesToLoad, List<String> removalIds) {

        removeScenes(removalIds);

        this.scenesToLoad = scenesToLoad;

        // Start trying to load in the background scenes
        doJoin = true;
    }

    public void joinScenes() throws ExecutionException, InterruptedException {

        if (doJoin) {

            // Load the scenes the user requested
            for (String s : scenesToLoad) {

                // See if the scene exists
                if (pendingScenes.containsKey(s)) {

                    Future<BaseScene> value = pendingScenes.get(s);

                    // See if the scene has been created
                    if (value != null) {

                        if (value.isDone()) {

                            // Add the new scene
                            BaseScene finishedScene = value.get();

                            // Remove the finished scene
                            pendingScenes.remove(s);

                            // Will get active after spinner gets removed
                            finishedScene.setActive(false);
                            finishedScene.setDrawable(false);
                            parentScene.registerScene(finishedScene);

                            processedScenes.add(s);
                        }
                    } else {
                        processedScenes.add(s);
                        EngineContext.getLogger().logMessage(ErrorType.ERROR, "The scene pre-empt loader does not contain a scene with id: " + s + " !" +
                                "\nCould not load scene into parent!");
                    }

                } else {
                    processedScenes.add(s);
                    EngineContext.getLogger().logMessage(ErrorType.ERROR, "The scene pre-empt loader does not contain a scene with id: " + s + " !" +
                            "\nCould not load scene into parent!");
                }
            }

            // Remove all scenes that were processed from the request queue
            scenesToLoad = scenesToLoad.stream().filter(s -> processedScenes.stream().anyMatch(s::equals)).collect(Collectors.toList());
            processedScenes.clear();

            if (pendingScenes.isEmpty()) {
                doJoin = !removeSpinner();
            }
        }
    }
}
