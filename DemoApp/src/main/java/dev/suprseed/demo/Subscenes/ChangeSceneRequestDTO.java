package dev.suprseed.demo.Subscenes;


public class ChangeSceneRequestDTO {

    private SceneType sceneType;
    private boolean isChangeRequested = false;

    public ChangeSceneRequestDTO(SceneType startingScene) {
        this.sceneType = startingScene;
    }

    public void notifyRequestChange() {
        isChangeRequested = true;
    }

    public void resolveChangeRequest() {
        isChangeRequested = false;
    }

    public boolean isChangeRequested() {
        return isChangeRequested;
    }

    public SceneType getCurrentScene() {
        return sceneType;
    }

    public void setCurrentScene(SceneType sceneType) {
        this.sceneType = sceneType;
    }
}
